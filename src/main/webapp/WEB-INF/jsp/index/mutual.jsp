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
     <table class="table table-border table-bordered table-bg table-hover" >
					<thead>
						<tr class="text-c">
							<td>类别名称</td>
					<td>加入金额</td>
					<td>加入人数</td>
					<td>成功互件数</td>
					<td>互助类别</td>
					<td>预存金额</td>
					<td>观察天数</td>
					<td>服务费用</td>	
					<td>互助内容</td>	
					<td>余额要求</td>	
					<td>会员资格说明</td>	
					<td>余额要求</td>	
					<td>受助资格</td>	
					<td>福利,其他</td>	
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
					     	<td>${mutuallist.mutualUnder }</td>
					     	<td>${mutuallist.mutualContent }</td>
					     	<td><c:if test="${mutuallist.mutualCharge!='0' }">
					     		<span>账户余额<${mutuallist.mutualCharge },会失去会员资格</span>
					     	</c:if>
					     	
					     	</td>
					     	<td>${mutuallist.mutualVipdis }</td>
					     	<td>${mutuallist.mutualClaims }</td>
					     	<td>${mutuallist.mutualMuclaims }</td>
					     	<td>${mutuallist.mutualOther }</td>
					     	<td>${mutuallist.mutualTime }</td>
					     	<td>${mutuallist.mutualZt }</td>
					     	<td>
						     	<form action="<%=path%>/index/joinMutual.do" method="post">
							     	<input type="text" name="mutualId" value="${mutuallist.mutualId}" />
							     	<input type="text"  name="mutualType" value="${mutuallist.mutualType}"/>
							     	<input type="submit" value="加入" id="but" />
						     	</form>
					     	</td>
			  		</tr>
			  </c:forEach>
	</tbody>
					
				</table>
  </body>
  <script type="text/javascript">

  	$("#but").click(function(){
  		
  	});
  </script>
</html>
