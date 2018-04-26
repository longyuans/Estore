package com.estore.controller;

import com.estore.bean.CategoryDetail;
import com.estore.bean.Product;
import com.estore.service.CategoryDetailService;
import com.estore.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class SearchContoller {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryDetailService categoryDetailService;

    @RequestMapping(value ="searchProServlet",method = {RequestMethod.POST,RequestMethod.GET})
    public void SearchProServlet(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        HttpSession session = req.getSession();
        String searchVal = req.getParameter("searchVal");
       // searchVal = new String(searchVal.getBytes("ISO8859-1"), "UTF-8");
        if(StringUtils.isNotEmpty(searchVal)){
            String name = "%" + searchVal + "%";
            Set<Product> products = productService.queryProductByName(name);
            //?what mean
            for (Product p : products) {
                CategoryDetail categoryDetail = categoryDetailService.queryCategoryDetailById(p.getCategoryDetailId());
                String CateName = categoryDetail.getName();
                session.setAttribute("ListName", CateName);
            }
            session.setAttribute("Book", products);
            resp.sendRedirect("/estore/listJsp");
        }
        resp.sendRedirect("/estore/listJsp");
    }
}
