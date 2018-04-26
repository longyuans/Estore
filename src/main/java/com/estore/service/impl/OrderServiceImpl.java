package com.estore.service.impl;

import com.estore.bean.Order;
import com.estore.estoreEnum.OperateEnum;
import com.estore.manager.bizManager.BaseManagerImpl;
import com.estore.model.BaseResponse;
import com.estore.model.OrderModel;
import com.estore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("bizCreateOrderManager")
    private BaseManagerImpl<OrderModel,BaseResponse> bizCreateOrderManager;

    @Override
    public BaseResponse createOrder(OrderModel orderModel) throws Exception {
        orderModel.setSourceId((int) (Math.random() * 100000) + "");
        orderModel.setOperate(OperateEnum.createOrder.toString());
        return bizCreateOrderManager.process(orderModel);
    }
}
