<script type="text/javascript">
function doDepartmentAdd(){
	$('#departmentAddForm').form('submit',{
		url:'department/department_add',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getDepartmentData('department/query_list');
			$('#addDepartment').window('close');
		}
	});
}
function closeDAddW() {
	$('#addDepartment').window('close');
}
</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="departmentAddForm" method="post">
			<table>
				<tr>
					<td><label for="name">Name: </label></td>
					<td>
						<input id="name" name="name" required="true" style="width:200px;">
					</td>
				</tr>
				<tr>
					<td><label for="name">Description: </label></td>
					<td> <textarea rows="3" cols="40" id="description" name="description"></textarea> </td>
				</tr>
			</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doDepartmentAdd();">Ok</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeDAddW();">Cancel</a>
		</div>
</div>