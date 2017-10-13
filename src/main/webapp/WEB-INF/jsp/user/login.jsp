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

  </head>
  
  <body>
     <div id="login"></div>
    <div class="login_bg">
        <div id="logo">
            <img src="images/logo.png" alt=""/>
        </div>
        <form action="<%=path%>/user/logins.do" method="post" name="myform" id="myform">
            <div class="userName">
                <lable>用户手机号：</lable>
                <input type="text" name="userPhone" placeholder="请输入手机号" required/>
            </div>
            <div class="passWord">
                <lable>密&nbsp;&nbsp;&nbsp;码：</lable>
                <input type="password" name="userPassword" placeholder="请输入密码"  required/>
            </div>
            <div class="code">
            	<label>验证码：</label>
            	<input type="text" name="code" palaceholder="请输入验证码" />
            </div>
            <div class="choose_box">
                <div>
                    <input type="checkbox" checked="checked" />
                    <lable>记住密码</lable>
                </div>
                <a href="newPassword.html">忘记密码</a>
            </div>
            <button class="login_btn" type="submit">登&nbsp;&nbsp;录</button>
        </form>
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
