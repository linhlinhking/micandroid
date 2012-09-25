<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<div id="rightnow">
		<h3 class="reallynow">
			<span>Notifications</span>
			<!-- 
			<a href="#" class="add">Add New Product</a> <a
				href="#" class="app_add">Some Action</a> 
			 -->
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
	<div id="infowrap" style="height: 578px">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>
</div>

<div id="addLocation" style="display: none">
		<div id="add-location-dialog-form" title="添加位置信息">
			<form>
				<fieldset>
					<label for="locName">位置名称：</label> 
					<input type="text" name="name_cn" id="locationName"class="text ui-widget-content ui-corner-all" /> 
					
					<label for="locAddress">位置地址：</label> 
					<input type="text" name="address_cn" id="locationAddress" class="text ui-widget-content ui-corner-all" />
					
					
				</fieldset>
			</form>
		</div>
</div>

<div id="editLocation" style="display: none">
		<div id="edit-location-dialog-form" title="编辑位置信息">
			
			
		</div>
</div>