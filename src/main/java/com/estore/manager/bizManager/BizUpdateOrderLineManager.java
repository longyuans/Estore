package com.estore.manager.bizManager;

import com.estore.bean.OrderLine;
import com.estore.dao.OrderLineMapper;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.model.BaseResponse;
import com.estore.model.OrderLineModel;
import com.estore.utils.EstoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bizUpdateOrderLineManager")
public class BizUpdateOrderLineManager extends BaseManagerImpl<OrderLineModel,BaseResponse>  {

    @Autowired
    private OrderLineMapper orderLineMapper;

    @Override
    protected BaseResponse doProcess(OrderLineModel orderLineModel) throws Exception {

        BaseResponse response = new BaseResponse();

        if (null == orderLineModel || null == orderLineModel.getId()) {
            setResponseFalse(response);
            throw new EstoreException("4005", "购物项添加失败,传参错误");
        }
        if (1 > orderLineModel.getAmount()) {
            setResponseFalse(response);
            throw new EstoreException("4005", "购物项添加创建失败,数量参数出错,");
        }
        OrderLine orderLine = orderLineMapper.selectByPrimaryKey(orderLineModel.getId());
        if (null == orderLine) {
            setResponseFalse(response);
            throw new EstoreException("4005", "购物项不存在,");
        }
        int count = orderLineMapper.updateAmountByPrimaryKey(orderLineModel.getTotalPrice(),orderLineModel.getAmount(),orderLineModel.getId());
        if (1 != count) {
            setResponseFalse(response);
            throw new EstoreException("4005", "购物项修改失败");
        }
        return response;
    }
    private void setResponseFalse(BaseResponse response){
        response.setSuccess(false);
        response.setNeedRetry(true);
        response.setErrorCode(ErrorCodeEnum.orderLineUpdateError.toString());
    }
}
