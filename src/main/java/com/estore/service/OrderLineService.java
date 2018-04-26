package com.estore.service;

import com.estore.model.BaseResponse;
import com.estore.model.OrderLineModel;
import com.estore.utils.EstoreException;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderLineService {
    /**
     * 查询用户的购物车
     * @param userId 用户id
     * @return 购物车
     */
    List<OrderLineModel> queryAllOrderLine(Integer userId) throws EstoreException;

    /**
     * 添加商品到购物车
     * @param orderLineModel 商品项
     * @return BaseResponse
     */
    BaseResponse addProductToShopCart(OrderLineModel orderLineModel) throws Exception;

    /**
     * 删除购物项
     * @param id id
     * @return BaseResponse
     */
    BaseResponse deleteOrderLine(Integer id);

    /**
     * 根据id 查询
     * @param id id
     * @return
     */
    OrderLineModel queryOrderLineModelById(Integer id);

    BaseResponse updateOrderLineModel(OrderLineModel orderLineModel) throws Exception;
}
