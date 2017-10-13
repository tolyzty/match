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
		项目管理
		<span class="c-gray en">&gt;</span>
		项目查询
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" id="replace" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
	 <article class="cl pd-20">
		<form action="<%=path %>/order/orderList.do" method="post" id="myform" >
		 <div class="text-c">
	 		<%-- <span>交易时间:</span>
		   		 <input type="text" 
		   		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'requestEndTime\')}',startDate:'%y-%M-%d'})" id="requestStatrtTime" name="requestStatrtTime" class="input-text Wdate" style="width:170px;" value="${paramTime.requestStatrtTime}" />
			<span> 到 </span>
				<input type="text" 
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59', minDate:'#F{$dp.$D(\'requestStatrtTime\')}',startDate:'%y-%M-%d'})" id="requestEndTime" name="requestEndTime" class="input-text Wdate" style="width:170px;" value="${paramTime.requestEndTime }" />
			<span>支付类型:</span>
			<select class="select" size="1" name="rateType" id="rateType" style="width:85px;height:31px;">
				<option value="" selected=""> 请选择 </option>
				<option value="0101" <c:if test="${requestScope.reqMap.rateType=='0101' }">selected</c:if>>网银</option>
				<option value="0102" <c:if test="${requestScope.reqMap.rateType=='0102' }">selected</c:if>>网银 WAP</option>
				<option value="0201" <c:if test="${requestScope.reqMap.rateType=='0201' }">selected</c:if>>微信扫码</option>
				<option value="0202" <c:if test="${requestScope.reqMap.rateType=='0202' }">selected</c:if>>微信直连</option>
				<option value="0301" <c:if test="${requestScope.reqMap.rateType=='0301' }">selected</c:if>>支付宝扫码</option>
				<option value="0302" <c:if test="${requestScope.reqMap.rateType=='0302' }">selected</c:if>>支付宝直连</option>
				<option value="0501" <c:if test="${requestScope.reqMap.rateType=='0501' }">selected</c:if>>QQ钱包扫码</option>
				<option value="0502" <c:if test="${requestScope.reqMap.rateType=='0502' }">selected</c:if>>QQ钱包直连</option>
			</select> 
			<span>交易状态:</span>
			<select class="select size-M" size="1" name="orderStatus" style="width:85px;height:31px;">
				<option value="" selected=""> 请选择 </option>
				<option value="0" <c:if test="${requestScope.reqMap.orderStatus=='0' }">selected</c:if>>处理中</option>
				<option value="1" <c:if test="${requestScope.reqMap.orderStatus=='1' }">selected</c:if>>成功</option>
				<option value="2" <c:if test="${requestScope.reqMap.orderStatus=='2' }">selected</c:if>>失败</option>
				<option value="3" <c:if test="${requestScope.reqMap.orderStatus=='3' }">selected</c:if>>已退款</option>
				<option value="4" <c:if test="${requestScope.reqMap.orderStatus=='4' }">selected</c:if>>可疑</option>
			</select>
				<input class="btn btn-primary" type="button" value="导出数据" id="download"/>
			<br><br>
			<input type="text" name="consumerNo" id="consumerNo" 
				placeholder="商户编号" 
				style="width:180px" 
				class="input-text" 
				value="${requestScope.reqMap.consumerNo}"  />
			<input type="text" name="orderNo" id="orderNo" 
				placeholder="订单号" 
				style="width:180px" 
				class="input-text" 
				value="${requestScope.reqMap.orderNo}"  />
			
			<input type="text" name="merOrderNo" id="merOrderNo" 
				placeholder="商户订单号" 
				style="width:180px" 
				class="input-text" 
				value="${requestScope.reqMap.merOrderNo}"  />
			
			<input type="text" name="consumerName" id="consumerName" 
				placeholder="商户名称" 
				style="width:180px" 
				class="input-text" 
				value="${requestScope.reqMap.consumerName}"  />		
			<input type="text" name="agentId" id="agentId" 
				placeholder="代理商ID模糊查询" 
				style="width:180px" class="input-text"
				value="${requestScope.reqMap.agentId }" />	
			&nbsp;
			<select class="select input-text" size="1" name="platform" style="width:200px;height:31px;">
				<option value="" selected="">--选择平台名称--</option>
				<c:forEach items="${findwh}" var="wh" >
				<option value="${wh.cooporgNo }" <c:if test="${requestScope.reqMap.platform==wh.cooporgNo }">selected</c:if>>${wh.coopName }</option>
				</c:forEach>
			</select>
			
			
			
			<input type="hidden" name="vaArr" id="vaArr" value="" /> --%>
<!-- 			<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont"></i> 查询</button>			
 -->		
 					<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius" data-title="添加项目"  onclick="mutual_add('添加项目','<%=path %>/ht/mutual/mutualInsert.do','500','450')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>新增项目</a>
				</span>
				</div>
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
							<td>类别名称</td>
					<td>加入金额</td>
					<td>加入人数</td>
					<td>成功互件数</td>
					<td>互助类别</td>
					<td>帐户预存金额</td>
					<td>单次最高金额</td>
					<td>观察天数</td>
					<td>服务费用</td>	
					<td>互助内容</td>	
					<td>余额要求</td>	
					<td>会员资格说明</td>	
					<td>会员资格</td>	
					<td>时间</td>	
					<td>状态</td>	
					<td>操作</td>
						</tr>
					</thead>
	<tbody id="taody">
			<c:forEach items="${lists }" var="mutuallist">
			  		<tr class="text-c">
			  				<td>${mutuallist.mutualName }</td>
					     	<td>${mutuallist.mutualAmt }</td>
					     	<td>${mutuallist.mutualNumber }</td>
					     	<td>${mutuallist.mutualEvent }</td>
					     	<td>${mutuallist.mutualType }</td>
					     	<td>${mutuallist.mutualPreamt }</td>
					     	<td>${mutuallist.mutualMumamt }</td>
					     	<td>${mutuallist.mutualUnder }</td>
					     	<td>${mutuallist.mutualCharge }</td>
					     	<td>${mutuallist.mutualContent }</td>
					     	<td>
					     		${mutuallist.mutualClaims }
					     	</td>
					     	<td>${mutuallist.mutualVipdesc }</td>
					     	<td>${mutuallist.mutualVipzg }</td>
					     	
					     	<td>${mutuallist.mutualTime }</td>
					     	<td>${mutuallist.mutualZt }</td>
					     	<td>
					     	<a style="text-decoration:none" class="btn btn-warning-outline radius size-MINI" 
			onClick="mutual_edit('项目修改修改','<%=path %>/ht/mutual/mutualEdit.do?mutualId=${mutuallist.mutualId}','--','600','550')" 
			href="javascript:;" title="编辑" class="btn btn-warning-outline radius size-MINI">编辑
		</a>			  
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
  
  /*订单详情页面*/
	function mutual_add(title,url,w,h){
		layer_show(title,url,w,h);
	};
  
  /*用户-编辑*/
	function mutual_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	};
  
  
 
 /**
 *点击事件
 **/
 function ruacc(orNo){
	   layer.confirm('确认入账', {icon: 3, title:'系统提示', btn: ['入账','取消'],offset:'150px'
		}, function(index){
			addacc(orNo);
		},function(index){
			layer.close(index);
	 });
 };
 /**
 *执行入账
 *@param 订单编号
 **/
 function addacc(ordNo){
 	$.ajax({
       url:'<%=path%>/order/accAdd.do',
       type:"POST",
       data:{
          orderNo:ordNo,
       },
       success:function(data){
          if(data.msg=='success'){
             layer.msg('入账成功', {icon: 1,offset:'80px'});
	    	  location.reload();
	    	}else{
	    		layer.msg('入账失败', {icon: 2,offset:'80px'});
	    	}       
	    }
    });
 };
 
 function backCall(orderNo){
		$.ajax({
	  		  url:'<%=path%>/order/payBackCall.do',
	  		  data:{"prdOrdNo":orderNo},
	  		  type:"POST",
	  		  success:function(data){
	  		  		alert("通知成功 ");
	  		  },
	  		  error:function(){
	  		  		alert("通知失败，服务器连接异常... ");
	  		  }
	  	});
};
/**
*  确认收款
*
**/
function confimbackCall(orderNo){
	$.ajax({
		url:'<%=path%>/order/confimBackCall.do',
		data:{"prdOrdNo":orderNo},
		type:"POST",
		success:function(data){
			 if(data.map.respCode=='00'){
             layer.msg('收款确认成功', {icon: 1,offset:'80px'});
	    	  location.reload();
	    	}else{
	    	  layer.msg('收款确认失败', {icon: 2,offset:'80px'});
	    	}       
		},
		error:function(data){
			alert("确认失败");
		}
	});
}

/**
*导出列表
*/
 $("#download").click(function(){
   var urls = "<%=path%>/order/exportBrand.do";
   var urlsdown = "<%=path%>/uploadfile/paymentExcel.xls";
   var table = $("table");
   var th = "th";
   var varId = $("#vaArr");
   var formId = $("#myform");
   download(urls,urlsdown,table,th,varId,formId);
 });


  </script>
</html>
