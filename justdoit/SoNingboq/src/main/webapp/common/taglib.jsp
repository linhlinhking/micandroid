<%@ taglib uri="http://www.soningbo.com/tags/c" prefix="c"%>
<%@ taglib uri="http://www.soningbo.com/tags/spring" prefix="spring"%>
<%@ taglib uri="http://www.soningbo.com/tags/form" prefix="form"%>
<c:if test="${sessionScope.SONINGBO_USER_SESSION != null }">
	<c:set var="sonbUser" value="${sessionScope.SONINGBO_USER_SESSION}"></c:set>
</c:if>