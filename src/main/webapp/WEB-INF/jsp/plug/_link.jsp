<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="favicon.ico" >
<link rel="Shortcut Icon" href="favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=path %>/ui-util/lib/html5.js"></script>
<script type="text/javascript" src="<%=path %>/ui-util/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path %>/ui-util/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/ui-util/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/ui-util/static/h-ui.admin/css/H-ui.login.css" />

<link rel="stylesheet" type="text/css" href="<%=path %>/ui-util/lib/Hui-iconfont/1.0.8/iconfont.css" />

<link rel="stylesheet" type="text/css" href="<%=path %>/ui-util/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>/ui-util/static/h-ui.admin/css/style.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/ui-util/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/ui-util/lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="<%=path %>/ui-util/lib/layer/2.4/layer.js"></script> 

<script type="text/javascript" src="<%=path %>/ui-util/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=path %>/ui-util/static/h-ui.admin/js/H-ui.admin.page.js"></script> 
<script type="text/javascript" src="<%=path %>/js/utils/core-1.0.js"></script> 
<script type="text/javascript" src="<%=path %>/js/utils/layUtil.js"></script>
<script type="text/javascript" src="<%=path %>/ui-util/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path %>/ui-util/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path %>/ui-util/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=path %>/ui-util/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="<%=path %>/ui-util/lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=path %>/ui-util/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>

<style>
 .left a,.right a{ text-decoration:none; }
.left{
	float: left;
	display:block;
	width:20%;
}
.right{
	float:right;
	display:block;
	width:60%;
	text-align: right;
	margin-left:1%;
}
.pagebutton{
	border: 1px solid #ccc;
	cursor: pointer;
	display: inline-block;
	margin-left: 2px;
	text-align: center;
	text-decoration: none;
	color: #666;
	height: 26px;
	line-height: 26px;
	text-decoration: none;
	margin: 0 0 6px 6px;
	padding: 0 10px;
	font-size: 14px;
}
.pagebutton:hover{
    background: #5a98de;
	color: #fff;
}
.current{
 	background: #5a98de;
	color: #fff;
 }
.table-hover tbody tr:hover td,.table-hover tbody tr:hover th{background-color: #dff0d8}
</style>
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