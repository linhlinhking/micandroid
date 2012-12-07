<%@ taglib uri="http://dev.globaldesign.co.nz/tags/c" prefix="c"%>
<c:if test="${sessionScope.XERO_USER_SESSION != null }">
	<c:set var="xeroUser" value="${sessionScope.XERO_USER_SESSION}"></c:set>
</c:if>