<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>listBook.jsp</title>
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>

<c:forEach items="${listProductOrderByPrice}" var="listPrice">
					<li class="c4_b5_c_box">
						<!--图片-->
						<div class="c4_b5_c_box_pic">
							<div class="c4b5_pic_view">
								<a
									href="/book/viewBookServlet?bookName=${listPrice.getName()}"><img
									src="images/list_p1.png"> </a>
							</div>
						</div> <!--价钱-->
						<div class="c4_b5_c_box_txt">
							<h1>
								销量：${listPrice.getSale_num()}
							</h1>
							<h1>
								￥ ${listPrice.getPrice()}</h1>
							<h2>
								<a
									href="/book/viewBookServlet?bookName=${listPrice.getName()}">${listPrice.getName()}</a>
							</h2>
						</div> <!--购买等操作-->
						<div class="c4b5_el">
							<div class="c4b5_el_x">
								<span class="wjx01"></span>
							</div>
						</div>
						<ul class="c4b5_option">
							<li class="shopcar_box"><span class="shopcar01"></span><a
								href="javascript:void(0)" onclick="addShopCart('${listPrice.getName()}')">加入购物车
							</a>
							</li>
						</ul></li>
</c:forEach>

  </body>
</html>
