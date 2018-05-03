package com.estore.manager.bizManager;

import com.estore.bean.User;
import com.estore.dao.UserMapper;
import com.estore.estoreEnum.DataSourceEnum;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.model.BaseResponse;
import com.estore.mysqlRouter.DataSourceHolder;
import com.estore.utils.EstoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bizCreateUserManager")
public class BizCreateUserManagerImpl extends BaseManagerImpl<User,BaseResponse> {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseResponse doProcess(User user) throws Exception {
        BaseResponse response = new BaseResponse();
        if (null == user) {
            response.setErrorCode(ErrorCodeEnum.createUserParamError.toString());
            throw new EstoreException("1001", "用户创建失败,传参错误");
        }
        if (null == user.getName() || null == user.getPassword()) {
            response.setErrorCode(ErrorCodeEnum.createUserParamError.toString());
            throw new EstoreException("1001","用户创建失败,传参错误,");
        }
        User u = userMapper.selectByName(user.getName());
        if (null != u){
            response.setErrorCode(ErrorCodeEnum.userAlreadyHaveError.toString());
            throw new EstoreException("1003","用户创建失败,用户已经存在,");
        }
        int count = userMapper.insertSelective(user);
        if (1 != count) {
            response.setSuccess(false);
            response.setNeedRetry(true);
            response.setErrorCode(ErrorCodeEnum.createUserError.toString());
            throw new EstoreException("1004", "用户创建失败");
        }
        return response;
    }
}
