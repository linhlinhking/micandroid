<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
</head>
<body>

<table>
	<tr>
		<td colspan="2" align="center"><b>Input</b></td>
	</tr>
	<tr>
		<td>Amount</td>
		<td><input type="text" name="Amount" value="1.00" /></td>
	</tr>
	<tr>
		<td>CardHolderName</td>
		<td><input type="text" name="CardHolderName" value="A Anderson"/></td>
	</tr>
	<tr>
		<td>CardNumber</td>
		<td><input type="text" name="CardNumber" value="4111111111111111"/></td>
	</tr>
	
	<tr>
		<td>MerchantReference</td>
		<td><input type="text" name="MerchantReference" value="Test Transaction"/></td>
	</tr>
	
	<tr>
		<td>DateExpiry</td>
		<td><input type="text" name="DateExpiry" value="1010"/></td>
	</tr>
	
	<tr>
		<td>Cvc2</td>
		<td><input type="text" name="Cvc2" value="3456"/></td>
	</tr>
	
	<tr>
		<td>Cvc2Presence</td>
		<td><input type="text" name="Cvc2Presence" value="1"/></td>
	</tr>
	<tr>
		<td>CurrencyInput</td>
		<td><input type="text" name="CurrencyInput" value="NZD"/></td>
	</tr>
	<tr>
		<td>TxnId</td>
		<td><input type="text" name="TxnId" value="inv1278"/></td>
	</tr>
	<tr>
		<td>TxnType</td>
		<td><input type="text" name="TxnType" value="Purchase"/></td>
	</tr>
	
	<tr>
		<td>EnableAddBillingCard</td>
		<td><input type="text" name="EnableBillingCard" value="1"/></td>
	</tr>
	<tr>
		<td>EnableAvsData</td>
		<td><input type="text" name="EnableAvsData" value="1"/></td>
	</tr>
	<tr>
		<td>AvsAction</td>
		<td><input type="text" name="AvsAction" value="1"/></td>
	</tr>
	<tr>
		<td>AvsPostCode</td>
		<td><input type="text" name="AvsPostCode" value="1"/></td>
	</tr>
	<tr>
		<td>AvsStreetAddress</td>
		<td><input type="text" name="AvsStreetAddress" value="1"/></td>
	</tr>
	
	<tr>
		<td>StartDate</td>
		<td><input type="text" name="StartDate" /></td>
	</tr>
	<tr>
		<td>IssueNumber</td>
		<td><input type="text" name="IssueNumber" /></td>
	</tr>
	
	<tr>
		<td>UserId</td>
		<td><input type="text" name="currency_user_id" value="1"/></td>
	</tr>
	
	<tr>
		<td>CompanyId</td>
		<td><input type="text" name="current_company_id" value="1"/></td>
	</tr>

	<tr>
		<td></td>
		<td><input type="button" id="payBtn" value="submit"/></td>
	</tr>
</table>

</body>
<script type="text/javascript" src="/js/common/base.js"></script>
<script type="text/javascript" src="/js/payment.js"></script>
</html>