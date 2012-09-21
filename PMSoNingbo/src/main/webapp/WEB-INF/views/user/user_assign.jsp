<script type="text/javascript">
function doUserAssign(){
	$('#assignGroupForm').form('submit',{
		url:'user/user_assign',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getTableData('user/query_list');
			$('#assignGroup').window('close');
		}
	});
}
function closeAddW() {
	$('#assignGroup').window('close');
}
</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="assignGroupForm" method="post">
			<input type="hidden" id="username" name="username" value="<%=request.getAttribute("username")%>">
			<table>
				<tr>
					<td><label>Group:</label></td>
					<td>
						<input class="easyui-combobox" 
							name="group"
							url="group/query_assign" 
							valueField="id" 
							textField="name" 
							style="width:204px;" multiple="true" panelHeight="auto">
					</td>
				</tr>
				<tr>
					<td><label>Role Type:</label></td>
					<td>
						<select id="role" class="easyui-combobox" name="role" style="width:204px;" required="true" panelHeight="auto">
							<option value="0" selected="selected">User</option>
							<option value="1">Administrator</option>
						</select>
					</td>
				</tr>
			</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doUserAssign();">Ok</a>
			<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAddW();">Cancel</a>
		</div>
</div>