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
	<input id="locations_total" type="hidden" value="${total}" name="locations_total">
	</div>
	<!-- 
	<div id="infowrap" style="height: 578px">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>
	 -->
	 
<div class="location_count">
  <div class="total_loc" id="content_location">
  
    </div>
    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
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

<script type="text/template" id="template_location">


	
			<div class="location">
		      <div class="parts">
		        <div class="border">
		<@ if(photo_path == 'undefined' || "0" == photo_path || photo_path == null){@>
		<img src="/images/shop_photo_gray.gif" />
		<@}@> 
		<@ if(photo_path != null && photo_path != "0"){@>
		<img src='<@="http://api.soningbo.com/upload/" + photo_path.substring(0,4) + "/" + photo_path.substring(4,8) + "/" + photo_path.substring(8,12) + "/" + photo_path.substring(12)+"-100x100" @>' />
		<@}@>
		          
		         <p><a href="/admin/location/edit/<@=id @>" id="<@=md5Value @>" class="edit_<@=id @>">编辑</a>  <a href="/admin/location/appoval/<@=id @>" id="<@=md5Value @>" class="appoval_<@=id @>">审核</a>  <a href="/admin/location/delete/<@=id @>" id="<@=md5Value @>" class="delete_<@=id @>">删除</a></p>
		        </div>
		      </div>
		       <div class="parts margin">
		        <p>名称：<@=name_cn @></p>
		        <p>地址：<@=address_cn @></p>
		        <p>关键字：<@=tags_cn @></p>
		        <p>类别：
		        <p>电话号码：<@=telephone @>&nbsp;&nbsp;&nbsp;&nbsp;营业时间：8:30~22:00</p>
		        <p>信息完整度：90%</p>
		      </div>
		    </div>	


	
</script>