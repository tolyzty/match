<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<jsp:include page="/WEB-INF/jsp/plug/_qtmenu.jsp" />
     <h1>欢迎来到会员中,欢迎您：  ${userPhone} : ${rsmap.islockId}  ：${rsmap.islock}</h1>
     <c:if test="${rsmap.islock =='1'}">
     <textarea rows="3">推荐人链接：
     http://localhost:8080/match/user/register.do?islockId=${rsmap.islockId}</textarea>
     </c:if>
     <a href="<%=path%>/user/userJoin.do">加入列表</a>
  </body>
</html>
