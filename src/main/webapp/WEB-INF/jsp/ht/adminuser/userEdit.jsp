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
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="${requestScope.user.id}" placeholder="" id="id" name="id">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.userId }" placeholder="" id="userId" name="userId" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户昵称:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.userName}" id="userName" name="userName" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登陆密码:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" value="" id="userPwd" name="userPwd" />
				</div>
			</div>
				<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户状态：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="userStatus" type="radio" value="1" <c:if test="${user.userStatus == 1 }">checked</c:if> />
						<label for="sex-2">正常</label>
					</div>
					<div class="radio-box">
						<input name="userStatus" type="radio" value="0" <c:if test="${user.userStatus == 0 }">checked</c:if> />
						<label for="sex-1">禁用</label>
					</div>
					
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属系统：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
						<select class="select" size="1" name="sysId" id="sysId">
							<option value="0002" <c:if test="${user.sysId == '0002' }">selected="selected"</c:if> >
								商户系统
							</option>
							<option value="0003" <c:if test="${user.sysId == '0003' }">selected="selected"</c:if> >
								交易系统
							</option>
							<option value="0001" <c:if test="${user.sysId == '0001' }">selected="selected"</c:if> >
								运营系统
							</option>
						</select>
					</span>
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>登陆次数:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.lunm}" id="lunm" name="lunm" value="0" />
				</div>
			</div> 
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>最后登录时间:</label>
				<div class="formControls col-xs-8 col-sm-9">
<%-- 					<input type="text" class="input-text" value="${lastLoginTime }" id="lastLoginTime" name="lastLoginTime" />
 --%>				     <input value="${user.lastLoginTime}" id="lastLoginTime"  name="lastLoginTime"  class="input-text Wdate" onfocus="WdatePicker({readOnly:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</div>
			</div> 
		
		<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>最后登录Ip:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.lastLoginIp} " id="lastLoginIp" name="lastLoginIp" />
				</div>
			</div>  
		<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>绑定手机:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.phone }" id="phone" name="phone" />
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>所属商户ID：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.merId }" placeholder="" id="merId" name="merId" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>邮箱地址:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.email }" id="email" name="email" />
				</div>
			</div> 
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>IP白名单:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.ipWhite }" id="ipWhite" name="ipWhite" />
				</div>
			</div> 
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>地址白名单:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.addressWhite }" id="addressWhite" name="addressWhite" />
				</div>
			</div> 
			

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>

  </body>
<script type="text/javascript">
$(function(){
	var msg = "修改用户成功";
	var msger = "修改用户失败,请重试";
	var url = '<%=path %>/ht/adminuser/edit/update.do';
	var inputskin = $('.skin-minimal input');icheck(inputskin);
	var validateform = $("#user-form");
	$("#user-form").validate({
		rules:{
			userId:{
				required:true,
				minlength:2,
			},
			userPwd:{
				required:true,
				minlength:6,
				
			},		
		},
		messages:{
			userId:{
				required:"请填写信息",
				minlength:"最小2个字符",
			},
			userPwd:{
				required:"请填写密码",
				minlength:"最小6个字符",

			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
		  var data = validateform.serialize();
		  editajax(url,data,msg,msger,1,2);
		}
	});
	

});
</script> 
</html>