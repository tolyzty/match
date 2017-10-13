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
		加入管理
		<span class="c-gray en">&gt;</span>
		加入查询
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" id="replace" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
	 <article class="cl pd-20">
		<form action="<%=path %>/ht/join/joinList.do" method="post" id="myform" >
		 <div class="text-c">
	 		<span>交易时间:</span>
		   		 <input type="text" 
		   		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'requestEndTime\')}',startDate:'%y-%M-%d'})" id="requestStatrtTime" name="requestStatrtTime" class="input-text Wdate" style="width:170px;" value="${paramTime.requestStatrtTime}" />
			<span> 到 </span>
				<input type="text" 
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59', minDate:'#F{$dp.$D(\'requestStatrtTime\')}',startDate:'%y-%M-%d'})" id="requestEndTime" name="requestEndTime" class="input-text Wdate" style="width:170px;" value="${paramTime.requestEndTime }" />
		<button type="button" name="buton" id="buton" >批量扣款</button>
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
							<td>编号</td>
							<td>用户编号</td>
							<td>事件编号</td>
							<td>加入人姓名</td>
							<td>加入证件号</td>
							<td>帐户余额</td>
							<td>冻结金额</td>	
							<td>会员资格</td>
							<td>加入互助时间</td>
							<td>距离加入天数</td>
							<td>会员生效时间</td>		
							<td>支付状态</td>	
							<td>加入类型</td>	
							<td>加入状态</td>	
							<td>申请互助</td>						
							<td>操作</td>
						</tr>
					</thead>
	<tbody id="taody">
	<c:forEach items="${lists }" var="joinlist">
  		<tr class="text-c">
  				<td>${joinlist.consumerNo }</td>
  				<td>${joinlist.agentId }</td>
		     	<td>${joinlist.custNumbers }</td>
		     	<td>${joinlist.joinName }</td>
		     	<td>${joinlist.joinCard }</td>
		     	<td>${joinlist.acBal }</td>
		     	<td>${joinlist.joinNomoney }</td>
		     	<td><c:if test="${joinlist.joinVipzg =='01' }">观察期</c:if>
		     	<c:if test="${joinlist.joinVipzg =='02' }">会员</c:if>
		     	
		     	</td>
		     	<td>${joinlist.joinTime }</td>
		     	<td>${joinlist.joinEffetime }</td>
		     	<td>${joinlist.joinVipsuTime }</td>					     	
		     	
		     	<td>
			     	<c:if test="${joinlist.orderStatus=='0' }"><span style="color:red;">待支付</span></c:if>
			     	<c:if test="${joinlist.orderStatus =='1' }">已付款</c:if>
		     	</td>
		     	<td>
		     	<c:if test="${joinlist.joinMutype=='01'}">老年</c:if>
		     	<c:if test="${joinlist.joinMutype=='02'}">中年版</c:if>
		     	<c:if test="${joinlist.joinMutype=='03'}">中年版</c:if>
		     		
		     	</td>
		     	<td>
		     	<c:if test="${joinlist.joinStatus=='1'}">已加入</c:if>
		     	<c:if test="${joinlist.joinStatus=='0'}"><span style="color:red;">未加入</span></c:if>
		     	</td>
		     	<td>
		     	<c:if test="${joinlist.custStatus=='0'}">审核中</c:if>
		     	<c:if test="${joinlist.custStatus=='1'}"><span style="color:#00FF00;">审核成功</span></c:if>
		     	</td>
		     	<td>
<%-- <a class="btn btn-warning-outline radius size-MINI" 
href="javascript:;" title="申请互助" 
onclick="join_details('申请互助','<%=path %>/ht/cust/custApply.do?agentId=${joinlist.agentId }','800','550');">申请互助</a>								
	 --%>	     
		     	</td>
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
    /**跳转到申请页面**/
    function join_details(title,url,w,h){
       layer_show(title,url,w,h);
    };
    
    var URL = "<%=path%>/ht/join/joinUpdate.do";
  	$("#buton").click(function(){
	   $.ajax({
	   	url:URL,
	   	data:{
	   		joinMutype:"02",
	   		joinStatus:"1",
	   	},
	   	type:"POST",
	   	success:function(data){
	   		alert(data.msg);
	   	}
	   });
	});
  </script>

</html>
