<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>briup电子商务-首页</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/icons.css" />
    <link rel="stylesheet" href="css/table.css" />
    <script type="text/javascript">
        function search() {
            var searchVal = document.getElementById("search").value;
            window.location = "/Book/searchProServlet?searchVal=" + searchVal;
        }
    </script>
</head>
<body>
<!--顶部-->
<div class="top">
    <div class="top_center">
       <%-- <ul class="top_lr">
            <li><a href="login.html" style="color: red;">亲,请登入</a></li>
            <li><a href="register.html">免费注册</a></li>
        </ul>--%>
        <ul class="top_bars">
            <li><a href="/estore/skipToLogin" style="color: red;">亲,请登入</a></li>
            <li><a href="/estore/skipToRegister">免费注册</a></li>
            <li><a href="#">关注杰普<span class="jt_down"></span> </a>|</li>
            <li><a href="#">网站导航<span class="jt_down"></span> </a></li>
        </ul>
    </div>
</div>
<!--头部-->
<div class="header3">
    <a href="index.jsp"><img src="images/logo.png"> </a>
    <div class="h3_center">
        <div class="search_box">
            <input type="text" id="search" value="" /> <span onclick="search()" style="cursor: pointer;">搜索</span>
        </div>
        <p>
            <c:forEach items="${list }" var="list">
                <a
                        href="/Book/booklistServlet?CateName=${list.name}&CateId=${list.id}">${list.name}</a>|
            </c:forEach>
        </p>
    </div>
</div>
<!--头部导航-->
<div class="nav_top">
    <div class="nav_top_center">
        <div>全部图书分类</div>
        <ul>
            <c:forEach items="${list }" var="list">
                <li><a
                        href="/estore/bookList?CateName=${list.name}&CateId=${list.id}">${list.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="container3">
    <div class="c3_b1">
        <div class="c3_b1_left">
            <form action="/estore/bookList" method="get">
                <dl>
                    <c:forEach items="${list}" var="list">
                        <dd>
                            <h1>
                                <a
                                        href="/estore/bookList?CateName=${list.name}&CateId=${list.id}&isBigCate=true">${list.name}</a>
                            </h1>
                            <p>
                                <c:forEach items="${list.getCategoryDetailList()}" var="detail">
                                    <a
                                            href="/estore/bookList?cate_detailName=${detail.name}&cate_detailId=${detail.id}&catId=${detail.categoryId}&isBigCate=false">${detail.name}</a>
                                </c:forEach>
                            </p>
                        </dd>
                    </c:forEach>
                </dl>
            </form>
        </div>
        <div class="c3_b1_center">
            <div>
                <a href="/estore/viewBookServlet"><img src="images/ad1.png"> </a>
            </div>
            <div class="c3_b1_c_bottom">
                <form action="/estore/viewBookServlet" method="get">
                    <ul>
                        <c:forEach items="${bestPro}" var="bestPro">
                            <li><a href="/estore/viewBookServlet?ProId=${bestPro.getId()}"><img src="${bestPro.images}" />
                            </a> <a href="/estore/viewBookServlet?ProId=${bestPro.getId()}">${bestPro.name}</a></li>
                        </c:forEach>
                    </ul>
                </form>
            </div>
        </div>
        <div class="c3_b1_right">
            <h1>
                杰普快报<a href="#">更多</a>
            </h1>
            <ul>
                <li><a href="#">${report.get(0).getTitle()}</a></li>
                <li><a href="#">${report.get(1).getTitle()}</a></li>
                <li><a href="#">${report.get(2).getTitle()}</a></li>
                <li><a href="#">${report.get(3).getTitle()}</a></li>
                <li><a href="#">${report.get(4).getTitle()}</a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
                <li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
            </ul>
        </div>
        <div style="clear:both"></div>
    </div>
</div>
<div class="c3_b2">
    <ul>
        <c:forEach items="${product}" var="product">
            <li>
                <div class="c3_b2_txt">
                    <h1>${product.name}</h1>
                        <%-- <p>${mq.cate_detail.name}</p>--%>
                    <h2>畅销书籍</h2>
                    <h2>${product.explains}</h2>
                    <p>
                        <a href="/estore/viewBookServlet">更多精彩，点击进入</a>
                    </p>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="c20"></div>
<!--脚部-->
<div class="footer3">
    <div class="f3_top">
        <div class="f3_center">
            <div class="ts1">品目繁多 愉悦购物</div>
            <div class="ts2">正品保障 天天低价</div>
            <div class="ts3">极速物流 特色定制</div>
            <div class="ts4">优质服务 以客为尊</div>
        </div>
    </div>
    <div class="f3_middle">
        <ul class="f3_center">
            <li class="f3_mi_li01">
                <h1>购物指南</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>配送方式</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>支付方式</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>售后服务</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>服务保障</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li06">
                <h1>客服中心</h1> <img src="images/qrcode_jprj.jpg" width="80px"
                                   height="80px">
                <p>抢红包、疑问咨询、优惠活动</p>
            </li>
        </ul>
    </div>
    <div class="f3_bottom">
        <p class="f3_links">
            <a href="#">关于我们</a>| <a href="#">联系我们</a>| <a href="#">友情链接</a>| <a
                href="#">供货商入驻</a>
        </p>
        <p>沪ICP备14033591号-8 杰普briup.com版权所有 杰普软件科技有限公司</p>
        <img src="images/police.png">
    </div>
</div>
</body>
</html>
