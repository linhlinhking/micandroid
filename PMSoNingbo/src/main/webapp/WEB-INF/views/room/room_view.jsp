<script type="text/javascript">
$(function(){
	getRoomData('room/query_list');
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
});
function setRoom(departmentId) {
	$('#roomId').combobox({
		url:'room/query_union?departmentId=' + departmentId,  
	});
}
function getRoomData(linkUrl) {
	$('#room_view').datagrid({
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
			{field:'name',title:'Name',width:180,rowspan:2,resizable:false},
			{field:'owner',title:'Administrator',width:100,rowspan:2,resizable:false},
			{field:'location',title:'Location',width:150,rowspan:4,resizable:false},
			{field:'department',title:'Department',width:150,rowspan:4,resizable:false},
			{field:'description',title:'Description',width:200,rowspan:2,resizable:false},
		]],
		pagination:true,
		rownumbers:true,
		onLoadSuccess:function(){  
            $('#room_view').datagrid('clearSelections');
        }  
	});
}
</script>
<div region="north" border="false">
	<br/>
	<form id="roomForm" method="post">
	<table>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Department : </a></td>
			<td>
				<select class="easyui-combobox" style="width:204px;" id="departmentId" name="departmentId"></select>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Room : </a></td>
			<td>
				<select class="easyui-combobox" style="width:204px;" id="roomId" name="roomId"></select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="submitRQuery()">Search</a></td>
		</tr>
	</table>
	</form>
	<br/>
</div>
<div region="center" border="false">
	<div id="room" class="easyui-tabs" fit="false" border="false" plain="true">
		<div title="Room/Lab Information">
			<table id="room_view" toolbar="#toolbar_room"></table>
			<div id="toolbar_room">  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRoom();">Add</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRoom();">Edit</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRoom();">Delete</a>  
				<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="setCenter('Department', 'department/department_view');">Department</a>
			</div>
			<div id="addRoom" class="easyui-window" closed="true"></div>
			<div id="editRoom" class="easyui-window" closed="true"></div>
		</div>
	</div>
</div>
<form id="delRoomForm" method="post"></form>
<script type="text/javascript">
		function submitRQuery(){
			$('#roomForm').form('submit',{
				url:'room/room_form',
				success:function(linkUrl){
					linkUrl = linkUrl.replace(new RegExp("&amp;","gm"),"&");
					getRoomData(linkUrl);
				}
			});
		}
		function addRoom() {
			var $win;
		    $win = $('#addRoom').window({
		            title:'Add Room',
		            width: 500,
		            height: 300,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-add',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'room/room_add'
		         });
		   
		    $win.window('open');
		}
		function editRoom() {
			var row = $('#room_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			var $win;
		    $win = $('#editRoom').window({
		            title:'Edit Room',
		            width: 500,
		            height: 300,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-edit',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'room/room_edit?id='+row.id
		         });
		   
		    $win.window('open');
		}
		function delRoom() {
			var row = $('#room_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to delete the [' + row.id + '] '+ row.name + ' Room?',function(f){  
				    if (f){  
				    	$('#delRoomForm').form('submit',{
							url:'room/room_del?id='+row.id,
							success:function(msg){
								$.messager.alert('Info',msg); 
								getRoomData('room/query_list');
							}
						}); 
				    }  
				});
			}  
		}
</script>