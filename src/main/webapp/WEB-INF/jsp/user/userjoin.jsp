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
    
    <title>加入信息</title>
    
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
  	<table border="1">
  <thead>
    <tr>
    	 <th>项目名称</th>
    	 <th>加入编号ID</th>
     	 <th>加入姓名</th>
     	 <th>加入卡号</th>
     	 <th>帐户余额</th>
     	 <th>会员状态</th>
     	  <th>加入状态</th>
     	 <th>交易状态</th>
     	 <th>加入时间</th>
     	 <th>操作金额</th>
     	 <th>互助状态</th>
     	 <th>操作</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="joinlist" items="${requestScope.lists}">
  	  <tr>
	      <td>
		      <c:if test="${joinlist.joinMutype=='02'}">青年版</c:if>
		      <c:if test="${joinlist.joinMutype=='03'}">老年版</c:if>
	      </td>
	      <td>${joinlist.agentId }</td>
	      <td>${joinlist.joinName }</td>
	      <td>${joinlist.joinCard }</td>
	      <td>${joinlist.acBal }</td>
	      <td>
	      	   <c:if test="${joinlist.joinVipzg=='01'}">观察期</c:if>
	      	   <c:if test="${joinlist.joinVipzg=='02'}">会员</c:if>
	      <%-- 	 <c:if test=" ${joinlist.joinVipzg=='01'}">观察期</c:if>
	      	<c:if test=" ${joinlist.joinVipzg=='02'}">会员</c:if>  --%>
	     </td>
	      <td>
	    	<c:if test="${joinlist.joinStatus=='0'}">待付款,未加入</c:if>
	       <c:if test="${joinlist.joinStatus=='1'}">已付款,已加入</c:if> 
	      </td>
	      <td>
	      	<c:if test="${empty joinlist.accountStatus }">没有帐户</c:if>
	      	<c:if test="${joinlist.accountStatus=='1' }">有账户</c:if>
	      </td>
	      <td>${joinlist.joinTime }</td>
	      <td>
	      	<c:if test="${joinlist.acBal < '100'}">失效</c:if>
	      	<c:if test="${joinlist.acBal < '150'}">警示</c:if>
	      	<c:if test="${joinlist.acBal >= '200'}">帐号正常</c:if>
	      </td>	
	        <td>
	          <c:if test="${joinlist.custStatus=='1' }">互助申请成功</c:if>
	          <c:if test="${joinlist.custStatus=='0' }">互助申请审核中</c:if>
	        </td>
	      
	      <td>
	      <c:if test="${joinlist.joinStatus=='0'}">
	       <a href="<%=path%>/index/sendpays.do?joinId=${joinlist.joinId}">未加入充值</a>
	      </c:if>
	       <c:if test="${joinlist.joinStatus=='1'}">
	        <a href="<%=path%>/index/wxpay.do?agentId=${joinlist.agentId}">余额充值</a>
	       </c:if> 
	      <c:if test="${joinlist.joinVipzg=='02' && empty joinlist.custStatus}">
	      	 <a href="<%=path%>/cust/custAdd.do?agentId=${joinlist.agentId}">申请互助</a>
	      </c:if> 
	      
	      </td>
    		</tr>
    
  </c:forEach>
  </tbody>
</table>
  </body>
</html>
