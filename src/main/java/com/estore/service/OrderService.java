package com.estore.service;

import com.estore.model.BaseResponse;
import com.estore.model.OrderModel;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param orderModel 订单
     * @return BaseResponse
     */
    BaseResponse createOrder(OrderModel orderModel) throws Exception;
}
