<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<jsp:include page="/WEB-INF/jsp/plug/_qtlink.jsp" />
  </head>
  
  <body>
  <jsp:include page="/WEB-INF/jsp/plug/_qtmenu.jsp" />
	<div>项目名称：${params}</div>
  	
  	<div>支付方式:
  		<div>微信支付：
  		  <form action="<%=path %>/pay/pay/payCallBack.do" method="post" name="myform">
			支付金额：<input type="text" name="payAmt" id="payAmt" value="${params.payAmt}" />	
			交易状态：<input type="text" name="orderStatus" id="orderStatus" value="${params.orderStatus}" />						
			订单号：<input type="text" name="orderNo" id="orderNo" value="${params.orderNo}" />						
			用户编号：<input type="text" name="agentId" id="agentId" value="${params.agentId}" />
								
			<input type="submit"  id="but" value="付款成功" />
		</form>
  		</div>
  	</div>
  	
  
  </body>

</html>
