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
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目ID：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="${requestScope.muMap.mutualId}" id="mutualId" name="mutualId">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目名称：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="${requestScope.muMap.mutualName}" id="mutualName" name="mutualName">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>总加入金额：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${requestScope.muMap.mutualAmt }" id="mutualAmt" name="mutualAmt" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>加入预存金额:</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text"  value="${requestScope.muMap.mutualPreamt }" id="mutualPreamt" name="mutualPreamt" />
				</div>
			</div> 
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>总加入人数:</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualNumber }" id="mutualNumber" name="mutualNumber" />
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>互助事件数:</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${requestScope.muMap.mutualEvent}" id="mutualEvent" name="mutualEvent" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目类型:</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualType}" id="mutualType" name="mutualType" />
				</div>
			</div>
			<%-- 	<div class="row cl">
				<label class="form-label col-xs-2 col-sm-3"><span class="c-red">*</span>用户状态：</label>
				<div class="formControls col-xs-6 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="userStatus" type="radio" value="0" <c:if test="${requestScope.reMap.userZt == 0 }">checked</c:if> />
						<label for="sex-2">正常</label>
					</div>
					<div class="radio-box">
						<input name="userStatus" type="radio" value="1" <c:if test="${requestScope.reMap.userZt == 1 }">checked</c:if> />
						<label for="sex-1">禁用</label>
					</div>
					
				</div>
			</div> --%>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>单次最高金额：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualMumamt }" id="mutualMumamt" name="mutualMumamt" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目观察天数：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualUnder }" id="mutualUnder" name="mutualUnder" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目服务费：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualCharge }" id="mutualCharge" name="mutualCharge" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目内容名称：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualContent }" id="mutualContent" name="mutualContent" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目余额要求：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualOfferamt }" id="mutualOfferamt" name="mutualOfferamt" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目年龄：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualAge }" id="mutualAge" name="mutualAge" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目会员资格说明：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualVipdesc }" id="mutualVipdesc" name="mutualVipdesc" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目会员资格：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualVipzg }" id="mutualVipzg" name="mutualVipzg" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目发布时间：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualTime }" id="mutualTime" name="mutualTime" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目状态：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="${requestScope.muMap.mutualZt }" id="mutualZt" name="mutualZt" />
				</div>
			</div>
		</form>
		<br>
		<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;保存&nbsp;&nbsp;"  id="submt"/>
				</div>
			</div>
	</article>

  </body>
<script type="text/javascript">


$(function(){
	var msg = "修改项目成功";
	var msger ="修改项目失败,请重试";
	var url = '<%=path %>/ht/mutual/updateMutual.do';
	var inputskin = $('.skin-minimal input');icheck(inputskin);
	 var myform = $("#user-form");
	 $("#submt").click(function(){
	 	var data = myform.serialize();
        editajax(url,data,msg,msger,1,2);
	 });
});
</script> 
</html>