<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<h2>搜索宁波审核管理</h2>
	<div id="topmenu">
		<ul>
			<li class="current"><a href="index.jsp">首页</a></li>
			<!-- 
			<li><a href="users.html">位置审核</a></li>
			<li><a href="#">评论审核</a></li>
			<li><a href="#">图片审核</a></li>
			<li><a href="#">事件审核</a></li>
			<li><a href="#">用户管理</a></li>
		 -->
			<li><a href="#">系统设置</a></li>
			<li><a href="/admin/logout">注销登陆</a></li>
		</ul>
	</div>
	<div id="top-panel">
	<div id="panel">
		<ul>
			<li><a href="#" class="search">搜索</a></li>
			<li><a href="#" class="feed">RSS Feed</a></li>
			<li>当前登陆用户：${sonbUser.username}</li>
		</ul>
	</div>
</div>
</div>