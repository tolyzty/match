<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/login.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <style>
        .login_bg{
            background: #ffffff;
        }
        .login_btn{
            width: 80%;
            margin: 10%;
        }
        .other_login{
            top: 70%;
        }
        .other_choose{
            top: 80%;
        }
    </style>
  </head>
  
  <body>
    <div class="login_bg">
    <div id="logo">
        <img src="images/logo.png" alt=""/>
    </div>
    <a class="login_btn" href="<%=path%>/user/login.do">登&nbsp;&nbsp;录</a>
    <a class="login_btn" href="<%=path%>/user/register.do">新&nbsp;&nbsp;手&nbsp;&nbsp;注&nbsp;&nbsp;册</a>
    <div class="other_login">
        <div class="other"></div>
        <span>其他方式登录</span>
        <div class="other"></div>
    </div>
    <div class="other_choose">
        <a href="">
            <img src="images/qq.png" alt=""/>
        </a>
        <a href="">
            <img src="images/wx.png" alt=""/>
        </a>
        <a href="">
            <img src="images/wb.png" alt=""/>
        </a>
    </div>
</div>
  </body>
</html>
