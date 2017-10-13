<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path%>/ht/adminuser/userList.do">员工管理</a></li>
					<li><a href="<%=path%>/ht/role/roleList.do">角色管理</a></li>
					<li><a href="<%=path%>/ht/menu/menuList.do">菜单管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 项目管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path %>/ht/mutual/mutualList.do" title="项目列表">项目查询</a></li>
				</ul>
			</dd>
		</dl>
 		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path %>/ht/user/userList.do" title="用户查询">用户查询</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-join">
			<dt><i class="Hui-iconfont">&#xe613;</i> 加入管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path %>/ht/join/joinList.do" title="加入用户查询">加入查询</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-join">
			<dt><i class="Hui-iconfont">&#xe613;</i> 互助管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path %>/ht/cust/custList.do" title="申请互助人查询">互助查询</a></li>
				</ul>
			</dd>
		</dl>
			<dl id="menu-account">
			<dt><i class="Hui-iconfont">&#xe613;</i> 帐户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path %>/ht/account/accountList.do" title="帐户查询">帐户查询</a></li>
					<li><a href="<%=path %>/ht/account/logsList.do" title="日志查询">日志查询</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path %>/ht/role/roleList.do" title="角色管理">角色管理</a></li>
					<li><a href="<%=path %>/ht/adminuser/userList.do" title="运营管理">运营用户管理</a></li>
					<li><a href="<%=path %>/ht/adminuser/userLists.do" title="权限管理">权限管理</a></li>
				<!-- 	<li><a href="admin-role.html" title="角色管理">角色管理</a></li>
					<li><a href="admin-permission.html" title="权限管理">权限管理</a></li>
					<li><a href="admin-list.html" title="管理员列表">管理员列表</a></li> -->
				</ul>
			</dd>
		</dl>
	<!-- 	<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 系统统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="charts-1.html" title="折线图">折线图</a></li>
					<li><a href="charts-2.html" title="时间轴折线图">时间轴折线图</a></li>
					<li><a href="charts-3.html" title="区域图">区域图</a></li>
					<li><a href="charts-4.html" title="柱状图">柱状图</a></li>
					<li><a href="charts-5.html" title="饼状图">饼状图</a></li>
					<li><a href="charts-6.html" title="3D柱状图">3D柱状图</a></li>
					<li><a href="charts-7.html" title="3D饼状图">3D饼状图</a></li>
				</ul>
			</dd>
		</dl> -->
		<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="system-base.html" title="系统设置">系统设置</a></li>
					<li><a href="system-category.html" title="栏目管理">栏目管理</a></li>
					<li><a href="system-data.html" title="数据字典">数据字典</a></li>
					<li><a href="system-shielding.html" title="屏蔽词">屏蔽词</a></li>
					<li><a href="system-log.html" title="系统日志">系统日志</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>

