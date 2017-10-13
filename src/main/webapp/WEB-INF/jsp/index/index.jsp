<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<body>
<a href="<%=path%>/index/mutual.do">首页</a>
<a href="<%=path%>/ht/user/userLogin.do">后台登录地址</a>
</body>
</html>
