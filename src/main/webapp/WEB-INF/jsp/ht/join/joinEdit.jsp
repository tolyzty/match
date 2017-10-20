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
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商户编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="${requestScope.findMap.agentId}" placeholder="" id="agentId" name="agentId">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${findMap.consumerNo }" placeholder="" id="consumerNo" name="consumerNo" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属项目ID：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${findMap.muId }" placeholder="" id="muId" name="muId" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>加入编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${findMap.eventNubmer }" placeholder="" id="eventNubmer" name="eventNubmer" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>加入姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${findMap.joinName }" placeholder="" id="joinName" name="joinName" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>加入卡号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${findMap.joinCard }" placeholder="" id="joinCard" name="joinCard" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账户金额：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${findMap.joinAmt }" placeholder="" id="joinAmt" name="joinAmt" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>帐户期：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="${findMap.joinVipzg }" placeholder="" id="joinVipzg" name="joinVipzg" />
				</div>
			</div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>加入时间：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" readonly="readonly" value="${findMap.joinTime }" placeholder="" id="joinTime" name="joinTime" />
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员生效时间：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" readonly="readonly" value="${findMap.joinVipsuTime }" placeholder="" id="joinVipsuTime" name="joinVipsuTime" />
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>距会员生效天数：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" readonly="readonly" value="${findMap.joinEffetime }" placeholder="" id="joinEffetime" name="joinEffetime" />
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>冻结金额：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" readonly="readonly" value="${findMap.joinNomoney }" placeholder="" id="joinNomoney" name="joinNomoney" />
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>帐户安全期：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" readonly="readonly" value="${findMap.joinZt }" placeholder="" id="joinZt" name="joinZt" />
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>加入类型：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" readonly="readonly" value="${findMap.joinMutype }" placeholder="" id="joinMutype" name="joinMutype" />
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>加入状态：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" readonly="readonly" value="${findMap.joinStatus }" placeholder="" id="joinStatus" name="joinStatus" />
                </div>
            </div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否申请互助：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<%--@declare id="sex-1"--%><input name="custZt" type="radio" value="0" <c:if test="${findMap.custZt == 0 }">checked</c:if> />
						<label for="sex-1">未申请</label>
					</div>
					<div class="radio-box">
						<%--@declare id="sex-2"--%><input name="custZt" type="radio" value="1" <c:if test="${findMap.custZt == 1 }">checked</c:if> />
						<label for="sex-2">已申请</label>
					</div>
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
	var msg = "修改加入信息成功";
	var msger = "修改加入信息成功,请重试";
	var url = '<%=path %>/ht/join/joinUpdate.do';
	var inputskin = $('.skin-minimal input');icheck(inputskin);
	var validateform = $("#role-form");
	$("#role-form").validate({
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