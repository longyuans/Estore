package com.estore.dao;

import com.estore.bean.OrderLine;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderLineMapper {
    List<OrderLine> selectAllOrderLineByUserId(@Param("userId") Integer userId);

    int insertSelective(OrderLine orderLine);

    OrderLine selectByPrimaryKey(@Param("id") Integer id);

    int updateAmountByPrimaryKey(@Param("totalPrice") BigDecimal totalPrice,@Param("amount") Integer amount, @Param("id") Integer id);

    OrderLine selectByProductId(@Param("productId") Integer productId,@Param("userId") Integer userId);

    int deleteOrderLine(@Param("id") Integer id);

    int updateOrderId(@Param("orderId") Integer orderId,@Param("id") Integer id);
}
