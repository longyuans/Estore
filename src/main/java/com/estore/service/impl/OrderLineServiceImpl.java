package com.estore.service.impl;

import com.estore.bean.Order;
import com.estore.bean.OrderLine;
import com.estore.bean.Product;
import com.estore.bean.User;
import com.estore.dao.OrderLineMapper;
import com.estore.dao.OrderMapper;
import com.estore.dao.ProductMapper;
import com.estore.dao.UserMapper;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.estoreEnum.OperateEnum;
import com.estore.manager.BaseBizManager;
import com.estore.model.BaseResponse;
import com.estore.model.OrderLineModel;
import com.estore.service.OrderLineService;
import com.estore.service.ProductService;
import com.estore.utils.EstoreException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private OrderLineMapper orderLineMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    @Qualifier("bizCreateOrderLineManager")
    private BaseBizManager<OrderLineModel, BaseResponse> bizCreateOrderLineManager;
    @Autowired
    @Qualifier("bizUpdateOrderLineManager")
    private BaseBizManager<OrderLineModel, BaseResponse> bizUpdateOrderLineManager;

    @Override
    public List<OrderLineModel> queryAllOrderLine(Integer userId) throws EstoreException {
        List<OrderLine> orderLineList = orderLineMapper.selectAllOrderLineByUserId(userId);
        //生成 OrderLineModel
        if (CollectionUtils.isNotEmpty(orderLineList)) {
            List<OrderLineModel> orderLineModelList = new ArrayList<>();
            for (OrderLine orderLine : orderLineList) {
                OrderLineModel orderLineModel = new OrderLineModel();
                orderLineModel.setId(orderLine.getId());
                orderLineModel.setUser(userMapper.selectByPrimaryKey(orderLine.getUserId()));
                orderLineModel.setProduct(productService.queryProductById(orderLine.getProductId()));
                orderLineModel.setVersion(orderLine.getVersion());
                orderLineModel.setAmount(orderLine.getAmount());
                orderLineModel.setTotalPrice(orderLine.getTotalPrice());
                orderLineModelList.add(orderLineModel);
            }
            return orderLineModelList;
        } else {
            return null;
        }
    }

    @Override
    public BaseResponse addProductToShopCart(OrderLineModel orderLineModel) throws Exception {
        orderLineModel.setSourceId((int) (Math.random() * 100000) + "");
        orderLineModel.setOperate(OperateEnum.createOrderLine.toString());
        return bizCreateOrderLineManager.process(orderLineModel);
    }

    @Override
    public BaseResponse deleteOrderLine(Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        int count = orderLineMapper.deleteOrderLine(id);
        if (1 != count) {
            baseResponse.setSuccess(false);
            baseResponse.setNeedRetry(true);
            baseResponse.setErrorCode(ErrorCodeEnum.orderLineDelError.toString());
        }
        return baseResponse;
    }

    @Override
    public OrderLineModel queryOrderLineModelById(Integer id) {
        OrderLine orderLine = orderLineMapper.selectByPrimaryKey(id);
        OrderLineModel orderLineModel = new OrderLineModel();
        orderLineModel.setId(orderLine.getId());
        orderLineModel.setUser(userMapper.selectByPrimaryKey(orderLine.getUserId()));
        orderLineModel.setProduct(productService.queryProductById(orderLine.getProductId()));
        orderLineModel.setVersion(orderLine.getVersion());
        orderLineModel.setAmount(orderLine.getAmount());
        orderLineModel.setTotalPrice(orderLine.getTotalPrice());
        return orderLineModel;
    }

    @Override
    public BaseResponse updateOrderLineModel(OrderLineModel orderLineModel) throws Exception {
        orderLineModel.setSourceId((int) (Math.random() * 100000) + "");
        orderLineModel.setOperate(OperateEnum.deleteOrderLine.toString());
        return bizUpdateOrderLineManager.process(orderLineModel);
    }
}
