package com.estore.controller;

import com.estore.bean.Categories;
import com.estore.bean.Product;
import com.estore.service.CategoryService;
import com.estore.service.ProductService;
import com.estore.service.PublishService;
import com.estore.utils.EstoreException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Controller
public class ViewController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PublishService publishService;

    //书籍详情页
    @RequestMapping(value = "viewBookServlet", method = {RequestMethod.POST, RequestMethod.GET})
    public void viewBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        String ProId = req.getParameter("ProId");
        if (StringUtils.isEmpty(ProId)) {
            resp.sendRedirect("/estore/listJsp");
        }
        Product products = productService.queryProductById(Integer.parseInt(ProId));
        if (null == products) {
            resp.sendRedirect("/estore/listJsp");
            throw new EstoreException("", "没有找到对应的商品");
        }
        Categories categories = categoryService.queryCatById(products.getCategoryDetailId());
        String[] version = new String[50];
        switch (products.getVersion()) {
            case "1":
                version[1] = "Hardback";// 精装版
                break;
            case "2":
                version[2] = "Paperback";// 简装版
                break;
            case "4":
                version[4] = "Collection";// 收藏版
                break;
            case "3":
                version[1] = "Hardback";
                version[2] = "Paperback";
                break;
            case "5":
                version[1] = "Hardback";// 精装版
                version[4] = "Collection";// 收藏版
                break;
            case "6":
                version[2] = "Paperback";// 简装版
                version[4] = "Collection";// 收藏版

                break;
            case "7":
                version[1] = "Hardback";
                version[2] = "Paperback";
                version[4] = "Collection";// 收藏版
                break;

            default:
                break;
        }
        session.setAttribute("ProductId", ProId);
        session.setAttribute("version", version);
        session.setAttribute("viewPro", products);
        session.setAttribute("viewProCate", categories);
        resp.sendRedirect("/estore/viewBook");
    }

    //某类下的书籍列表
    @RequestMapping(value = "bookList", method = {RequestMethod.POST, RequestMethod.GET})
    public void bookList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        //顶部热卖书籍的查询，flag=1

        //根据书的分类的id查询
        if (req.getParameter("isBigCate").equals("true")) {
            String cateId = req.getParameter("CateId");
            List<Product> CateBook = productService.queryProductByCategoryId(Integer.parseInt(cateId));
            session.setAttribute("Book", CateBook);
            session.setAttribute("ListId", cateId);
            session.setAttribute("ListIdFlag", true);
            //获取网址上传递的书的大类名
            String cateName = req.getParameter("CateName");
            //Catename = new String(Catename.getBytes("ISO8859-1"), "UTF-8");
            session.setAttribute("ListName", cateName);
        } else {
            //根据书的详细分类查询
            String cate_detailId = req.getParameter("cate_detailId");
            String cateDetailName = req.getParameter("cate_detailName");
            //query catById
            String cateName = categoryService.queryCatById(Integer.parseInt(req.getParameter("catId"))).getName();

            // -1是因为历史遗留问题，cat表中是从1开始（为了使用自增，以前oracle没有），而product中存的却是0开始
            Set<Product> Cate_detailBook = productService.queryProductByCateDetailId(Integer.parseInt(cate_detailId) - 1);
            session.setAttribute("Book", Cate_detailBook);
            session.setAttribute("ListId", cate_detailId);
            session.setAttribute("ListIdFlag", false);

            session.setAttribute("ListName", cateName);
            session.setAttribute("ListDetailName", cateDetailName);
        }
        resp.sendRedirect("/estore/listJsp");
    }

    //书籍的排序
    @RequestMapping(value = "listServlet", method = {RequestMethod.POST, RequestMethod.GET})
    public void listServlet(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

        String id = (String) session.getAttribute("ListId");
        boolean flag = (Boolean) session.getAttribute("ListIdFlag");
        if (req.getParameter("CheckId") == null) {
            // 根据出版社查询
            if (StringUtils.isEmpty(req.getParameter("publishRank"))) {
                // 根据价格区间进行查询
                if (StringUtils.isEmpty(req.getParameter("priceRank"))) {
                    System.out.println("[ViewController.listServlet] req = {} 价格为空，出版社为空,则不做处理");
                } else {
                    //为大类（如文学类）传过来的
                    System.out.println("[ViewController.listServlet] req = {} 价格有，出版社为空");
                    String[] priceRank = req.getParameter("priceRank").split(
                            "-");
                    String min_price = priceRank[0];
                    String max_price = priceRank[1];
                    System.out.println("[ViewController.listServlet] req = {} min_price,max_price"+min_price+"-"+max_price);
                    Set<Product> allProducts = productService.queryProductByPriceRank(new BigDecimal(min_price), new BigDecimal(max_price));
                    // 得到的products是所有的，还需要筛选为对应catId的（getParameter(Book)比较获取相同的）
                    Set<Product>products = targetProductCompareAllProduct(session, allProducts);
                    session.setAttribute("Book", products);
                }
            } else {
                // 根据出版社的名字查询出版id，根据id查询书籍
                if (req.getParameter("priceRank") == null) {
                    System.out.println("[ViewController.listServlet] req = {} 价格为空，出版社有");
                    String publishName = req.getParameter("publishRank");
                    //publishName=new String(publishName.getBytes("ISO8859-1"), "utf-8");
                    System.out.println("[ViewController.listServlet] publishName = {}" + publishName);
                    //根据出版社name查询出版社id
                    int publishId = publishService.queryPublishByName(publishName).getId();
                    //根据出版社id查询product
                    Set<Product> allProducts = productService.queryProductByPublishId(publishId);
                    Set<Product>products = targetProductCompareAllProduct(session, allProducts);
                    session.setAttribute("Book", products);
                } else {
                    System.out.println("[ViewController.listServlet] req = {} 价格有，出版社有");
                    String[] priceRank = req.getParameter("priceRank")
                            .split("-");
                    String min_price = priceRank[0];
                    String max_price = priceRank[1];
                    String publishName = req.getParameter("publishRank");
                    System.out.println("[ViewController.listServlet] req = {} min_price,max_price"+min_price+"-"+max_price);
                    System.out.println("[ViewController.listServlet] req = {} publishRank"+publishName);
                    int publishId = publishService.queryPublishByName(publishName).getId();
                    //根据出版社id查询product
                    Set<Product> allProductsByPublish = productService.queryProductByPublishId(publishId);
                    //根据出版社价格范围查询product
                    Set<Product> allProductsByPriceRank = productService.queryProductByPriceRank(new BigDecimal(min_price), new BigDecimal(max_price));
                    //targetProductCompareAllProduct(session, allProductsByPublish);
                    Set<Product>products = targetProductCompareAllProduct(session, allProductsByPublish, allProductsByPriceRank);
                    session.setAttribute("Book", products);
                }
            }
        } else {
            // 判断排序
            int Checkid = Integer.parseInt(req.getParameter("CheckId"));

            if (Checkid == 1) {
                Set<Product> CatePro = productService.queryProductOrderBySale(true, false);
                session.setAttribute("Book", CatePro);
            } else if (Checkid == 2) {
                Set<Product> CatePro = productService.queryProductOrderBySale(false, true);
                session.setAttribute("Book", CatePro);
            } else if (Checkid == 0) {
                //保持默认
            } else {
                //保持默认
            }
        }
        resp.sendRedirect("/estore/listJsp");
    }

    private Set<Product> targetProductCompareAllProduct(HttpSession session, Set<Product> allProducts) {

        Set<Product> targetProducts = (Set<Product>) session.getAttribute("Book");
        Set<String> allProductsName = new HashSet<>();
        allProducts.parallelStream().forEachOrdered(allProduct -> {
                    allProductsName.add(allProduct.getName());
                }
        );
        Set<Product> products = new HashSet<>();
        targetProducts.parallelStream().forEachOrdered(product -> {
             Boolean isIntersection = allProductsName.parallelStream().anyMatch(allProduct->
                StringUtils.equals(allProduct,product.getName()));
             if (isIntersection){
                 products.add(product);
             }
        });
        return products;
    }

    private Set<Product> targetProductCompareAllProduct(HttpSession session, Set<Product> allProducts1, Set<Product> allProducts2) {
        Set<Product> targetProducts = targetProductCompareAllProduct(session,allProducts1);
        Set<String> allProductsName = new HashSet<>();
        allProducts2.parallelStream().forEachOrdered(allProduct -> {
                    allProductsName.add(allProduct.getName());
                }
        );
        Set<Product> products = new HashSet<>();
        targetProducts.parallelStream().forEachOrdered(product -> {
            Boolean isIntersection = allProductsName.parallelStream().anyMatch(allProduct->
                    StringUtils.equals(allProduct,product.getName()));
            if (isIntersection){
                products.add(product);
            }
        });
        return products;
    }
}
