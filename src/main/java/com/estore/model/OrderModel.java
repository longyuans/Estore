package com.estore.model;

import com.estore.bean.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel extends BaseBean{

    //此订单在落表时不会包含具体的商品信息，而是去修改商品项表，修改其中的orderId

    private Integer id;

    //用户id
    private Integer userId;

    //收件人id
    private Integer receiverId;

    //总金额
    private BigDecimal money;

    //支付方式
    private String payWay;

    //所有的购物项
    private List<OrderLineModel> orderLineModels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public List<OrderLineModel> getOrderLineModels() {
        return orderLineModels;
    }

    public void setOrderLineModels(List<OrderLineModel> orderLineModels) {
        this.orderLineModels = orderLineModels;
    }
}
