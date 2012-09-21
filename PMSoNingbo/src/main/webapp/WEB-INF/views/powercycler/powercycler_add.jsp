<script type="text/javascript">
	function doPowerCyclerAdd(){
		$('#powercyclerAddForm').form('submit',{
			url:'powercycler/powercycler_add',
			onSubmit: function(){ 
			},
			success:function(msg){
				$.messager.alert('Info',msg); 
				getPowerclyerData('powercycler/query_list');
				$('#addPowercycler').window('close');
			}
		});
	}
	function doPowerCyclerAddClose() {
		$('#addPowercycler').window('close');
	}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="powercyclerAddForm" method="post">
		<table>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Name:</a></td>
				<td>
					<input id="name" name="name" required="true" style="width:200px;">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Type : </a></td>
				<td>
					<select id="type" class="easyui-combobox" name="type" style="width:204px;" required="true" panelHeight="auto">
						<option value="1" selected="selected">DTR</option>
						<option value="2">DualComm</option>
						<option value="3">APC</option>
						<option value="4">uBR10kLC</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Protocol : </a></td>
				<td>
					<select id="protocol" class="easyui-combobox" name="protocol" style="width:204px;" required="true" panelHeight="auto">
						<option value="1" selected="selected">Snmp</option>
						<option value="2">Telnet</option>
						<option value="3">Socket</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Status : </a></td>
				<td>
					<select id="status" class="easyui-combobox" name="status" style="width:204px;" required="true" panelHeight="auto">
						<option value="0" selected="selected">Off</option>
						<option value="1">On</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">HostAddress:</a></td>
				<td>
					<input id="host" name="host" required="true" style="width:200px;">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">LoginUser:</a></td>
				<td>
					<input id="loginUser" name="loginUser" required="true" style="width:200px;">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">LoginPwd:</a></td>
				<td>
					<input id="loginPwd" name="loginPwd" required="true" style="width:200px;">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">EnablePwd:</a></td>
				<td>
					<input id="enablePwd" name="enablePwd" required="true" style="width:200px;">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Outlet/line:</a></td>
				<td>
					<span style="font-weight:bold;color:green">&nbsp;From&nbsp;</span>
					<input id="from" name="from" required="true" style="width:60px;">
					<span style="font-weight:bold;color:red">&nbsp;To&nbsp;</span>
					<input id="to" name="to" required="true" style="width:60px;">
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">PowerStatus:</a></td>
				<td>
					<span style="font-weight:bold;color:green">&nbsp;On&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input id="on" name="on" required="true" style="width:60px;">
					<span style="font-weight:bold;color:red">&nbsp;Off&nbsp;</span>
					<input id="off" name="off" required="true" style="width:59px;">
				</td>
			</tr>
		</table>
		</form>
	</div>	
	<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doPowerCyclerAdd();">Ok&nbsp;</a>
		<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" href="javascript:void(0)" onclick="doPowerCyclerAddClose();">Cancel</a>
	</div>	
</div>
