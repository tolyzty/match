<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div>
	<a href="<%=path %>/index/index.do" >首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="<%=path %>/index/mutual.do" >项目列表</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=path %>/index/pays.do" >充值</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=path %>/user/content.do" >个人中心</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=path %>/user/login.do" >登录</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=path %>/user/register.do">注册</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=path %>/ht/user/userCenter.do">后台会员中心</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=path %>/user/logout.do">清空session</a>&nbsp;&nbsp;&nbsp;
	<a href="" >后台登录</a>
</div>