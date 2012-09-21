<script type="text/javascript">
$(function(){
	getDepartmentData('department/query_list');
	$('#departmentId').combobox({
		url:'department/query_all',  
	    valueField:'id',  
	    textField:'name', 
	    panelHeight:'auto',
	});
});
function getDepartmentData(linkUrl) {
	$('#department_view').datagrid({
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
			{field:'description',title:'Description',width:200,rowspan:2,resizable:false},
		]],
		pagination:true,
		rownumbers:true,
		onLoadSuccess:function(){  
            $('#department_view').datagrid('clearSelections');
        }  
	});
}
</script>
<div region="north" border="false">
	<br/>
	<form id="departmentForm" method="post">
	<table>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Department : </a></td>
			<td>
				<select class="easyui-combobox" style="width:204px;" id="departmentId" name="departmentId"></select>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="submitDQuery()">Search</a></td>
			<td><a class="easyui-linkbutton" plain="true" iconCls="icon-back" href="javascript:void(0)" onclick="setCenter('Room/Lab', 'room/room_view')">Back</a></td>
		</tr>
	</table>
	</form>
	<br/>
</div>
<div region="center" border="false">
	<div id="department" class="easyui-tabs" fit="false" border="false" plain="true">
		<div title="Department/Lab Information">
			<table id="department_view" toolbar="#toolbar_department"></table>
			<div id="toolbar_department">  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addDepartment();">Add</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDepartment();">Edit</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delDepartment();">Delete</a>  
			</div>
			<div id="addDepartment" class="easyui-window" closed="true"></div>
			<div id="editDepartment" class="easyui-window" closed="true"></div>
		</div>
	</div>
</div>
<form id="delDepartmentForm" method="post"></form>
<script type="text/javascript">
		function submitDQuery(){
			$('#departmentForm').form('submit',{
				url:'department/department_form',
				success:function(linkUrl){
					linkUrl = linkUrl.replace(new RegExp("&amp;","gm"),"&");
					getDepartmentData(linkUrl);
				}
			});
		}
		function addDepartment() {
			var $win;
		    $win = $('#addDepartment').window({
		            title:'Add Department',
		            width: 500,
		            height: 200,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-add',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'department/department_add'
		         });
		   
		    $win.window('open');
		}
		function editDepartment() {
			var row = $('#department_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			var $win;
		    $win = $('#editDepartment').window({
		            title:'Edit Department',
		            width: 500,
		            height: 200,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-edit',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'department/department_edit?id='+row.id
		         });
		   
		    $win.window('open');
		}
		function delDepartment() {
			var row = $('#department_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to delete the [' + row.id + '] '+ row.name + ' Department?',function(f){  
				    if (f){  
				    	$('#delDepartmentForm').form('submit',{
							url:'department/department_del?id='+row.id,
							success:function(msg){
								$.messager.alert('Info',msg); 
								getDepartmentData('department/query_list');
							}
						}); 
				    }  
				});
			}  
		}
</script>