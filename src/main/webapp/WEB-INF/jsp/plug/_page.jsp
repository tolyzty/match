<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <script type="text/javascript">
	/**
*分页执行post提交
*/
function pages(s){
         	var inp = "<input type='hidden' name='pageIndex' value='"+s+"' />";
         	$("#myform").append(inp);
         	myform.submit();
 };
</script>
<div class="left "></div>
         <div class="right">
         
         <c:if test="${reqMap.pageIndex != 1 }">
		         		<a onclick="pages(1)" class="pagebutton" >首页</a>
		         		<a onclick="pages(${reqMap.pageIndex-1 })" class="pagebutton" >上一页</a> 
		         	<%-- 	href="<%=path %>/role/roleList.do?page=${page.pageIndex-1 }"
		         			href="<%=path %>/role/roleList.do?page=1"
		         	 --%>
		         	</c:if>
		         	<c:if test="${reqMap.pageIndex == 1 }">
		         		<a class="pagebutton" href="javascript:void(0)">首页</a>
		         		<a class="pagebutton" href="javascript:void(0)">上一页</a>
		         	</c:if>
		         	
		         	<c:forEach var="pageIndex" begin="1" end="${reqMap.totalPage }">
		         		<c:if test="${pageIndex == reqMap.pageIndex}">
		         			<a class="pagebutton current" onclick="pages(${pageIndex })">${pageIndex }</a>
		         			<%-- href="<%=path %>/role/roleList.do?page=${pageIndex }" --%>
		         		</c:if>
		         		<c:if test="${pageIndex >= reqMap.pageIndex-2  && pageIndex <= reqMap.pageIndex+2 && pageIndex != reqMap.pageIndex}">
		         			<a class="pagebutton" onclick="pages(${pageIndex })">${pageIndex }</a>
		         			<%-- href="<%=path %>/role/roleList.do?page=${pageIndex }" --%>
		         		</c:if>
		         	</c:forEach>
		         	<c:if test="${reqMap.pageIndex != reqMap.totalPage }">	
		         <%-- 	href="<%=path %>/role/roleList.do?page=${page.pageIndex+1 }" --%>
		         		<a class="pagebutton" onclick="pages(${reqMap.pageIndex+1 })" >下一页</a>
		         		<a class="pagebutton" onclick="pages(${reqMap.totalPage})">尾页</a>
		         		<%-- href="<%=path %>/role/roleList.do?page=${page.totalSize}" --%>
		         	</c:if>
		         	<c:if test="${reqMap.pageIndex == reqMap.totalPage }">
		         		<a class="pagebutton" href="javascript:void(0)">下一页</a>
		         		<a class="pagebutton" href="javascript:void(0)">尾页</a>
		         	</c:if>
         </div>
       
      </form>
    