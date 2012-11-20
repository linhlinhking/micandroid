<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div id="content">
	
	<div class="sel_category_rightnow">
		<div id="rightnow">
			<c:if test="${!empty firstList}">
				<c:forEach var="first" items="${firstList}">
					<c:if test="${first.id == currentFirstId}">
						<span class="hover_span" >
							<a href="/admin/location/first-category/${first.id}">
								<c:out value="${first.name_cn }"/>
							</a>
						</span>
					</c:if>
					<c:if test="${first.id != currentFirstId}">
						<span>
							<a href="/admin/location/first-category/${first.id}">
								<c:out value="${first.name_cn }"/>
							</a>
						</span>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty firstList}">
				<span>没有数据.</span>
			</c:if>
		</div>
		
		<div id="secondRigtnow">
			<c:if test="${!empty secondList}">
				<c:forEach var="second" items="${secondList}">
					<span id="<c:out value='${second.id}'/>">
					<a href="/admin/location/first-category/${currentFirstId}/second-category/${second.id}/${currentPage}">
					<c:out value="${second.name_cn}"/>
					</a></span>
				</c:forEach>
			</c:if>
			<c:if test="${empty secondList}">
				<span>没有数据.</span>
			</c:if>
		</div>
	</div>
</div>
	
	
