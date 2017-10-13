<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<jsp:include page="/WEB-INF/jsp/plug/_qtlink.jsp" />
  </head>
  
  <body>
  <jsp:include page="/WEB-INF/jsp/plug/_qtmenu.jsp" />
  <br>
  <!--头部  star-->
		<header>
			<a href="javascript:history.go(-1);">
				<div class="_left"><img src="images/Arrow_left_icon.png"></div>已有账户充值
			</a>
		</header>

  <div class="banner">
				<span>${parMap}</span>
		</div>
		<p>帐户余额:${parMap.joinAmt }</p>
		<p>是否加入:
		<c:if test="${parMap.joinStatus=='0'}">未加入,未付款</c:if>
		<c:if test="${parMap.joinStatus=='1'}">已加入,修改帐户金额</c:if>
		</p>
  		<br>
		<!--充值列表-->
		<form action="<%=path%>/index/pay.do" method="post" >	
		<br>加入人姓名:<input type="text" name="joinName" value="${parMap.joinName}" id="joinName" />
  		<br>加入人卡号：<input type="text" name="joinCard" value="${parMap.joinCard}" id="joinCard" />	
  		<br>微信支付:<input type="text" name="payType" value="1">
  		<br>项目ID：<input type="text" name="mutualId" value="${parMap.mutualId }" />
  		<br>项目类别:<input type="text" name="muType" value="${parMap.mutualType }"/>
  		<br>项目受助资格：<input type="text" name="mutualVipzg" value="${parMap.mutualVipzg }" />
  		<br>项目观察天数：<input type="text" name="mutualUnder" value="${parMap.mutualUnder }" />
  		<br>交易类型:<input type="text" name="orderType" value="old" />
  		<br>用户编号ID:<input type="text" name="agentId" id="agentId" value="${parMap.agentId }" />
  		<br>
		<div class="person_wallet_recharge">
			<ul class="ul">
                <li class="current">
                    <span>￥500</span>
                    <div class="sel" style="display: block;"></div>
                </li>
                <li>
                    <span>￥800</span>
                    <div class="sel" style=""></div>
                </li>
                <li>
                    <span>￥1200</span>
                    <div class="sel" style=""></div>
                </li>
                <li>
                    <span>￥2000</span>
                    <div class="sel" style=""></div>
                </li>
                <li>
                    <span>￥3000</span>
                    <div class="sel" style=""></div>
                </li>
                <li>
                    <span>￥5000</span>
                    <div class="sel" style=""></div>
                </li>
                <li>
                   <span>￥10000</span>
                    <div class="sel" style=""></div>
                </li>
                <li>
                   <span>￥20000</span>
                    <div class="sel" style=""></div>
                </li>
                <li>
					<span>￥50000</span>
                    <div class="sel" style=""></div>
                </li>
                 <div style="clear: both;"></div>
            </ul>
            <div class="pic">金额：<input type="text" placeholder="金额必须为10元以上" id="txt" /></div>
            <div class="botton">我要充值</div>
            <div class="agreement"><p>点击我要充值，即您已经表示同意<a>《充返活动协议》</a></p></div>
            <!--遮罩层-->
            <div class="f-overlay"></div>
            <div class="addvideo" style="display: none;">
            	<h3>本次充值<span id="requeAmt">500</span>元</h3>
            	<input type="hidden" name="payAmt" value="500"  id="payAmt"/>
	            <ul>
	            	<li><a id="nextSubs">微信支付</a></li>
	            	<li><a>支付宝支付</a></li>
	            	<li class="cal">取消</li>
	            </ul> 
            </div>
		</div>
  </form>
     	
  </body>
 <script type="text/javascript">
	var joinName = $("#joinName").val();
    var joinCard = $("#joinCard").val();
	$("#nextSubs").click(function(){
	 var url = "<%=path%>/index/findByJoin.do";
      var data = $("form").serializeArray();
		if ($("#joinName").val()=="") {
			alert("用户名比填");
			return false;
		}else{
			alert("走否则");
			//findByjo(url,data);
			$("form").submit();
			return false;
		}
	});
 </script>
</html>
