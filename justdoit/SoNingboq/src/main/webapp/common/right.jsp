<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sidebar">
	<ul>
		<li><h3>
				<a href="#" class="house">首页面板</a>
			</h3>
			<ul>
				<li><a href="#" class="search">搜索</a></li>
			</ul></li>
		<li><h3>
				<a href="#" class="folder_table">位置审核</a>
			</h3>
			<ul>
					<li><a href="/admin/location" class="manage_page">管理位置</a></li>
				<li><a href="#" class="cart">待审核位置</a></li>
				<li><a href="#" class="search">搜索位置</a></li>
			</ul></li>
		<li><h3>
				<a href="#" class="manage">活动审核</a>
			</h3>
			<ul>
				<li><a href="/admin/event" class="manage_page">管理活动</a></li>
				<li><a href="#" class="cart">待审核活动</a></li>
				<li><a href="#" class="search">搜索活动</a></li>
			</ul></li>
		<li>
			<li><h3>
				<a href="#" class="manage">评论审核</a>
			</h3>
			<ul>
				<li><a href="#" class="manage_page">管理评论</a></li>
				<li><a href="#" class="cart">待审核评论</a></li>
				<li><a href="#" class="search">搜索评论</a></li>
			</ul></li>
			<li><h3>
				<a href="#" class="manage">图片审核</a>
			</h3>
			<ul>
				<li><a href="#" class="manage_page">管理图片</a></li>
				<li><a href="#" class="cart">待审核图片</a></li>
				<li><a href="#" class="search">搜索图片</a></li>
			</ul></li>
		<li>
		<li>
			<h3>
				<a href="#" class="user">用户管理</a>
			</h3>
			<ul>
				<li><a href="/admin/user" class="group">管理用户</a></li>
				<li><a href="#" class="search">搜索用户</a></li>
				<li><a href="#" class="online">在线用户</a></li>
				
				<c:if test="${sonbUser.employeeIs}">
				<li><a href="/admin/staff" class="staffPicks">员工推荐</a></li>
				</c:if>
			</ul>
		</li>
		<li>
			<h3>
				<a href="#" class="user">API管理</a>
			</h3>
			<ul>
				<li><a href="#" class="online">管理API</a></li>
				<li><a href="#" class="group">在线测试</a></li>
				<li><a href="#" class="search">搜索API</a></li>
			</ul>
		</li>
	</ul>
</div>