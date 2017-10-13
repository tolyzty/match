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
	<h4>欢迎来到申请互助页面</h4>
	<article class="cl pd-20">
		<form action="<%=path%>/cust/addcust.do" method="post" name="myform" class="form form-horizontal">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" name="agentId" id="agentId" value="${agentId }"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>性别：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="custGender" type="radio" value="0" checked="checked"  />
						<label for="sex-2">男</label>
					</div>
					<div class="radio-box">
						<input name="custGender" type="radio" value="1"  />
						<label for="sex-1">女</label>
					</div>	
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>现住地址：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  value="" id="custAddress" name="custAddress">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>互助金额：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  value="" id="custAmt" name="custAmt">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"  id="submt"/>
				</div>
			</div>
	</form>
	</article>
  </body>
</html>
