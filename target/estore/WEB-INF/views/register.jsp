<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>briup电子商务-注册页</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
	function translate(){
		
	}
</script>
</head>
<body>
	<div class="container2">
    	<div class="header2">
        	<div>
            	<a href="index.jsp"><img class="logo" src="images/logon_register.png"></a>
            </div>
            <div>
            	<ul class="tabs">
                	<li class="phone current"><a href="#">用手机登陆</a></li>
                </ul>
            </div>
            <div class="tlg">
            	返回登入 <a href="/estore/skipToLogin">登陆</a>
            </div>
        </div>
        <div class="content2" >
          <form action="/estore/RegistServlet" method="post">
			<ul class="reg_box" >
            	<li>
                	<span><b>*</b>用户名：</span>
                    <input  type="text" name="name"/>
                </li>
               <li>
                	<span><b>*</b>密码：</span>
                    <input type="password" name="password"/>
                </li>
                <li>
                	<span>邮编：</span>
                    <input type="text" name="zip"/>
                </li>
                <li>
                	<span>地址：</span>
                    <input type="text" name="address"/>
                </li>
                <li>
                	<span>电话：</span>
                    <input type="text" name="telephone"/>
                </li>
                <li>
                	<span>电子邮箱：</span>
                    <input type="text" name="email"/>
                </li>
            </ul>
			<p>
            	<input type="checkbox" checked/>
                我已阅读并同意
                <a href="#">用户注册协议</a>
            </p>
          <input class="btn_submit" type="submit"  value="立即注册" />
         </form>
        </div>
   	</div>
</body>
</html>
