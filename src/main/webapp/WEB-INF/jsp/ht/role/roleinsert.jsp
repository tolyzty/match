<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<form action="" method="post" id="insertform" name="myform" class="form form-horizontal">
			<!-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否是子节点：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input class="isLeaf" name="menuIsLeaf" type="radio" value="0" checked/>
						<label for="sex-1">否</label>
					</div>
					<div class="radio-box">
						<input class="isLeaf" name="menuIsLeaf" type="radio" value="1" />
						<label for="sex-2">是</label>
					</div>
				</div>
			</div> -->
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="" id="roleName" name="roleName" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色描述:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="" id="roleDesc" name="roleDesc" />
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色状态:</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="roleStatus" type="radio" value="1" checked/>
						<label for="sex-1">正常</label>
					</div>
					<div class="radio-box">
						<input name="roleStatus" type="radio" value="0" />
						<label for="sex-2">禁用</label>
					</div>
				</div>
			</div>
			<!-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单类型:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
						<select class="select" size="1" name="menuType">
							<option value="1" >
								按钮
							</option>
							<option value="2" >
								其他
							</option>
							<option value="0" >
								菜单
							</option>
						</select>
					</span>
				</div>
			</div> -->
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属系统:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
						<select class="select" size="1" name="sysId" id="sysId">
							<option value="0002">商户系统</option>
							<option value="0003">交易系统</option>
							<option value="0001" selected="selected">运营系统</option>
						</select>
					</span>
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>所属商户ID:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="" id="merId" name="merId" />
				</div>
			</div> 
			<%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">补充权限：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name="beizhu" cols="" rows="" class="textarea"  placeholder="扩展权限控制，每个路径使用','分割" onKeyUp="textarealength(this,100)">${requestScope.menu.authUrl }</textarea>
					<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
				</div>
			</div> --%>
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
     var msg = "新增角色成功";
     var msger = "新增角色失败";
     var url = "<%=path %>/ht/role/role_add.do";
     var inputskin = $('.skin-minimal input');icheck(inputskin);
     var validateform = $("#insertform");
     validateform.validate({
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
