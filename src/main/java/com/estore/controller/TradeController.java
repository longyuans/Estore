package com.estore.controller;

import com.estore.bean.*;
import com.estore.model.CategoryModel;
import com.estore.service.CategoryService;
import com.estore.service.ProductService;
import com.estore.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * 交易相关的controller
 */
@Controller
public class TradeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ReportService reportService;

    //index界面查询相关数据的控制器
    @RequestMapping(value = "indexS", method = {RequestMethod.POST, RequestMethod.GET})
    public void index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        // 查询书的类别名并set到session中
        List<CategoryModel> categoryModelList = categoryService.queryAllCategory();
        session.setAttribute("list", categoryModelList);

        // 查询简报
        List<Report> Reports = reportService.queryAllReport();

        // 底部书的推荐，flag=1，热卖
        Set<Product> Products = productService.queryProductByFlag("1");

        // 畅销书籍的推荐，flag=3，特推
        Set<Product> BestPro = productService.queryProductByFlag("3");

        session.setAttribute("report", Reports);
        session.setAttribute("product", Products);
        session.setAttribute("bestPro", BestPro);

        User user = (User) req.getSession().getAttribute("LoginSuccessUser");
        if (user == null) {
            resp.sendRedirect("/estore/index.jsp");
        } else {
            resp.sendRedirect("/estore/skipToIndexSuccess");
        }
    }
}
