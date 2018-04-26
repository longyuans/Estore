package com.estore.manager.bizManager;

import com.estore.bean.Order;
import com.estore.dao.OrderLineMapper;
import com.estore.dao.OrderMapper;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.model.BaseResponse;
import com.estore.model.OrderModel;
import com.estore.utils.EstoreException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bizCreateOrderManager")
public class BizCreateOrderManagerImpl extends BaseManagerImpl<OrderModel, BaseResponse> {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderLineMapper orderLineMapper;

    @Override
    protected BaseResponse doProcess(OrderModel orderModel) throws Exception {
        BaseResponse response = new BaseResponse();
        checkParam(orderModel);
        Order order = new Order();
        order.setMoney(orderModel.getMoney());
        order.setPayWay(orderModel.getPayWay());
        order.setReceiverId(orderModel.getReceiverId());
        order.setUserId(orderModel.getUserId());
        int countOrder = orderMapper.insertSelective(order);
        //在insert的同时对 orderLine 修改其orderId
        if(CollectionUtils.isEmpty(orderModel.getOrderLineModels())){
            throw new EstoreException(ErrorCodeEnum.createOrderError.toString(), "订单创建失败，订单项为空");
        }
        orderModel.getOrderLineModels().parallelStream().forEachOrdered(orderLineModel -> {
            orderLineMapper.updateOrderId(order.getId(), orderLineModel.getId());
        });
        if (1 != countOrder) {
            response.setSuccess(false);
            response.setNeedRetry(true);
            response.setErrorCode(ErrorCodeEnum.createOrderError.toString());
            throw new EstoreException(ErrorCodeEnum.createOrderError.toString(), "订单创建失败，插入数据库出错");
        }
        return response;
    }

    private void checkParam(OrderModel orderModel) throws EstoreException {
        if (null == orderModel) {
            throw new EstoreException(ErrorCodeEnum.createOrderError.toString(), "订单创建失败，传参为空");
        }
        if (orderModel.getUserId() == null) {
            throw new EstoreException(ErrorCodeEnum.createOrderError.toString(), "订单创建失败，用户为空");
        }
        if (orderModel.getReceiverId() == null) {
            throw new EstoreException(ErrorCodeEnum.createOrderError.toString(), "订单创建失败，收件人为空");
        }
        if (CollectionUtils.isEmpty(orderModel.getOrderLineModels())) {
            throw new EstoreException(ErrorCodeEnum.createOrderError.toString(), "订单创建失败，产品为空");
        }
        if (orderModel.getPayWay() == null) {
            throw new EstoreException(ErrorCodeEnum.createOrderError.toString(), "订单创建失败，支付方式为空");
        }
    }
}
