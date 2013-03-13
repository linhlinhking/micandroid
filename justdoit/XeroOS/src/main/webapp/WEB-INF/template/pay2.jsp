<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
	<tr>
		<td colspan="2" align="center"><b>Input</b></td>
	</tr>
	<tr>
		<td>Amount</td>
		<td><input type="text" name="amount" value="1.00" /></td>
	</tr>
	<tr>
		<td>Billing ID</td>
		<td><input type="text" name="billingId" /></td>
	</tr>
	<tr>
		<td>Currency</td>
		<td><input type="text" name="currencyInput" value="USD" /></td>
	</tr>
	<tr>
		<td>Email Address</td>
		<td><input type="text" name="emailAddress" /></td>
	</tr>
	<tr>
		<td>Enable Add Bill Card</td>
		<td><input type="text" name="enableAddBillCard" /></td>
	</tr>
	<tr>
		<td>Merchant Reference</td>
		<td><input type="text" name="merchantReference"
			value="my merchant ref" /></td>
	</tr>
	<tr>
		<td>TxnData 1</td>
		<td><input type="text" name="txnData1" /></td>
	</tr>
	<tr>
		<td>TxnData 2</td>
		<td><input type="text" name="txnData2" /></td>
	</tr>
	<tr>
		<td>TxnData 3</td>
		<td><input type="text" name="txnData3" /></td>
	</tr>
	<tr>
		<td>Txn ID</td>
		<td><input type="text" name="txnId" /></td>
	</tr>
	<tr>
		<td>Transaction Type</td>
		<td><input type="text" name="txnType" value="Purchase" /></td>
	</tr>
	<tr>
		<td>Options</td>
		<td><input type="text" name="opt" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="button" id="payBtn" value="submit"/></td>
	</tr>
</table>

</body>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/common/base.js"></script>
<script type="text/javascript" src="/js/payment.js"></script>
</html>