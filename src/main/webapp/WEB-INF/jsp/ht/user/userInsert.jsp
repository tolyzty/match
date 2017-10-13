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
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登陆用户名:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="" id="userName" name="userName" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登陆密码:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="" id="pwd" name="pwd" />
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号码:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="" id="userPhone" name="userPhone" />
				</div>
			</div>
		</form>
		<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type=button value="&nbsp;&nbsp;提交&nbsp;&nbsp;" id="submt" />
				</div>
			</div>
	</article>
  
  
  </body>
<script type="text/javascript">

 $(function(){ 
     var msg = "新增用户成功";
     var msger = "新增用户失败";
     var url = "<%=path %>/user/insertUser.do";
     var inputskin = $('.skin-minimal input');icheck(inputskin);
     var myform = $("#insertform");
     $("#submt").click(function(){
     	 var data = myform.serialize();
	     addajax(url,data,msg,msger,1,2); 
     });
});



</script> 
</html>
