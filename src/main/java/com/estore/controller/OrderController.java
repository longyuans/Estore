package com.estore.controller;

import com.estore.bean.Order;
import com.estore.bean.Receiver;
import com.estore.bean.User;
import com.estore.model.OrderLineModel;
import com.estore.model.OrderModel;
import com.estore.model.ReceiverResponse;
import com.estore.service.CategoryDetailService;
import com.estore.service.OrderLineService;
import com.estore.service.OrderService;
import com.estore.service.ReceiverService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private ReceiverService receiverService;
    @Autowired
    private OrderService orderService;

    //收单
    @RequestMapping(value = "confirmServlet", method = {RequestMethod.POST, RequestMethod.GET})
    public void insureOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        // 获取从购物车选中的商品列表
        String[] OrdId = req.getParameterValues("check");
        String[] Amount = req.getParameterValues("amount");
        List<OrderLineModel> orderLines = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;
        // 更新选中的商品的数量及价格
        for (int i = 0; i < OrdId.length; i++) {
            OrderLineModel orderLineModel = orderLineService.queryOrderLineModelById(Integer.parseInt(OrdId[i]));
            if (null == orderLineModel) {
                System.out.println("OrderController.insureOrder res = { 订单项不存在}");
                resp.sendRedirect("/estore/showShopCartServlet");
            }
            BigDecimal price = orderLineModel.getProduct().getPrice();
            BigDecimal singlePrice = price.multiply(new BigDecimal(Amount[i]));
            sum = singlePrice.add(sum);
            if (Integer.parseInt(Amount[i]) == orderLineModel.getAmount()) {
                //价格变动则商品未改动，则无需update db
                orderLines.add(orderLineModel);
            } else {
                orderLineModel.setAmount(Integer.parseInt(Amount[i]));
                orderLineModel.setTotalPrice(singlePrice);
                try {
                    orderLineService.updateOrderLineModel(orderLineModel);
                    orderLines.add(orderLineModel);
                } catch (Exception e) {
                    resp.sendRedirect("/estore/showShopCartServlet");
                    e.printStackTrace();
                }
            }
        }
        Integer all = orderLines.size();
        //产品的类型
        // session.setAttribute("orderProductCatDetail", all);
        session.setAttribute("confirmAllNum", all);
        session.setAttribute("confirmSumPrice", sum);
        session.setAttribute("OrdPro", orderLines);
        resp.sendRedirect("/estore/receiverAllServlet");
    }

    //确认订单
    @RequestMapping(value = "receiverAllServlet", method = {RequestMethod.POST, RequestMethod.GET})
    public void conformOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("LoginSuccessUser");
        List<Receiver> receivers = receiverService.queryAllReceiverByUserId(user.getId());
        System.out.println("OrderController.showShopCartServlet.查询receiver res ={}" + receivers.size());
        if (receivers.size() > 0) {
            session.setAttribute("receiver", receivers);
        }
        resp.sendRedirect("/estore/confirmOrder");
        /*else {
            resp.sendRedirect("/estore/showShopCartServlet");
        }*/
    }

    //下单
    @RequestMapping(value = "receiverServlet", method = {RequestMethod.POST, RequestMethod.GET})
    public void doOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        // 获取收件人填入的信息
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        // 获取当前用户
        User user = (User) session.getAttribute("LoginSuccessUser");
        // 获取当前用户所有的收货信息
        List<Receiver> receivers = receiverService.queryAllReceiverByUserId(user.getId());
        // 判断是否有信息
        // 判断收货人信息是否有的为空
        boolean flag = false;
        if ((StringUtils.isEmpty(name)) || (StringUtils.isEmpty(address)) || (StringUtils.isEmpty(phone))) {
            resp.sendRedirect("/estore/confirmServlet");
        } else {
            if (receivers.size() > 0) {
                System.out.println("OrderController.doOrder req = {} 进入receiver的对比");
                for (int j = 0; j < receivers.size(); j++) {
                    // 判断当前填入的信息是否和数据库中查询的信心是否相等
                    if (name.equals(receivers.get(j).getName())
                            && address.equals(receivers.get(j).getAddress())
                            && phone.equals(receivers.get(j).getPhone())) {
                        System.out.println("OrderController.doOrder req = {} 有receiver，并且三个参数相等");
                        session.setAttribute("receiver", receivers.get(j));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    Receiver receiver = new Receiver();
                    receiver.setAddress(address);
                    receiver.setName(name);
                    receiver.setPhone(phone);
                    receiver.setUserId(user.getId());
                    ReceiverResponse receiverResponse = receiverService.insertReceiver(receiver);
                    System.out.println("OrderController.doOrder req = {} 有receiver，但是与新传入的值不同，新插入:" + receiver);
                    receiver.setId(receiverResponse.getReceiverId());
                    session.setAttribute("receiver", receiver);
                }
            } else {
                System.out.println("OrderController.doOrder req = {} 准备插入新的receiver");
                Receiver receiver = new Receiver();
                receiver.setAddress(address);
                receiver.setName(name);
                receiver.setPhone(phone);
                receiver.setUserId(user.getId());
                receiverService.insertReceiver(receiver);
                session.setAttribute("receiver", receiver);
            }
            BigDecimal sum_price = (BigDecimal) session.getAttribute("confirmSumPrice");
            session.setAttribute("allMoney", sum_price);
            resp.sendRedirect("/estore/payWay");
        }
    }

    //删除收货人
    @RequestMapping(value = "deleteReceiverServlet", method = {RequestMethod.POST, RequestMethod.GET})
    public void deleteReceiver(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("ReceiverId"));
        receiverService.deleteReceiver(id);
        resp.sendRedirect("/estore/receiverAllServlet");
    }

    //完成订单并存表
    @RequestMapping(value = "PayforServlet", method = {RequestMethod.POST, RequestMethod.GET})
    public void doOrderWithAllInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

        //生成订单表
        //获取支付方式
        OrderModel orderModel = new OrderModel();
        String payWay = req.getParameter("radio");
        orderModel.setPayWay(payWay);
        //获取收件人信息
        Receiver receiver = (Receiver) session.getAttribute("receiver");
        orderModel.setReceiverId(receiver.getId());
        //获取用户信息
        User currentUser = (User) session.getAttribute("LoginSuccessUser");
        orderModel.setUserId(currentUser.getId());
        //获取订单项信息
        List<OrderLineModel> orderLines = (List<OrderLineModel>) session.getAttribute("OrdPro");
        orderModel.setOrderLineModels(orderLines);
        //获取总金额
        BigDecimal sum_price = (BigDecimal) session.getAttribute("confirmSumPrice");
        orderModel.setMoney(sum_price);
        try {
            orderService.createOrder(orderModel);
            resp.sendRedirect("/estore/payCommit");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/estore/receiverAllServlet");
        }
    }
}
