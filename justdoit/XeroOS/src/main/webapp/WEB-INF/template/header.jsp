<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<div class="header login_header">
	<a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a> <span>Global
		Design &amp; Production</span>
	<ul>
		<li><a href="/logout">Sign Out</a></li>
		<li><a href="/user">Manage Users</a></li>
		<li>
			<c:if test="${planId == 1 || planId == null}">
				<a href="/payment">${leftDays} Days Remaining</a>
			</c:if> 
			<c:if test="${planId != 1}">
				<a href="/payment">My Account</a>
			</c:if>
		</li>
	</ul>
	
	<input type="hidden" name="current_company_id" value="${companyId}"/>
    <input type="hidden" name="current_planId" value="${planId}"/>
    <input type="hidden" name="isLinkXero" value="${isLinkXero}"/>
    <input type="hidden" name="currency_user_id" value="${currency_user_id}">
</div>
