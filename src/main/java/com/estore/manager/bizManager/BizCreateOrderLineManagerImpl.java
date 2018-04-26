package com.estore.manager.bizManager;

import com.estore.bean.OrderLine;
import com.estore.bean.User;
import com.estore.dao.OrderLineMapper;
import com.estore.dao.UserMapper;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.model.BaseResponse;
import com.estore.model.OrderLineModel;
import com.estore.utils.EstoreException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("bizCreateOrderLineManager")
public class BizCreateOrderLineManagerImpl extends BaseManagerImpl<OrderLineModel,BaseResponse> {

    @Autowired
    private OrderLineMapper orderLineMapper;

    @Override
    protected BaseResponse doProcess(OrderLineModel orderLineModel) throws Exception {
        BaseResponse response = new BaseResponse();
        if (null == orderLineModel || null == orderLineModel.getUser()) {
            response.setErrorCode(ErrorCodeEnum.orderLineCreateError.toString());
            throw new EstoreException("4001", "购物项添加失败,传参错误");
        }
        if (1 > orderLineModel.getAmount()) {
            response.setErrorCode(ErrorCodeEnum.orderLineCreateError.toString());
            throw new EstoreException("4002", "购物项添加创建失败,数量参数出错,");
        }
        OrderLine orderLine = orderLineMapper.selectByProductId(orderLineModel.getProduct().getId());
        int count;
        if (null != orderLine){
            //说明该产品已经添加过，现在只修改其数量,总金额即可
            Integer amount = orderLineModel.getAmount()+orderLine.getAmount();
            BigDecimal totalPrice = new BigDecimal(amount).multiply(orderLineModel.getProduct().getPrice());
            count = orderLineMapper.updateAmountByPrimaryKey(totalPrice,amount,orderLine.getId());
        }else {
            //添加该商品
            OrderLine o = new OrderLine();
            o.setAmount(orderLineModel.getAmount());
            o.setProductId(orderLineModel.getProduct().getId());
            o.setTotalPrice(orderLineModel.getTotalPrice());
            o.setUserId(orderLineModel.getUser().getId());
            o.setVersion(orderLineModel.getVersion());
            count = orderLineMapper.insertSelective(o);
        }
        if (1 != count) {
            response.setSuccess(false);
            response.setNeedRetry(true);
            response.setErrorCode(ErrorCodeEnum.orderLineCreateError.toString());
            throw new EstoreException("4001", "购物项创建失败");
        }
        return response;
    }
}
