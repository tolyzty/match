<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="favicon.ico" >
<link rel="Shortcut Icon" href="favicon.ico" />
	<link rel="stylesheet" href="<%=path %>/css/new_file.css" />
	<link rel="stylesheet" href="<%=path %>/layer/mobile/need/layer.css" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=path %>/ui-util/lib/html5.js"></script>
<script type="text/javascript" src="<%=path %>/ui-util/lib/respond.min.js"></script>
<![endif]-->
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/ui-util/lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="<%=path %>/js/utils/core-1.0.js"></script> 
<script type="text/javascript" src="<%=path %>/js/new_file.js" ></script>
<script type="text/javascript" src="<%=path %>/layer/mobile/layer.js" ></script>
  <script type="text/javascript">
<%--    function loginout(){
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
   --%>
  </script>