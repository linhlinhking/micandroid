<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div id="content">
	
	<div class="sel_category_rightnow">
		<div id="rightnow">
			<c:if test="${!empty firstCategoryList}">
				<c:forEach var="first" items="${firstCategoryList}">
				 	<c:if test="${first.id == currentFirstId}">
				 		<span class='hover_span' id="<c:out value='${first.id}'/>" >
				 			<a href="/admin/location/first-category/${first.id}"><c:out value="${first.name_cn}"/></a>
				 		</span>
				 	</c:if>
				 	<c:if test="${first.id != currentFirstId}">
				 		<span id="<c:out value='${first.id}'/>" >
				 			<a href="/admin/location/first-category/${first.id}"><c:out value="${first.name_cn}"/></a>
				 		</span>
				 	</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty firstCategoryList}">
				<span>没有数据.</span>
			</c:if>
		</div>
		<div id="secondRigtnow">
			<c:if test="${!empty secondList}">
				<c:forEach var="second" items="${secondList}">
					<c:if test="${second.id == secondId}">
				 		<span class='click_span' id="<c:out value='${second.id}'/>" ><a href="/admin/location/first-category/${currentFirstId}/second-category/${secondId}/${currentPage}"><c:out value="${second.name_cn}"/></a></span>
				 	
				 	</c:if>
				 	<c:if test="${second.id != secondId}">
				 		<span id="<c:out value='${second.id}'/>" ><a href="/admin/location/first-category/${currentFirstId}/second-category/${second.id}/${currentPage}"><c:out value="${second.name_cn}"/></a></span>
				 	
				 	</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty secondList}">
				<span>没有数据.</span>
			</c:if>
		</div>
	</div>
	
	<div class="location_count">
		<input id="locations_total" type="hidden" value="${total}" name="locations_total">
		<input id="locations_first_id" type="hidden" value="${currentFirstId}" name="current_first_id">
		<input id="locations_second_id" type="hidden" value="${secondId}" name="current_second_id">
		<input id="locations_current_page" type="hidden" value="${currentPage}" name="location_current_page">
		<input id="locations_total_page" type="hidden" value="${totalPage}" name="location_total_page">
		
		  <ul>
		  	<li><a href="#content_location">位置信息列表</a></li>
		  </ul>
		  <div class="total_loc" id="content_location">
		  	 <c:if test="${!empty locationList}">
		  	 	<c:forEach var="location" items="${locationList}">
		  	 	<div class="location">
			     <div class="parts">
				    <div class="border">
				    	<div class="location_img">
							<img src="" class="img_photo_path" id="${location.photo_path }"/>
						</div>
				         <p>
				         <a class="edit_${location.id}" id="${location.md5Value }" href="/admin/location/edit/${location.id}">编辑</a>  
				         <span id="delete_span"> <a class="delete_${location.id}" id="${location.md5Value}" href="javascript:void(0);">删除</a></span>
				         </p>
				     </div>
			      </div>
			      <div class="parts margin">
			        <p>名称：${location.name_cn }</p>
			        <p>地址：${location.address_cn }</p>
			        <p>关键字：${location.tags_cn }</p>
			        <p>电话号码：${location.telephone }&nbsp;&nbsp;&nbsp;&nbsp;</p>
			      </div>
		   		</div>
		   		</c:forEach>
		  	 </c:if>
		  	 <c:if test="${empty locationList}">
		  	 	<div class="location">没有位置数据.</div>
		  	 </c:if>
		  
		  </div>
		  <div id="location_pagable"><!-- 这里显示分页 -->
		  	
		  </div>
  </div>
</div>

	
	





