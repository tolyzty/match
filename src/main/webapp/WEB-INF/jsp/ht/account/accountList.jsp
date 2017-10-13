<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
   <jsp:include page="/WEB-INF/jsp/plug/_head.jsp" />
     <jsp:include page="/WEB-INF/jsp/plug/_menu.jsp" />
     
    <section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		帐户管理
		<span class="c-gray en">&gt;</span>
		帐户查询
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" id="replace" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
	 <article class="cl pd-20">
		<form action="<%=path %>/ht/account/accountList.do" method="post" id="myform" >
		 <div class="text-c">
	 		<span>交易时间:</span>
		   		 <input type="text" 
		   		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'requestEndTime\')}',startDate:'%y-%M-%d'})" id="requestStatrtTime" name="requestStatrtTime" class="input-text Wdate" style="width:170px;" value="${paramTime.requestStatrtTime}" />
			<span> 到 </span>
				<input type="text" 
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59', minDate:'#F{$dp.$D(\'requestStatrtTime\')}',startDate:'%y-%M-%d'})" id="requestEndTime" name="requestEndTime" class="input-text Wdate" style="width:170px;" value="${paramTime.requestEndTime }" />
<!-- 		<button type="button" name="buton" id="buton" >添加order</button>
		<button type="button" name="buts" id="buts">充值成功</button> -->
		</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<span style="margin-left:20px;font-size: 12px;">
					共有 [ <span style="color:red;">${listsize}</span> ] 条记录 , 
					&nbsp;&nbsp;
				</span>
				</span>
					<span class="r">共有数据：<strong id="listCount">${listsize}</strong> 条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover" >
					<thead>
						<tr class="text-c">
							<td>用户编号</td>
							<td>帐户编号</td>
							<td>帐户类型</td>
							<td>帐户余额</td>
							<td>冻结金额</td>
							<td>最后更新时间</td>
							<td>帐户状态</td>			
							<td>操作</td>
						</tr>
					</thead>
	<tbody id="taody">
			<c:forEach items="${lists }" var="accountlist">
			  		<tr class="text-c">
			  				<td>${accountlist.consumerNo }</td>
					     	<td>${accountlist.agentId }</td>
					     	<td>${accountlist.acType }</td>
					     	<td>${accountlist.acBal }</td>
					     	<td>${accountlist.frozBalance }</td>
					     	<td>${accountlist.lstTxDatetime }</td>
					     	<td>
					     	<c:if test="${accountlist.accountStatus =='1' }">正常</c:if>
					     	<c:if test="${accountlist.accountStatus =='0' }">冻结</c:if>
					     	</td>
					     	<td></td>
			  		</tr>
			  </c:forEach>
	</tbody>
					
				</table>
				 <div style="margin-top:10px;">
				<jsp:include page="/WEB-INF/jsp/plug/_page.jsp" />
				</div>
			</div>
		
   
   		</article>
	</div>
	
</section>	 
  </body>
  
    <script type="text/javascript">
<%--     var URL = "<%=path%>/join/orderAdd.do";
  	$("#buton").click(function(){
	   $.ajax({
	   	url:"<%=path%>/order/orderAdd.do",
	   	data:{
	   		requestAmt:"188.00",
	   		orderStatus:"1",
	   		payType:"01",
	   		orderType:"01"
	   	},
	   	type:"POST",
	   	success:function(data){
	   		alert(data.msg);
	   	}
	   });
	});
	
	var URLACC = "<%=path%>/join/orderAcc.do";
	$("#buts").click(function(){
	 $.ajax({
	   	url:URLACC,
	   	data:{
	   		requestAmt:"188.00",
	   		orderStatus:"1",
	   		payType:"01",
	   		orderType:"01"
	   	},
	   	type:"POST",
	   	success:function(data){
	   		alert(data.msg);
	   	}
	   });
	}); --%>
  </script>

</html>
