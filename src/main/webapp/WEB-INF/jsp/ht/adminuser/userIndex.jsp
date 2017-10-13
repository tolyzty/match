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
    <jsp:include page="/WEB-INF/jsp/plug/_head.jsp" />
   <jsp:include page="/WEB-INF/jsp/plug/_menu.jsp" />
   <section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
<!-- 		<span class="c-gray en">&gt;</span>
		会员管理
		<span class="c-gray en">&gt;</span>
		用户管理 -->
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" id="replace" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
		<br>
           <h3>欢迎登陆</h3>
            <br>
            <p><a href="<%=path%>/wap/userIndex.do" class="btn btn-primary radius">手机访问</a></p>
   		</article>
	</div>
	
</section>
  </body>

</html>
