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
    <jsp:include page="/WEB-INF/jsp/plug/_head.jsp" />
   <jsp:include page="/WEB-INF/jsp/plug/_menu.jsp" />
   <section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		运营管理
		<span class="c-gray en">&gt;</span>
		用户管理
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" id="replace" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			      
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
<!-- 				<a href="javascript:;" onclick="abce(0)" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
 -->				<a class="btn btn-primary radius" data-title="添加用户" _href="<%=path %>/ht/adminuser/userinsert.do" onclick="user_add('添加用户','<%=path %>/ht/adminuser/userinsert.do','500','500')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>添加用户</a>
				</span>
			<!-- <form id="myform">
				<span><input type="text" value="" name="formname"/></span>
				<input type="submit" value="查询" />
				</form>	 -->
				<span class="r">共有数据：<strong id="listCount">${requestScope.totalCount}</strong> 条</span>
			</div>
			<form action="<%=path %>/ht/adminuser/userList.do" method="post" id="myform">				
				<%--  <input id="userId" type="text" name="userId" value="${userId }" />
				 <input type="submit" value="按用户名模糊查询"/>  --%>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover" >
					<thead>
						<tr class="text-c">
							<th ><input type="checkbox" name="" value=""></th>
							<th >编号</th>
							<th >登陆账号</th>
							<th >用户昵称</th>

							<th >用户状态</th>
							<!-- <th >所属系统</th> -->
							<th width="40px;">总登陆次数</th>
					
							<th width="50px;">最后登陆时间</th>
							<th >最后登陆IP</th>
							<th width="80px;">绑定手机号</th>
							<!-- <th >所属商户ID</th> -->
							<th >邮箱地址</th>
							<th >IP白名单</th>
							<th >所属角色</th>
							<th width="70px;">操作</th>
						</tr>
					</thead>
					<tbody id="taody">
						<c:forEach var="userlist" items="${requestScope.lists}">
							<tr class="text-c">
								<td><input type="checkbox" value="" name=""></td>					
								<td>${userlist.id }</td>
								<td>${userlist.userId }</td>
								<td>${userlist.userName }</td>
								<td>
								<c:choose>
							   		<c:when  test="${userlist.userStatus == '1'}">
							   		<span class="label label-success radius">已启用</span>
							   		</c:when>
							   		<c:otherwise>
							   		<span class="label radius">已停用</span>
							   		</c:otherwise>
								</c:choose>								
								</td>
							<%-- 	<td>
								<c:if test="${userlist.sysId == '0002'}">商户系统</c:if>
								<c:if test="${userlist.sysId == '0003'}">交易系统</c:if>
								<c:if test="${userlist.sysId == '0001'}">运营系统</c:if>
								</td> --%>
								<td>${userlist.lnum }</td>
							
								<td>${userlist.lastLoginTime }</td>
								<td>${userlist.lastLoginIp }</td>
								<td>${userlist.phone }</td>
								<%-- <td>${userlist.merId }</td> --%>
								<td>${userlist.email }</td>
								<td>${userlist.ipWhite }</td>
								<td>
								<c:forEach var="ro" items="${userlist.roles }">
								  <c:if test="${not empty ro.roleName}">
								  		${ro.roleName} ,
								  </c:if>
								</c:forEach>
								</td>
	
	<td class="f-14 td-manage">
	<c:choose>
	   		<c:when  test="${userlist.userStatus == '1'}">
	   		<a style="text-decoration:none" class="ml-5" onclick="user_disena('${userlist.id}','${userlist.userStatus}');" href="javascript:;" title="停用" id="disEna"><i class="Hui-iconfont">&#xe631;</i></a>
	   		</c:when>
	   		<c:otherwise>
	   		<a style="text-decoration:none" class="ml-5" onclick="user_disena('${userlist.id}','${userlist.userStatus}');" href="javascript:;" title="启用" id="disEna"><i class="Hui-iconfont">&#xe676;</i></a>
	   		</c:otherwise>
		</c:choose>			
		<a style="text-decoration:none" class="ml-5" onClick="user_edit('用户修改','<%=path %>/ht/adminuser/userEdit.do?id=${userlist.id}','--','500','550')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
	<a style="text-decoration:none" class="ml-5" onClick="user_edit('分配角色','<%=path %>/ht/adminuser/userShri.do?uId=${userlist.id}','--','400','450')" href="javascript:;" title="分配角色"><i class="Hui-iconfont">&#xe63c;</i></a>


	</td>
			</tr>
		</c:forEach>
	</tbody>
					
				</table>
			
				<div style="margin-top:10px;">
				<jsp:include page="/WEB-INF/jsp/plug/_page.jsp" />
				</div>
			</div>
		</form>
   
   		</article>
	</div>
	
</section>

  </body>
    <script type="text/javascript">	 
		/*菜单-编辑*/
	function user_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	};
	/*菜单-新增*/
	function user_add(title,url,w,h){
		layer_show(title,url,w,h);
	};

	

 var msg = "是否要停用吗?";
 var msgs = "是否要启用吗?";
 
 function user_disena(uId,status){
 	var url = "<%=path%>/ht/adminuser/disEna.do";
    if(status==1){
    	laycofm(url,uId,msg,0,'停用');
    }else{
    	laycofm(url,uId,msgs,1,'启用');
    }
 
 };
 /**
 * confim提示信息框
 * @param msg  文本信息
 * @param roleId ID
 */
function laycofm(url,uId,msg,status,btnmsg){
	layer.confirm(msg, {icon: 3, title:'系统提示', btn: [btnmsg,'取消'],offset:'150px'
	}, function(index){
		godis(url,uId,status);
	},function(index){
		layer.close(index);
 });
};
function godis(url,uId,status){
	$.post(url,{id:uId,userStatus:status},function(data){
		if(data.msg=='success'){
	    	    layer.msg('设置成功', {icon: 1,offset:'80px'});
	    	    location.reload();
	    	}else{
	    		layer.msg('设置失败', {icon: 2,offset:'80px'});
	    	}
 	});
	
};
 
  </script>
</html>
