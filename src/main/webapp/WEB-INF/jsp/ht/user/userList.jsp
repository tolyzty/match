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
		用户管理
		<span class="c-gray en">&gt;</span>
		用户查询
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" id="replace" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="<%=path %>/ht/user/userList.do" method="post" id="myform">
			 <div class="text-c">
<%-- 			 		<span>商户注册时间:</span>
				    <input type="text" onfocus="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="createTime" name="createTime" class="input-text Wdate" style="width:120px;">
					<input value="${reqMap.consumerNo }" type="text" name="consumerNo" id="consumerNo" placeholder="商户编号" style="width:180px" class="input-text" />
					<input value="${reqMap.agentId }" type="text" name="agentId" id="agentId" placeholder="代理商ID模糊查询" style="width:180px" class="input-text" /> --%>
					<input value="${requestScope.reqMap.islockId}" type="text" name="islockId" id="islockId" placeholder="推荐人编号模糊查询" style="width:180px;" class="input-text" />
					
					<span>是否推荐人:</span>
					<select class="select size-M" size="1" name="islock" style="width:85px;height:31px;">
						<option value="" selected="">请选择 </option>
						<option value="1" <c:if test="${requestScope.reqMap.islock=='1' }">selected</c:if>>推荐人</option>
						<option value="2" <c:if test="${requestScope.reqMap.islock=='2' }">selected</c:if>>已推荐</option>	
						<option value="0" <c:if test="${requestScope.reqMap.islock=='0' }">selected</c:if>>暂无</option>			
					</select>
					
					
					<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">查询</i></button>		
				</div>
				<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius" data-title="添加角色"  onclick="role_add('添加用户','<%=path %>/ht/user/userInsert.do','500','450')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>添加角色</a>
				</span>
				</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				
				<span class="l">
					<span style="margin-left:20px;font-size: 12px;">
					共有 [ <span style="color:red;">${totalCount}</span> ] 条记录&nbsp;&nbsp;			
				</span>
				</span>
					<span class="r">共有数据：<strong id="listCount">${totalCount}</strong> 条</span>
			</div>				
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover" id="tables">
					<thead>
						<tr class="text-c">
							<th width="80px;">编号</th>
							<th width="80px;">用户名称</th>
							<th width="120px;">手机号码</th>
							<!-- <th>电子邮箱</th>
							<th>昵称</th>
							<th>真是姓名</th>	
							<th>证件类型</th>
							<th>证件号码</th>
							<th>联系地址</th> -->
							<th>注册时间</th>
							<th>用户编号</th>
							<!-- <th>最后登陆时间</th> -->
							<th id="sbye" style="width:80px;cursor:pointer;">是否推荐人
							</th>
							<th>推荐人编号</th>
							<th>推荐成功人数</th>
							<th>推荐奖励金额</th>
							<th>奖励标示</th>
							<th>帐号状态</th>
							<th style="width: 170px;">操作</th>						
						</tr>
					</thead>
					<tbody id="taody">
						<c:forEach var="userlist" items="${requestScope.lists}">
							<tr class="text-c">			
								<td>${userlist.consumerNo }</td>
								<td>${userlist.userName }</td>
								<td>${userlist.userPhone }</td>
							<%-- 	<td>${userlist.userEmail }</td>
								<td>${userlist.userNickname }</td>
								<td>${userlist.userRealname }</td>
								<td>${userlist.credType }</td>
								<td>${userlist.userCredNumber }</td>
								<td>${userlist.userAddress}</td> --%>
								<td>${userlist.createTime}</td>
								<td>${userlist.agentId}</td>
								<%-- <td>${userlist.lastLoginTime}</td> --%>
								<td>
								<c:if test="${userlist.islock == 1}">推荐人</c:if>
								<c:if test="${userlist.islock == 0}">暂无</c:if>
								<c:if test="${userlist.islock == 2}">已推荐</c:if>
								
								</td>
								<td>${userlist.islockId}</td>
								<td>${userlist.islockNumber} 人</td>
								<td>
								  <c:if test="${empty userlist.islockAmt}">0.00   元</c:if>
								  <c:if test="${not empty userlist.islockAmt}">${userlist.islockAmt} 元</c:if>
								</td>
								<td>
								   <c:if test="${userlist.awardFlag ==0}">未奖励</c:if>
								    <c:if test="${userlist.awardFlag ==1}">已奖励</c:if>
								</td>
								<td>
								<c:if test="${userlist.userZt==0 }"><span class="label label-success radius">正常</span></c:if>
									<c:if test="${userlist.userZt==1 }"><span class="label  radius">已禁用</span></c:if>
								</td>
                                <td class="f-14 td-manage">
        <%-- 	<c:choose>
	   		<c:when  test="${custlist.userZt == '1'}">
	   			   		<a style="text-decoration:none" class="label radius" onclick="user_disena('${userlist.userId}','${userlist.userZt}');" href="javascript:;" title="启用" id="disEna"><i class="Hui-iconfont">&#xe676;</i></a>
	   		
	   		</c:when>
	   		<c:otherwise>
	   		<a style="text-decoration:none" class="label label-success radius" onclick="user_disena('${userlist.userId}','${userlist.userZt}');" href="javascript:;" title="停用" id="disEna"><i class="Hui-iconfont">&#xe631;</i></a>
	   		</c:otherwise>
		</c:choose>			 --%>
		<a style="text-decoration:none" class="btn btn-warning-outline radius size-MINI" 
			onClick="cust_edit('用户修改','<%=path %>/ht/user/userEdit.do?userId=${userlist.userId }','--','700','550')" 
			href="javascript:;" title="编辑" class="btn btn-warning-outline radius size-MINI">编辑
		</a>																	
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
   /*用户-编辑*/
	function cust_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	};
	/*用户-新增*/
	function role_add(title,url,w,h){
		layer_show(title,url,w,h);
	};
 
 	 var msgs = "是否要停用吗?";
 		var msg = "是否要启用吗?";
 
 function user_disena(uId,status){
 	var url = "<%=path%>/user/disEnas.do";
    if(status==1){
    	laycofm(url,uId,msg,0,'启用');
    }else{
    	laycofm(url,uId,msgs,1,'停用');
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
	$.post(url,{userId:uId,userZt:status},function(data){
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
