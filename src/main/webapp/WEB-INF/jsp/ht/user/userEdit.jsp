<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  <jsp:include page="/WEB-INF/jsp/plug/_link.jsp" />
</head>
 <body>
   <article class="cl pd-20">
		<form action="" method="post" class="form form-horizontal" id="user-form">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>ID：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="${requestScope.reMap.userId}" id="userId" name="userId">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="${requestScope.reMap.consumerNo}" id="cousumerNo" name="cousumerNo">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${requestScope.reMap.userName }" id="userName" name="userName" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>绑定手机:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly"  value="${requestScope.reMap.userPhone }" id="userPhone" name="userPhone" />
				</div>
			</div> 
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>邮箱地址:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.userEmail }" id="userEmail" name="userEmail" />
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户昵称:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${requestScope.reMap.userNickname}" id="userNickname" name="userNickname" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>真实姓名:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.userRealname}" id="userRealname" name="userRealname" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户状态：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="userStatus" type="radio" value="0" <c:if test="${requestScope.reMap.userZt == 0 }">checked</c:if> />
						<label for="sex-2">正常</label>
					</div>
					<div class="radio-box">
						<input name="userStatus" type="radio" value="1" <c:if test="${requestScope.reMap.userZt == 1 }">checked</c:if> />
						<label for="sex-1">禁用</label>
					</div>
					
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>证件类型：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.credType }" id="credType" name="credType" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>身份证号码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.userCredNumber }" id="userCredNumber" name="userCredNumber" />
				</div>
			</div>
				 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>地址：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.userAddress }" id="userAddress" name="userAddress" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>注册时间:</label>
				<div class="formControls col-xs-8 col-sm-9">
				     <input value="${requestScope.reMap.createTime}" id="createTime"  name="createTime"  class="input-text Wdate" />
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>商户编号ID：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.agentId }" id="agentId" name="agentId" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>最后登录时间:</label>
				<div class="formControls col-xs-8 col-sm-9">
				     <input value="${requestScope.reMap.lastLoginTime}" id="lastLoginTime"  name="lastLoginTime"  class="input-text Wdate"  />
				</div>
			</div> 
		
		<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>最后登录Ip:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.lastLoginIp} " id="lastLoginIp" name="lastLoginIp" />
				</div>
			</div>  
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>是否代理商：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="islock" type="radio" value="0" <c:if test="${requestScope.reMap.islock == 0 }">checked</c:if> />
						<label for="sex-2">否</label>
					</div>
					<div class="radio-box">
						<input name="islock" type="radio" value="1" <c:if test="${requestScope.reMap.islock == 1 }">checked</c:if> />
						<label for="sex-1">是</label>
					</div>
					
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>代理商ID:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.islockId}" readonly="readonly" id="islockId" name="islockId" />
				</div>
			</div>
			<div class="row cl" >
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>设置奖励金额:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.awardAmt}" id="awardAmt" name="awardAmt" readonly="readonly" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>推荐人数:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.islockNumber}" id="islockNumber" name="islockNumber" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>推荐成功金额:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.reMap.islockAmt}" id="islockAmt" name="islockAmt" />
				</div>
			</div>
		</form>
		<br>
		<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"  id="submt"/>
				</div>
			</div>
			
	</article>

  </body>
<script type="text/javascript">
$(function(){
	 var islocks = $("input[name='islock']:checked").val();
	 //alert(islocks);
	 if (islocks==1) {
		 $("#awardAmt").removeAttr("readonly");	
	  }
	 var msg = "修改用户成功";
	 var msger ="修改用户失败,请重试";
	 var url = '<%=path %>/ht/user/updateUser.do';
	 var inputskin = $('.skin-minimal input');icheck(inputskin);
	 var myform = $("#user-form");
	 $("#submt").click(function(){
	 	 var sjislock = $("#islockId").val();
		if (sjislock==0) {
			   sjislock="";
			   var islock = $("input[name='islock']:checked").val();
			   var cousumerNo = $("#cousumerNo").val();
				if (islock==1) {
					$("#islockId").val(sjislock+cousumerNo);
				}else{
					$("#islockId").val("0");
				} 
		 }
	 	var data = myform.serialize();
        editajax(url,data,msg,msger,1,2);
	 });
});
</script> 
</html>