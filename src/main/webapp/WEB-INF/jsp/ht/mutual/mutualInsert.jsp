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
		<form action="" method="post" class="form form-horizontal" id="insertMutual">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目名称：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text"  readonly="readonly" value="" id="mutualName" name="mutualName">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>总加入金额：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" readonly="readonly" value="" id="mutualAmt" name="mutualAmt" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"> </span>总加入人数:</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="" id="mutualNumber" name="mutualNumber" />
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>加入预存金额:</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text"  value="" id="mutualPreamt" name="mutualPreamt" />
				</div>
			</div> 
			 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目类型:</label>
				<div class="formControls col-xs-6 col-sm-9">			
 						<span class="select-box">						
						<select class="select" size="1" name="mutualType" id="mutualType">
								<option value="">请选择</option>
						    	<option value="01" >抗癌互助</option>
						    	<option value="02" >中青年互助</option>
						    	<option value="03" >老年互助</option>
						    	<option value="04" >儿童互助</option>
						    	<option value="05" >意外互助</option>
						 </select>  
						</span>
 				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>单次最高金额：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="" id="mutualMumamt" name="mutualMumamt" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目观察天数：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" placeholder="观察天数:例如 180天" value="" id="mutualUnder" name="mutualUnder" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目服务费：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text"  placeholder="项目服务费"  value="" id="mutualCharge" name="mutualCharge" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>帐户预存金额：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="" id="mutualPreamt" name="mutualPreamt" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目年龄：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="" id="mutualAge" name="mutualAge" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目内容名称：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" placeholder="癌症医疗费用" value="" id="mutualContent" name="mutualContent" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目余额要求：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="" id="mutualOfferamt" name="mutualOfferamt" />
				</div>
			</div>
			
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目会员资格说明：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="" id="mutualVipdesc" name="mutualVipdesc" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目会员资格：</label>
				<div class="formControls col-xs-6 col-sm-9">
					<input type="text" class="input-text" value="" id="mutualVipzg" name="mutualVipzg" />
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>项目发布时间：</label>
				<div class="formControls col-xs-6 col-sm-9">
				 <input type="text" 
		   		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
		   		 id="mutualTime" name="mutualTime" 
		   		 class="input-text Wdate"  value="" />
				</div>
			</div>

			 
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">项目状态：</label>
				<div class="formControls col-xs-6 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="mutualZt" type="radio" value="1" checked="checked"/>
						<label for="sex-2">发布</label>
					</div>
					<div class="radio-box">
						<input name="mutualZt" type="radio" value="0"  />
						<label for="sex-1">待发布</label>
					</div>
					
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
     var msg = "新增项目成功";
     var msger = "新增项目失败";
     var url = "<%=path %>/ht/mutual/insertMutual.do";
     var inputskin = $('.skin-minimal input');icheck(inputskin);
     var myform = $("#insertform");
     $("#submt").click(function(){
     	 var data = myform.serialize();
	     addajax(url,data,msg,msger,1,2); 
     });
});



</script> 
</html>
