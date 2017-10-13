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
    
    <title>欢迎登录后台管理系统</title>
    
	<jsp:include page="/WEB-INF/jsp/plug/_link.jsp" />
	
<%-- 	<script src="<%=path %>/ui-util/static/login/js/jquery.js" language="JavaScript"></script>
	<script src="<%=path %>/ui-util/static/login/js/cloud.js" type="text/javascript"></script> --%>
  </head>
 

 <!--验证码js-->
        <script src="<%=path %>/ui-util/static/login/js/xiton.js"></script>
		<script>
		$(document).ready(function() {
 		 //验证码
  		createCode();
 		var codeUp = code.toLowerCase();
 		$('#codeUp').val(codeUp);
		});
		
		</script>
        <!--验证码js-->
  <body>
	<div class="header">
	    <div style="text-align: center;">
	         <h2 style="color:#c4c4c4;line-height:20px">雅付后台管理系统V1.0</h2>
	    </div>
	</div>
 
<div class="loginWraper">
    <div id="loginform" class="loginBox">
       <div class="row cl">
            	<div class="formControls col-xs-offset-4" style="height:25px;">
            	   <c:if test="${error!='' &&error!=null }">
               		<span style="color:red;">${error }</span>
               </c:if>
                <span id="showname" style="color:red;"></span>
            	</div>
            </div>
        <form class="form form-horizontal" action="<%=path %>/user/Login.do" method="post" name="myforml">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
<!--                     <input id="token" name="token" type="hidden" placeholder="" class="input-text size-L" value="2794f6a20ee0685f4006210f40799acd">
 -->                    <input id="userId" name="userId" type="text" placeholder="账户"  class="input-text size-L" >
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="userPwd" name="userPwd" type="password" placeholder="密码"  class="input-text size-L" >
                </div>
            </div>
              <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="code" name="code" type="text" placeholder="验证码" class="input-text size-L" style="width:240px;" onblur="show();" >
                	 <canvas id="myCanvas" onclick="createCode()" style="width:90px;margin-left:20px;"></canvas>
                    <input id="codeUp" name="codeUp" type="hidden" value="" /> 
				</div>
               
            </div>
  <!--   <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online">
                        <input type="checkbox" name="online" id="online" value="">
                        使我保持登录状态</label>
                </div>
            </div>  -->
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="signin_submit" type="submit" id="submitB" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>

<div class="footer">Copyright 雅付运营管理平台by @yafupay</div>
	
  </body>
</html>
<%-- <body style="background-color:#1c77ac; background-image:url(<%=path %>/ui-util/static/login/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
 --%>
 

 <%--    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">返回首页</a></li>
    <li><a href="#">关于我们</a></li>
    </ul>    
    </div>
    
      <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form action="<%=path %>/user/Login.do" method="post" name="myforml">
    <ul>	
    <li style="height:10px;">
      <c:if test="${error!='' &&error!=null }">
         <span style="color:red;">${error }</span>
 	 </c:if>
    </li>
    <li>
       <input id="token" name="token" type="hidden" placeholder="" value="2794f6a20ee0685f4006210f40799acd">
       <input id="userId" name="userId" type="text" placeholder="请输入用户名" class="loginuser" >
    </li>
    <li>
    	<input id="userPwd" name="userPwd" type="password" placeholder="请输入密码" class="loginpwd" />
	</li>
    <li><input name="" type="text" class="loginpwds"  placeholder="请输入验证码" /></li>
  	<li style=" margin-top:-64px; margin-left:240px;">
		 <canvas id="myCanvas" onclick="createCode()"></canvas>
	</li> 
    <li><input name="" type="submit" class="loginbtn" value="登录"  />
   		 <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>
    </li>
   
    </ul>
    
    </form>
    </div>
    
    </div>   
    <div class="loginbm">Copyright 2016  © 上海雅付网络科技有限公司 备案号：沪ICP备16036723号</div> 
</body>

</html>
--%>

<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<div class="header"></div>
 
<div class="loginWraper">
    <div id="loginform" class="loginBox">
       <div class="row cl">
            	<div class="formControls col-xs-offset-4" style="height:25px;">
            	   <c:if test="${error!='' &&error!=null }">
               		<span style="color:red;">${error }</span>
               </c:if>
            	</div>
              
            </div>
        <form class="form form-horizontal" action="<%=path %>/user/Login.do" method="post" name="myforml">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="token" name="token" type="hidden" placeholder="" class="input-text size-L" value="2794f6a20ee0685f4006210f40799acd">
                    <input id="userId" name="userId" type="text" placeholder="账户"  class="input-text size-L" required>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="userPwd" name="userPwd" type="password" placeholder="密码" maxlength="16" minlength="6" class="input-text size-L" required>
                </div>
            </div>
         
    <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online">
                        <input type="checkbox" name="online" id="online" value="">
                        使我保持登录状态</label>
                </div>
            </div> 
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="signin_submit" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright 雅付运营管理平台by @yafupay</div>
     
  </body>
</html>
 --%>