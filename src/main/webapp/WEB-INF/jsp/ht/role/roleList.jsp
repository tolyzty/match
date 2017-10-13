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
		会员管理
		<span class="c-gray en">&gt;</span>
		角色管理
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" id="replace" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			      
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<a href="javascript:;" onclick="abce(0)" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
				<a class="btn btn-primary radius" data-title="添加角色" _href="<%=path %>/ht/role/roleinsert.do" onclick="role_add('添加用户','<%=path %>/ht/role/roleinsert.do','500','500')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>添加角色</a>
				</span>
				<span class="r">共有数据：<strong id="listCount">${requestScope.totalCount}</strong> 条</span>
					<form action="<%=path %>/ht/role/roleList.do" method="post" id="myform">	
			<div class="text-c">
			<input type="text" name="roleName" id="roleName" placeholder="按名字模糊查询" style="width:250px" class="input-text" value="${requestScope.reqMap.roleName}"  />
			<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont"></i> 查询</button>
			</div>	
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort" >
					<thead>
						<tr class="text-c">
							<th ><input type="checkbox" name="" value=""></th>
							<th >ID</th>
							<th >角色名称</th>
							<th >角色描述</th>
							<th >角色状态</th>
							<th >所属系统</th>
							<!-- <th >所属商户ID</th> -->
							<th >操作</th>
						</tr>
					</thead>
					<tbody id="taody">
						<c:forEach var="rolelist" items="${requestScope.lists}">
							<tr class="text-c">
								<td><input type="checkbox" value="" name=""></td>					
								<td>${rolelist.roleId }</td>
								<td>${rolelist.roleName }</td>
								<td>${rolelist.roleDesc }</td>
								<td>
								<c:choose>
							   		<c:when  test="${rolelist.roleStatus == '1'}">
							   		<span class="label label-success radius">已启用</span>
							   		</c:when>
							   		<c:otherwise>
							   		<span class="label radius">已停用</span>
							   		</c:otherwise>
								</c:choose>								
								</td>
								<td>
								<c:if test="${rolelist.sysId == '0002' }">商户系统</c:if>
								<c:if test="${rolelist.sysId == '0003' }">交易系统</c:if>
								<c:if test="${rolelist.sysId == '0001' }">运营系统</c:if>
								</td>
								<%-- <td>${rolelist.merId }</td> --%>
								<td class="f-14 td-manage">
									<a style="text-decoration:none" class="ml-5" onClick="role_edit('用户修改','<%=path %>/ht/role/roleEdit.do?roleId=${rolelist.roleId}','--','500','550')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
									<a id="deletes" style="text-decoration:none" class="ml-5"  href="javascript:;" title="删除" onClick="findByKeyId('${rolelist.roleId}')"><i class="Hui-iconfont">&#xe6e2;</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="role_edit('分配权限','<%=path %>/ht/role/roleShri.do?roleId=${rolelist.roleId}','--','400','450')" href="javascript:;" title="分配权限"><i class="Hui-iconfont">&#xe63c;</i></a>
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
		/*菜单-编辑*/
	function role_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	};
	/*菜单-新增*/
	function role_add(title,url,w,h){
		layer_show(title,url,w,h);
	};
	
	
	/**
	*查询是否可以删除角色
	*@parme roleId ID
	*/
	function findByKeyId(roleId){
	  $.ajax({
	   type:'post',
	   url:'<%=path %>/ht/role/findByKeyId.do',
	   dataType:'JSON',
	   data:{
	   	 roleId:roleId
	   },
	   success:function(data){
	   if(data.result==1){
	   		lay("对不起,不能删除此角色",2);
	   	}else{
	   	 	laycofm("是否要删除此角色",roleId);
	   	}
	   }
	  });
	};
	
	
	/**
	* 执行删除操作
	**/
function deletes(roleId){
	   $.ajax({
	   	url:'<%=path %>/ht/role/deletes.do',
	    type:'post',
	    dataType:'json',
	    data:{roleId:roleId},
	    success:function(data){
	    	if(data.result==1){
	    	    layer.msg('删除成功', {icon: 1,offset:'80px'});
	    	    location.reload();
	    	}else{
	    		layer.msg('删除失败', {icon: 2,offset:'80px'});
	    	}
	    }
	   });

	};
	
	/**
 * confim提示信息框
 * @param msg  文本信息
 * @param roleId ID
 */
function laycofm(msg,roleId){
	layer.confirm(msg, {icon: 3, title:'系统提示', btn: ['删除','取消'],offset:'150px'
	}, function(index){
		deletes(roleId);
	},function(index){
		layer.close(index);
 });
};

  </script>
</html>