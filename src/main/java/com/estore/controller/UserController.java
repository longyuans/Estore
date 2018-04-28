package com.estore.controller;

import com.estore.bean.User;
import com.estore.estoreEnum.OperateEnum;
import com.estore.mysqlRouter.DataSourceExchange;
import com.estore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DataSourceExchange dataSourceExchange;

    @RequestMapping(value = "RegistServlet", method = RequestMethod.POST)
    //@ResponseBody
    public void registerUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        //String zip = req.getParameter("zip");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");
        System.out.println("name:" + name + " password:" + password + " address" + address + " tel:" + telephone + " email:" + email);

        User user = new User();
        user.setSourceId((int) (Math.random() * 100000) + "");
        user.setOperate(OperateEnum.createUser.toString());
        user.setName(name);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(telephone);
        PrintWriter out = resp.getWriter();
        try {
            userService.createUser(user);
            out.write("<script> alert('congratulations,register success')</script>");
            System.out.println("UserController.registerUser = {} 注册成功===============================");
            //flush后才能输出弹框，但是同时resp关闭，无法完成跳转了
            resp.sendRedirect("/estore/skipToLogin");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            out.write("<script> alert('sorry,this userName has been registered  ')</script>");
            resp.sendRedirect("/estore/skipToRegister");
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "LoginServlet", method = RequestMethod.POST)
    public void userLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        System.out.println("UserController.userLogin = {} name:" + name + " --- password:" + password);
        try {
            //设置选择数据源的参数
            dataSourceExchange.setUserName(name);

            //query user from db
            User user = userService.queryUserByName(name);

            PrintWriter out = resp.getWriter();
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    System.out.println("登入成功");
                    HttpSession session = req.getSession();
                    session.setAttribute("LoginSuccessUser", user);
                    resp.sendRedirect("/estore/indexS");
                } else {
                    out.write("<script> alert('password error ')</script>");
                    resp.sendRedirect("/estore/skipToLogin");
                    out.flush();
                    System.out.println("UserController.userLogin = {}密码错误");
                }
            } else {
                System.out.println("用户不存在");
                out.write("<script> alert('user not exist,please register first ')</script>");
                resp.sendRedirect("/estore/skipToLogin");
                out.flush();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/estore/skipToLogin");
        }
    }

    //注销用户
    @RequestMapping(value = "Cancellation ")
    public String cancelUser(HttpServletRequest req) {
        req.getSession().removeAttribute("LoginSuccessUser");
        return "login";
    }

    //忘记密码，查询用户
    @RequestMapping(value = "FindLostPassword", method = RequestMethod.POST)
    public void findLostPassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("txtUser");
        String imgCode = req.getParameter("txtVcode");
        String code = (String) req.getSession().getAttribute("imgCode");

        PrintWriter out = resp.getWriter();
        try {
            User user = userService.queryUserByName(username);
            if (user != null && imgCode.equals(code)) {
                System.out.println("查找成功");
                HttpSession session = req.getSession();
                session.setAttribute("FindUser", user);
                System.out.println("UserController.findLostPassword user = {}" + user);
                resp.sendRedirect("/estore/forgetPassword1");
            } else {
                System.out.println("没有该用户");
                out.print("<script> alert('用户名不存在,请重新输入 ')</script>");
                resp.sendRedirect("/estore/forgetPassword");
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/estore/forgetPassword");
        }
    }

    //重置密码
    @RequestMapping(value = "UpdateUserInfo", method = RequestMethod.POST)
    public void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        User user = (User) req.getSession().getAttribute("FindUser");
        String newPassword = req.getParameter("password");
        String surePassword = req.getParameter("surepassword");
        System.out.println("UserController.updateUserInfo = {}" + newPassword + " == " + surePassword);
        user.setSourceId((int) (Math.random() * 100000) + "");
        user.setOperate(OperateEnum.updateUser.toString());
        PrintWriter out = resp.getWriter();

        if (StringUtils.isNotEmpty(newPassword) && newPassword.equals(surePassword)) {
            user.setPassword(newPassword);
            try {
                userService.updateUser(user);
                req.getSession().setAttribute("LoginSuccessUser", user);
                resp.sendRedirect("/estore/forgetPassword3");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("/estore/forgetPassword2");
            }
        } else {
            System.out.println("UserController.updateUserInfo = {}两次密码不正确");
            out.print("<script> alert('两次密码不正确,请重新输入 ')</script>");
            resp.sendRedirect("/estore/forgetPassword2");
            out.flush();
            out.close();
        }
    }

    //更新用户的信息
    @RequestMapping(value = "UpdateUserInfo2", method = RequestMethod.POST)
    public void updateUserInfo2(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        User user = (User) req.getSession().getAttribute("LoginSuccessUser");
        String newPassword = req.getParameter("password");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        user.setSourceId((int) (Math.random() * 100000) + "");
        user.setOperate(OperateEnum.updateUser.toString());
        user.setPhone(phone);
        user.setName(name);
        user.setAddress(address);
        user.setPassword(newPassword);
            try {
                userService.updateUser(user);
                req.getSession().setAttribute("LoginSuccessUser", user);
                resp.sendRedirect("/estore/skipToIndexSuccess");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
