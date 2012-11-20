<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
<!-- 	
	<div id="rightnow">
		<h3 class="reallynow">
			<span>编辑位置信息</span>
			
			<a href="#" class="add">Add New Product</a> <a
				href="#" class="app_add">Some Action</a> 
			
			<br />
		</h3>
		<p class="youhave">
			You have <a href="#">19 new orders</a>, <a href="#">12 new users</a>
			and <a href="#">5 new reviews</a>, today you made <a href="#">$1523.63
				in sales</a> and a total of <strong>$328.24 profit </strong>
		</p>
	</div>
-->

	<div id="infowrap" style="height: 578px">
		<ul>
  			<li><a href="#edit_content_location">编辑位置信息</a></li>
  		</ul>
		
		<div id="edit_content_location">
		<c:url var="saveLocation" value="/admin/location/${pageNumber}/${pageSize}/edit/${location.id}" />
		<form:form modelAttribute="locationAttr" action="${saveLocation}" method="POST">
		<input type="hidden" name="id" value="${location.id}" >
		请选择类别：
		<select id="select_FirstCategory">
			<c:if test="${firstList != null }">
				<c:forEach var="first" items="${firstList}">
					<c:choose>
						<c:when test="${first.id == selectFirst.id }">
							<option id='<c:out value="${first.id}"/>' selected="selected"><c:out value="${first.name_cn}"/></option>
						</c:when>
					<c:otherwise>
						<option id='<c:out value="${first.id}"/>'><c:out value="${first.name_cn}"/></option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</select>
		--
		<select id="select_SecondCategory" name="secondCategorys[0].id">
			<c:if test="${secondList != null }">
				<c:forEach var="second" items="${secondList}">
					<c:choose>
						<c:when test="${second.id == location.secondCategorys[0].id}">
						<option id='<c:out value="${second.id}" />' value='<c:out value="${second.id}" />' selected="selected"><c:out value="${second.name_cn}"/></option>
						</c:when>
						<c:otherwise>
						<option id='<c:out value="${second.id}" />' value='<c:out value="${second.id}" />' ><c:out value="${second.name_cn}"/></option>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</c:if>
		</select>
		<br/>
		<input type="hidden" name="photo_path" value="${location.photo_path}"/>
		<input type="hidden" name="name_py" value="${location.name_py }"/>
		<input type="hidden" name="md5Value" value="${location.md5Value }"/>
		位置名称（中文）：<input type="text" name="name_cn" value="${location.name_cn}" style="width:400px"><br/>
		位置名称（英文）：<input type="text" name="name_en" value="${location.name_en }" style="width:400px"><br/>
		位置地址（中文）：<input type="text" name="address_cn" value="${location.address_cn }" style="width:540px"><br/>
		位置地址（英文）：<input type="text" name="address_en" value="${location.address_en }" style="width:540px"><br/>
		电话号码：<input type="text" name="telephone" value="${location.telephone}"><br/>
		经度°：<input type="text" name="longitude" value="${location.longitude }">
		维度°：<input type="text" name="latitude" value="${location.latitude }"><br/>
		位置标签（中文）：<input type="text" name="tags_cn" value="${location.tags_cn }" style="width:540px"><br/>
		位置标签（英文）：<input type="text" name="tags_en" value="${location.tags_en }" style="width:540px"><br/>
		
		位置描述（中文）：<textarea rows="5" cols="6" name="description_cn" style="width:544px"><c:out value="${location.description_cn}"/></textarea><br/>
		位置描述（英文）：<textarea rows="5" cols="6" name="description_en" style="width:544px"><c:out value="${location.description_en}"/></textarea><br/>
		
		<img src="" alt="" id="location_header_image" style="width:100px;height: 100px;"/><br/>
		<span id="show_location_images">查看图片</span>  <span id="show_locationExt_infor">查看扩展信息</span>
		<input type="submit" value="保存">
	</form:form> 
		</div>
		
	</div>
</div>
<!-- 查看所有的扩展信息或者添加扩展信息。 -->
<div id="dialog_locationExt_infor">
	<div>
		<c:if test="${!empty fileDatas}">
		
		</c:if>
		<c:if test="${empty fileDatas}">
			<span>没有相关扩展信息</span>
		</c:if>
	</div>
</div>

<!-- 位置图片处理 -->
<div id="dialog_location_images">
	<div>
		<c:if test="${!empty fileDatas}">
			<c:forEach var="locImage" items="${fileDatas}">
				<li>
					<a href="#"><c:out value="${locImage.filePath}"/></a>
				</li>
			</c:forEach>
		</c:if>
		<c:if test="${empty fileDatas}">
			<span>没有相关图片信息</span>
		</c:if>
	</div>
</div>