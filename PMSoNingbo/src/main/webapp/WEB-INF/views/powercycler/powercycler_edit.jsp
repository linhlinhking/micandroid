<%@page import="com.cisco.pmonitor.dao.dataobject.*, java.util.*"%>
<%
	PowerCyclerDo powerCyclerDo = (PowerCyclerDo)request.getAttribute("powerCyclerDo");
%>
<script type="text/javascript">
	function doPowerCyclerEdit(){
		$('#powercyclerEditForm').form('submit',{
			url:'powercycler/powercycler_edit',
			onSubmit: function(){ 
			},
			success:function(msg){
				$.messager.alert('Info',msg); 
				getPowerclyerData('powercycler/query_list');
				$('#editPowercycler').window('close');
			}
		});
	}
	function doPowerCyclerEditClose() {
		$('#editPowercycler').window('close');
	}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="powercyclerEditForm" method="post">
		<input type="hidden" id="id" name="id" value="<%=powerCyclerDo.getId()%>">
		<table>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Name:</a></td>
				<td>
					<input id="name" name="name" required="true" style="width:200px;" value="<%=powerCyclerDo.getName()%>">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Type : </a></td>
				<td>
					<select id="type" class="easyui-combobox" name="type" style="width:204px;" required="true" panelHeight="auto">
						<option value="1" <%if(powerCyclerDo.getType() == 1){ %>selected="selected"<%} %>>DTR</option>
						<option value="2" <%if(powerCyclerDo.getType() == 2){ %>selected="selected"<%} %>>DualComm</option>
						<option value="3" <%if(powerCyclerDo.getType() == 3){ %>selected="selected"<%} %>>APC</option>
						<option value="4" <%if(powerCyclerDo.getType() == 4){ %>selected="selected"<%} %>>uBR10kLC</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Protocol : </a></td>
				<td>
					<select id="protocol" class="easyui-combobox" name="protocol" style="width:204px;" required="true" panelHeight="auto">
						<option value="1" <%if(powerCyclerDo.getProtocol() == 1){ %>selected="selected"<%} %>>Snmp</option>
						<option value="2" <%if(powerCyclerDo.getProtocol() == 2){ %>selected="selected"<%} %>>Telnet</option>
						<option value="3" <%if(powerCyclerDo.getProtocol() == 3){ %>selected="selected"<%} %>>Socket</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Status : </a></td>
				<td>
					<select id="status" class="easyui-combobox" name="status" style="width:204px;" required="true" panelHeight="auto">
						<option value="0" <%if(powerCyclerDo.getStatus() == 0){ %>selected="selected"<%} %>>Off</option>
						<option value="1" <%if(powerCyclerDo.getStatus() == 1){ %>selected="selected"<%} %>>On</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">HostAddress:</a></td>
				<td>
					<input id="host" name="host" required="true" style="width:200px;" value="<%=powerCyclerDo.getHost()%>">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">LoginUser:</a></td>
				<td>
					<input id="loginUser" name="loginUser" required="true" style="width:200px;" value="<%=powerCyclerDo.getLoginUser()%>">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">LoginPwd:</a></td>
				<td>
					<input id="loginPwd" name="loginPwd" required="true" style="width:200px;" value="<%=powerCyclerDo.getLoginPwd()%>">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">EnablePwd:</a></td>
				<td>
					<input id="enablePwd" name="enablePwd" required="true" style="width:200px;" value="<%=powerCyclerDo.getEnablePwd()%>">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Outlet/line:</a></td>
				<td>
					<span style="font-weight:bold;color:green">&nbsp;From&nbsp;</span>
					<input id="from" name="from" required="true" style="width:60px;" value="<%=powerCyclerDo.getFrom()%>">
					<span style="font-weight:bold;color:red">&nbsp;To&nbsp;</span>
					<input id="to" name="to" required="true" style="width:60px;" value="<%=powerCyclerDo.getTo()%>">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">PowerStatus:</a></td>
				<td>
					<span style="font-weight:bold;color:green">&nbsp;On&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input id="on" name="on" required="true" style="width:60px;" value="<%=powerCyclerDo.getOn()%>">
					<span style="font-weight:bold;color:red">&nbsp;Off&nbsp;</span>
					<input id="off" name="off" required="true" style="width:59px;" value="<%=powerCyclerDo.getOff()%>">
				</td>
			</tr>
		</table>
		</form>
	</div>	
	<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doPowerCyclerEdit();">Ok&nbsp;</a>
		<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" href="javascript:void(0)" onclick="doPowerCyclerEditClose();">Cancel</a>
	</div>	
</div>
