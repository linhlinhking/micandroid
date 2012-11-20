<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div id="content">
	
	<div class="sel_category_rightnow">
		<div id="rightnow">
			<c:if test="${!empty firstCategory}">
				<c:forEach var="first" items="${firstCategory }">
					<span id="${first.id}">
						<a href="/admin/location/first-category/${first.id}">
							<c:out value="${first.name_cn}"/>
						</a>
					</span>
				</c:forEach>
			</c:if>
			<c:if test="${empty firstCategory}">
				<span>没有数据.</span>
			</c:if>
		</div>
		
		<div id="secondRigtnow">
		</div>
	</div>
	
</div>