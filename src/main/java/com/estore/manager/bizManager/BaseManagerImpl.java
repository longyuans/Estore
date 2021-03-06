package com.estore.manager.bizManager;

import com.estore.bean.Idempotency;
import com.estore.dao.IdempotencyMapper;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.estoreEnum.OperateEnum;
import com.estore.utils.DistributeLockUtils;
import com.estore.utils.EstoreException;
import com.estore.utils.JedisPoolUtils;
import com.estore.utils.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public abstract class BaseManagerImpl<T, R> implements com.estore.manager.BaseBizManager<T, R> {

    @Autowired
    private IdempotencyMapper idempotencyMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Transactional(value = "transactionManager", rollbackFor = {EstoreException.class})
    public R process(T request) throws Exception {
        if (checkIdem(request)) {
            throw new EstoreException(ErrorCodeEnum.idemError.toString(), "幂等错误");
        }
        //TODO获取分布式锁
        DistributeLockUtils lock = new DistributeLockUtils(jedisCluster);
        String indentifier = lock.lockWithTimeout("doProcess", 5000, 1000);
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        //doProcess 专门处理业务
        R response = doProcess(request);
        lock.releaseLock("doProcess", indentifier);
        System.out.println(Thread.currentThread().getName() + "释放了锁");
        return response;
    }

    private boolean checkIdem(T request) throws EstoreException {
        //result 为true 则幂等
        boolean result = false;
        if (null == request) {
            throw new EstoreException("500", "通用错误");
        }
        String operationType = (String) ReflectionUtil.getValue(request, "operate", "");
        String sourceId = (String) ReflectionUtil.getValue(request, "sourceId", "");
        String idemKey = operationType + sourceId;
        if (!OperateEnum.isIn(operationType)) {
            throw new EstoreException(ErrorCodeEnum.operateError.toString(), "操作类型错误");
        }
        try {
            if (!StringUtils.isEmpty(idemKey)) {
                Idempotency idempotency = idempotencyMapper.selectByIdem(idemKey);
                if (null == idempotency) {
                    Idempotency idempotency1 = new Idempotency();
                    idempotency1.setIdemKey(idemKey);
                    idempotencyMapper.insertSelective(idempotency1);
                } else {
                    throw new EstoreException(ErrorCodeEnum.idemError.toString(), "幂等错误");
                }
            } else {
                throw new EstoreException(ErrorCodeEnum.getIdemParamError.toString(), "幂等参数错误");
            }
        } catch (Exception e) {
            result = true;
            e.printStackTrace();
        }
        return result;
    }

    protected abstract R doProcess(T request) throws Exception;
}
