<script type="text/javascript">
$(function() {
	$('#departmentAdd').combobox({
		url:'department/query_all',  
	    valueField:'id',  
	    textField:'name', 
	    panelHeight:'auto',
	    onChange:function(newValue,oldValue){
	    	setRoom(newValue);
	    }
	});
	$('#roomAdd').combobox({
	    valueField:'id',  
	    textField:'name', 
	    panelHeight:'auto',
	});
	$('#groupAdd').combobox({
	    valueField:'id',  
	    textField:'name', 
	    panelHeight:'auto',
	});
});
function setRoom(departmentId) {
	$('#roomAdd').combobox({
		url:'room/query_union?departmentId=' + departmentId,  
	    onChange:function(newValue,oldValue){
	    	setGroup(newValue);
	    }
	});
}
function setGroup(roomId) {
	$('#groupAdd').combobox({
		url:'group/query_union?roomId=' + roomId,  
	});
}
function doEquipmentAdd(){
	$('#equipmentAddForm').form('submit',{
		url:'equipment/equipment_add',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getTableData('equipment/query_list');
			$('#addEquipment').window('close');
		}
	});
}
function closeEAddW() {
	$('#addEquipment').window('close');
}
</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="equipmentAddForm" method="post">
			<table>
				<tr>
					<td><label for="name">Name: </label></td>
					<td>
						<input id="name" name="name" required="true" style="width:200px;">
					</td>
				</tr>
				<tr>
					<td><label for="name">Owner: </label></td>
					<td>
						<input id="owner" name="owner" required="true" style="width:200px;">
					</td>
				</tr>
				<tr>
					<td><label for="name">Location: </label></td>
					<td>
						<input id="location" name="location" required="true" style="width:200px;">
					</td>
				</tr>
				<tr>
					<td><label for="name">GrossPower(W): </label></td>
					<td>
						<input id="grossPower" name="grossPower" required="true" style="width:200px;" value="0.0">
					</td>
				</tr>
				<tr>
					<td><label for="name">Department: </label></td>
					<td>
						<select class="easyui-combobox" style="width:204px;" id="departmentAdd" name="departmentId"></select>
					</td>
				</tr>
				<tr>
					<td><label for="name">Room/Lab: </label></td>
					<td>
						<select class="easyui-combobox" id="roomAdd" name="roomId" style="width:204px;"></select>
					</td>
				</tr>
				<tr>
					<td><label for="name">Group: </label></td>
					<td>
						<select class="easyui-combobox" id="groupAdd" name="groupId" style="width:204px;"> </select>
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
			<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doEquipmentAdd();">Ok</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeEAddW();">Cancel</a>
		</div>
</div>