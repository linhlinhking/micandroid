<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<div id="rightnow">
		<h3 class="reallynow">
			<span>Notifications</span>
			
			<a href="#" class="add" id="user-add-event">Add Event</a>
			<!-- <a href="#" class="app_add">Some Action</a>  -->
			<br />
		</h3>
		<!-- 
		<p class="youhave">
			You have <a href="#">19 new orders</a>, <a href="#">12 new users</a>
			and <a href="#">5 new reviews</a>, today you made <a href="#">$1523.63
				in sales</a> and a total of <strong>$328.24 profit </strong>
		</p>
		 -->
	</div>
	<!-- list Events Start -->
	<div id="infowrap" style="height: 578px">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>
	<!-- list Events End -->
</div>


<div id="addEvents" style="display: none">
		<div id="add-events-dialog-form" title="添加活动信息">
			<form id="addEventsForm" method="POST">
			<table>
				<tr>
					<td><label for="name">活动类别: </label></td>
					<td>
						<select class="easyui-combobox" id="eventsCategory" name="eventsCategory" style="width:204px;"> 
								<option value="0" selected>请选择</option>
								<option value="1">演出/电影</option>
								<option value="2">生活/聚会</option>
								<option value="3">体育/健身</option>
								<option value="4">派对/夜店</option>
								<option value="5">公益/环保</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="name">活动标题(中文): </label></td>
					<td>
						<input id="title_cn" name="title_cn" required="true" style="width:200px;">
					</td>
				</tr>
				<tr>
					<td><label for="name">活动标题(英文): </label></td>
					<td>
						<input id="title_en" name="title_en" required="true" style="width:200px;">
					</td>
				</tr>
				<tr>
					<td><label for="name">活动主题(中文): </label></td>
					<td>
						<input id="subject_cn" name="subject_cn" required="true" style="width:200px;">
					</td>
				</tr>
				
				<tr>
					<td><label for="name">活动主题(英文): </label></td>
					<td>
						<input id="subject_en" name="subject_en" required="true" style="width:200px;">
					</td>
				</tr>
				
					<tr>
					<td><label for="name">活动地点(中文): </label></td>
					<td>
						<input id="address_cn" name="address_cn" required="true" style="width:200px;">
					</td>
				</tr>
				
				<tr>
					<td><label for="name">活动地点(英文): </label></td>
					<td>
						<input id="address_en" name="address_en" required="true" style="width:200px;">
					</td>
				</tr>
				
				<tr>
					<td><label for="name">联系方式: </label></td>
					<td>
						<input id="telephone" name="telephone" required="true" style="width:200px;">
					</td>
				</tr>
				
				<tr>
					<td><label for="name">活动组织者: </label></td>
					<td>
						<input id="organizer" name="organizer" required="true" style="width:100px;" value="${sonbUser.username }">
					</td>
					<td><label for="name">人均费用: </label></td>
					<td>
						<input id="eventPrice" name="selectPrice" type="radio">&nbsp;&nbsp;费用为：
						<input id="price" name="price" style="width:50px;">&nbsp;&nbsp;元
						<input id="eventFree" name="selectPrice" type="radio">免费
					</td>
				</tr>
				
				<tr>
					<td><label for="name">是否重复: </label></td>
					<td>
						<input id="organizer" name="organizer" required="true" style="width:200px;" value="${sonbUser.username }">
					</td>
				</tr>
				
			</table>
			</form>
		</div>
</div>