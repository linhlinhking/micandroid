<script type="text/javascript">
	$(function() {
		$('#departmentId').combobox({
			url:'department/query_all',  
		    valueField:'id',  
		    textField:'name', 
		    panelHeight:'auto',
		    onChange:function(newValue,oldValue){
		    	setRoom(newValue);
		    }
		});
		$('#roomId').combobox({
		    valueField:'id',  
		    textField:'name', 
		    panelHeight:'auto',
		});
		$('#groupId').combobox({
		    valueField:'id',  
		    textField:'name', 
		    panelHeight:'auto',
		});
	});
	$(function(){
		getTableData('equipment/query_list');
	});
	function getTableData(linkUrl) {
		$('#equipment_view').datagrid({
			title:'',  
		    width:'auto',  
		    height:'auto',  
		    remoteSort:false,  
		    singleSelect:true,  
		    nowrap:false,  
		    fitColumns:false, 
		    pageList:[10, 20, 40, 50],
		    url:linkUrl,  
		    sortName: 'name',
			sortOrder: 'asc',
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{title:'ID',field:'id',width:50,sortable:true,hidden:true}
			]],
			columns:[[
				{field:'name',title:'Name',width:120,rowspan:2,resizable:false},
				{field:'owner',title:'Owner',width:60,rowspan:2,resizable:false},
				{field:'location',title:'Location',width:80,rowspan:4,resizable:false},
				{field:'description',title:'Description',width:120,rowspan:4,resizable:false},
				{field:'group',title:'Group',width:120,rowspan:4,resizable:false},
				{field:'room',title:'Room',width:120,rowspan:4,resizable:false},
				{field:'department',title:'Department',width:120,rowspan:4,resizable:false},
				{field:'grossPower',title:'GrossPower(W)',width:100,rowspan:2,resizable:false},
				{field:'strStatus',title:'Status',width:60,rowspan:2,resizable:false},
				{field:'reserver',title:'Reserver',width:80,rowspan:2,resizable:false},
				{field:'reserverTo',title:'Reserve_EndTime',width:150,rowspan:2,resizable:false},
				{field:'usage',title:'Usage(%)',width:80,rowspan:2,resizable:false}
			]],
			pagination:true,
			rownumbers:true,
			onLoadSuccess:function(){  
                $('#equipment_view').datagrid('clearSelections');
            }  
		});
	}
</script>
<div region="north" border="false">
	<br/>
	<form id="equipmentForm" method="post">
	<table>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Department : </a></td>
			<td>
				<select class="easyui-combobox" style="width:204px;" id="departmentId" name="departmentId"></select>
			</td>
			<td><a href="#" class="easyui-linkbutton" plain="true">Room/Lab : </a></td>
			<td>
				<select class="easyui-combobox" id="roomId" name="roomId" style="width:204px;"></select>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Group : </a></td>
			<td>
				<select class="easyui-combobox" id="groupId" name="groupId" style="width:204px;"> </select>

			</td>
			<td><a href="#" class="easyui-linkbutton" plain="true">Status : </a></td>
			<td>
				<select id="status" class="easyui-combobox" name="status" style="width:204px;" required="true" panelHeight="auto">
					<option value="0" selected="selected">Idle</option>
					<option value="1">Reserved</option>
				</select>

			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Name : </a></td>
			<td>
				<input type="text" id="name" name="name" style="width:200px;" onkeypress="if(event.keyCode==13){submitEQuery();return false;}">
			</td>
			<td><a href="#" class="easyui-linkbutton" plain="true">Owner : </a></td>
			<td>
				<input type="text" id="owner" name="owner" style="width:200px;" onkeypress="if(event.keyCode==13){submitEQuery();return false;}">
			</td>
		</tr>
		<tr>
			<td></td>
			<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="submitEQuery()">Search</a></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	</form>
	<br/>
</div>
<div region="center" border="false">
	<div id="equipment" class="easyui-tabs" fit="false" border="false" plain="true">
		<div title="Equipment Information">
			<table id="equipment_view" toolbar="#toolbar_equipment"></table>
			<div id="toolbar_equipment">  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addEquipment();">Add</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editEquipment();">Edit</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delEquipment();">Delete</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="reserveEquipment();">Reserve</a>  
			</div>
			<div id="addEquipment" class="easyui-window" closed="true"></div>
			<div id="editEquipment" class="easyui-window" closed="true"></div>
		</div>
	</div>
</div>
<form id="delEquipmentForm" method="post"></form>
<script type="text/javascript">
		function submitEQuery(){
			$('#equipmentForm').form('submit',{
				url:'equipment/equipment_form',
				success:function(linkUrl){
					linkUrl = linkUrl.replace(new RegExp("&amp;","gm"),"&");
					getTableData(linkUrl);
				}
			});
		}
		function addEquipment() {
			var $win;
		    $win = $('#addEquipment').window({
		            title:'Add Equipment',
		            width: 550,
		            height: 350,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-add',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'equipment/equipment_add'
		         });
		   
		    $win.window('open');
		}
		function editEquipment() {
			var row = $('#equipment_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			var $win;
		    $win = $('#editEquipment').window({
		            title:'Edit Equipment',
		            width: 550,
		            height: 380,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-edit',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'equipment/equipment_edit?id='+row.id
		         });
		   
		    $win.window('open');
		}
		function delEquipment() {
			var row = $('#equipment_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to delete the [' + row.id + '] '+ row.name + ' Equipment?',function(f){  
				    if (f){  
				    	$('#delEquipmentForm').form('submit',{
							url:'equipment/equipment_del?id='+row.id,
							success:function(msg){
								$.messager.alert('Info',msg); 
								getTableData('equipment/query_list');
							}
						}); 
				    }  
				});
			}  
		}
		function reserveEquipment() {
			var row = $('#equipment_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			setCenter('Reserve', 'equipment/equipment_reserve?equipmentId='+row.id)
		}
		function setRoom(departmentId) {
			$('#roomId').combobox({
				url:'room/query_union?departmentId=' + departmentId,  
			    onChange:function(newValue,oldValue){
			    	setGroup(newValue);
			    }
			});
		}
		function setGroup(roomId) {
			$('#groupId').combobox({
				url:'group/query_union?roomId=' + roomId,  
			});
		}
</script>
