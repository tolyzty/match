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
		<form action="" method="post" class="form form-horizontal" id="role-form">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="${requestScope.role.roleId}" placeholder="" id="roleId" name="roleId">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${role.roleName }" placeholder="" id="roleName" name="roleName" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色描述：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${role.roleDesc }" placeholder="" id="roleDesc" name="roleDesc" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色状态：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="roleStatus" type="radio" value="0" <c:if test="${role.roleStatus == 0 }">checked</c:if> />
						<label for="sex-1">禁用</label>
					</div>
					<div class="radio-box">
						<input name="roleStatus" type="radio" value="1" <c:if test="${role.roleStatus == 1 }">checked</c:if> />
						<label for="sex-2">正常</label>
					</div>
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属系统：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
						<select class="select" size="1" name="sysId" id="sysId">
							<option value="0002" <c:if test="${role.sysId == '0002' }">selected="selected"</c:if> >
								商户系统
							</option>
							<option value="0003" <c:if test="${role.sysId == '0003' }">selected="selected"</c:if> >
								交易系统
							</option>
							<option value="0001" <c:if test="${role.sysId == '0001' }">selected="selected"</c:if> >
								运营系统
							</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>所属商户ID：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${role.merId }" placeholder="" id="merId" name="merId" />
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" id="submit" />
				</div>
			</div>
		</form>
		
	</article>

  </body>
<script type="text/javascript">
$(function(){
	var msg = "修改角色成功";
	var msger = "修改角色失败,请重试";
	var url = '<%=path %>/ht/role/edit/update.do';
	var inputskin = $('.skin-minimal input');icheck(inputskin);
	var validateform = $("#role-form");
	$("#role-form").validate({
		rules:{
			roleName:{
				required:true,
				minlength:2,
			},
			roleDesc:{
				required:true,
			},		
		},
		messages:{
			roleName:{
				required:"请填写信息",
				minlength:"最小字符2",
			}
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