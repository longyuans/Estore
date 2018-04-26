package com.estore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SkipController {
    @RequestMapping(value = "skipToRegister",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToRegister(){
        return "register";
    }

    @RequestMapping(value ="skipToLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToLogin(){
        return "login";
    }

    @RequestMapping(value ="skipToIndexSuccess",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToIndex(){
        return "indexSuccess";
    }

    @RequestMapping(value ="forgetPassword",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToForgetPassword(){
        return "forgetPassword";
    }

    @RequestMapping(value ="forgetPassword1",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToForgetPassword1(){
        return "forgetPassword1";
    }

    @RequestMapping(value ="forgetPassword2",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToForgetPassword2(){
        return "forgetPassword2";
    }

    @RequestMapping(value ="forgetPassword3",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToForgetPassword3(){
        return "forgetPassword3";
    }

    @RequestMapping(value ="userInfo",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToUserInfo(){
        return "userinfo";
    }

    @RequestMapping(value ="viewBook",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToViewBook(){
        return "viewBook";
    }

    @RequestMapping(value ="listJsp",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToBookList(){
        return "list";
    }

    @RequestMapping(value ="shopCart",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToShopCart(){
        return "shopCart";
    }

    @RequestMapping(value ="emptyCart",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToEmptyCart(){
        return "emptyCar";
    }

    @RequestMapping(value ="confirmOrder",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToConfirmOrder(){
        return "confirm";
    }

    @RequestMapping(value ="payWay",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToPayWay(){
        return "Payfor";
    }

    @RequestMapping(value ="payCommit",method = {RequestMethod.POST,RequestMethod.GET})
    public String skipToPayCommit(){
        return "PayforCommit";
    }
}
