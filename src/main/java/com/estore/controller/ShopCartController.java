package com.estore.controller;

import com.estore.bean.Product;
import com.estore.bean.User;
import com.estore.model.OrderLineModel;
import com.estore.service.OrderLineService;
import com.estore.service.ProductService;
import com.estore.utils.EstoreException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShopCartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderLineService orderLineService;

    //查看购物车
    @RequestMapping(value ="showShopCartServlet",method = {RequestMethod.POST,RequestMethod.GET})
    public void shopCartServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("LoginSuccessUser");
        List<OrderLineModel> orderLines = null;
        if (null == user){
            PrintWriter out = resp.getWriter();
            out.print("<script> alert('请先登入 ')</script>");
            out.flush();
            out.close();
        }
        try {
            if (user != null) {
                orderLines = orderLineService.queryAllOrderLine(user.getId());
            }
            session.setAttribute("OrdLine", orderLines);
            if (CollectionUtils.isNotEmpty(orderLines)) {
                resp.sendRedirect("/estore/shopCart");
            } else {
                resp.sendRedirect("/estore/emptyCart");
            }
        } catch (EstoreException e) {
            resp.sendRedirect("/estore/viewBook");
            e.printStackTrace();
        }
    }
    //添加商品到购物车
    @RequestMapping(value ="addProductToShopCartServlet",method = {RequestMethod.POST,RequestMethod.GET})
    public void addProductToShopCart(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // 获取加入购物车的商品数量
        Integer amount = Integer.parseInt(req.getParameter("amount"));
        // 获取用户信息
        User user = (User) req.getSession().getAttribute("LoginSuccessUser");
        // 获取书本信息
        Integer id =  Integer.parseInt((String) req.getSession().getAttribute("ProductId"));
        Product product = productService.queryProductById(id);
        // 获取加入购物车的商品版本
        String product_version = req.getParameter("version");
        //将加入购物车的商品信息添加到购物车
        BigDecimal totalAmount = new BigDecimal(amount).multiply(product.getPrice());
        try {
            OrderLineModel orderLineModel = new OrderLineModel();
            orderLineModel.setUser(user);
            orderLineModel.setTotalPrice(totalAmount);
            orderLineModel.setAmount(amount);
            orderLineModel.setVersion(product_version);
            orderLineModel.setProduct(product);
            orderLineService.addProductToShopCart(orderLineModel);
            resp.sendRedirect("/estore/viewBook");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //删除购物项
    @RequestMapping(value ="delCartProServlet",method = {RequestMethod.POST,RequestMethod.GET})
    public void deleteOrderLine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("OrdId"));
        orderLineService.deleteOrderLine(id);
        resp.sendRedirect("/estore/showShopCartServlet");
    }
}
