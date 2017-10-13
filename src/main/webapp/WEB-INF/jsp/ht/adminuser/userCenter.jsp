<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<form action="<%=path %>/user/updatePwd.do" method="post" class="form form-horizontal" id="userpwds">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <label class="form-label col-xs-3">${sessionScope.userId }</label>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>原登陆密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" autocomplete="off" placeholder="原密码" name="userPwd" id="password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新登陆密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					            	<input type="password" class="input-text" autocomplete="off" placeholder="新密码" name="pwd" id="pwd1">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					            	<input type="password" class="input-text" autocomplete="off" placeholder="重复密码" name="pwd_" id="pwd2">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					  <input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;修改&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>
<%--  <div class="tabCon">
			    	<div style=" background-color: #f1f1f1; padding: 15px;">
						<form action="<%=path %>/user/updatePwd.do" method="post" id="userpwds" class="form form-horizontal responsive">
							<div class="row cl">
					        	<label class="form-label col-xs-3">账户：</label>
					          	<div class="formControls col-xs-8">
					            	<label class="form-label col-xs-3">${sessionScope.userId }</label>
					         	</div>
					        </div>
							<div class="row cl">
					        	<label class="form-label col-xs-3">原登陆密码：</label>
					          	<div class="formControls col-xs-8">
					            	<input type="password" class="input-text" autocomplete="off" placeholder="原密码" name="userPwd" id="password">
					         	</div>
					        </div>
					    	<div class="row cl">
					        	<label class="form-label col-xs-3">新登陆密码：</label>
					           	<div class="formControls col-xs-8">
					            	<input type="password" class="input-text" autocomplete="off" placeholder="新密码" name="pwd" id="pwd1">
					         	</div>
					        </div>
					        <div class="row cl">
					        	<label class="form-label col-xs-3">确认密码：</label>
					          	<div class="formControls col-xs-8">
					            	<input type="password" class="input-text" autocomplete="off" placeholder="重复密码" name="pwd_" id="pwd2">
					          	</div>
					        </div>
					       	<div class="row cl">
					            <div class="col-xs-8 col-xs-offset-3">
					            	<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;修改&nbsp;&nbsp;">
					        	</div>
					    	</div>
						</form>
					</div>
			    </div> 
 --%>
  </body>
  <script type="text/javascript">
	
	$("#userpwds").submit(function(){
		var pwd1 = $("#pwd1").val();
		var pwd2 = $("#pwd2").val();
		if(pwd1.length<6||pwd1!=pwd2){
			alert("数据有误，请检查后重试...");
			return false;
		}
		$.ajax({
			type:"post",
			url:"<%=path %>/user/updatePwd.do",
			data:$("#userpwds").serialize(),
			dataType:"JSON",
			success:function(data){
				if(data.code==200){
					alert("修改密码成功,重新登陆");
					$(':input[type="password"]','#userpwd').val('');
					window.parent.location.href='<%=path%>/user/userLogin.do';
					//window.parent.location.reload(); //刷新父级页面
					//parent.layer.close(index);//关闭当前页面
					//layer.close(index); //关闭弹出框
				
				}else if(data.code==202){
					alert("修改失败，密码验证错误...");
				}else if(data.code==205){
					alert("系统异常");
				}else {
					alert("未知异常");
				}
			}
		});
		return false;
	});
	
	 function loginout(){
     $.ajax({
        url:'<%=path%>/user/loginOut.do',
         dataType:'JSON',
	 	 type:'post',
        success:function(data){
          if(data.msg=="success"){
            location.href = '<%=path%>/user/userLogin.do';
          }
        },
     }); 
   }
	
</script>
</html>
