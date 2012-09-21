<script type="text/javascript">
	$(function(){
		getTableData('user/query_list');
	});
	function getTableData(linkUrl) {
		$('#user_view').datagrid({
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
				{field:'username',title:'UserName',width:120,rowspan:2,resizable:false},
				{field:'fullName',title:'Full Name',width:100,rowspan:2,resizable:false},
				{field:'email',title:'Email',width:240,rowspan:4,resizable:false},
				{field:'roleView',title:'Role',width:240,rowspan:4,resizable:false},
				{field:'gmtCreate',title:'Time',width:130,rowspan:2,resizable:false}
			]],
			pagination:true,
			rownumbers:true,
			onLoadSuccess:function(){  
                $('#user_view').datagrid('clearSelections');
            }  
		});
	}
</script>
<div region="north" border="false">
	<br/>
	<form id="userForm" method="post">
	<table>
		<tr>
			<td></td>
			<td><a href="#" class="easyui-linkbutton" plain="true">Username : </a></td>
			<td>
				<input type="text" id="username" name="username" style="width:200px;" onkeypress="if(event.keyCode==13){submitQuery();return false;}"></td>
			</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td><a href="#" class="easyui-linkbutton" plain="true">Role : </a></td>
			<td>
				<select class="easyui-combobox" id="role" name="role" style="width:204px;">
					<option value="0" selected="selected">User</option>
					<option value="1">Administrator</option>
				</select>
			</td>
			<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="submitQuery()">Search</a></td>
		</tr>
	</table>
	</form>
	<br/>
</div>
<div region="center" border="false">
	<div id="user" class="easyui-tabs" fit="false" border="false" plain="true">
		<div title="User Information">
			<table id="user_view" toolbar="#toolbar_user"></table>
			<div id="toolbar_user">  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:addUser();">Add</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editUser();">Edit</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:delUser();">Delete</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="javascript:assignGroup();">Assign Group</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="javascript:manageGroup();">Manager Group</a>  
			</div>
			<div id="addUser" class="easyui-window" closed="true"></div>
			<div id="editUser" class="easyui-window" closed="true"></div>
			<div id="delUser" class="easyui-window" closed="true"></div>
			<div id="assignGroup" class="easyui-window" closed="true"></div>
			<div id="manageGroup" class="easyui-window" closed="true"></div>
		</div>
	</div>
</div>
<form id="delUserForm" method="post"></form>
<input type="hidden" id="privilege" name="privilege" value="<%=request.getAttribute("privilege")%>"/>
<script type="text/javascript">
		function submitQuery(){
			$('#userForm').form('submit',{
				url:'user/user_form',
				success:function(linkUrl){
					linkUrl = linkUrl.replace('&amp;', '&');
					getTableData(linkUrl);
				}
			});
		}
		function addUser() {
			var privilege = document.getElementById('privilege').value;
			if(null == privilege || privilege == '' || privilege < 2) {
				$.messager.alert('Warning','You don\'t have privilege to do this.'); 
				return;
			}
			var $win;
		    $win = $('#addUser').window({
		            title:'Add User',
		            width: 400,
		            height: 250,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-add',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'user/user_add'
		         });
		   
		    $win.window('open');
		}
		function editUser() {
			var privilege = document.getElementById('privilege').value;
			if(null == privilege || privilege == '' || privilege < 2) {
				$.messager.alert('Warning','You don\'t have privilege to do this.'); 
				return;
			}
			var row = $('#user_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			var $win;
		    $win = $('#editUser').window({
		            title:'Edit User',
		            width: 400,
		            height: 250,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-edit',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'user/user_edit?id='+row.id
		         });
		   
		    $win.window('open');
		}
		function delUser() {
			var privilege = document.getElementById('privilege').value;
			if(null == privilege || privilege == '' || privilege < 2) {
				$.messager.alert('Warning','You don\'t have privilege to do this.'); 
				return;
			}
			var row = $('#user_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to delete the [' + row.id + '] '+ row.username + ' user?',function(f){  
				    if (f){  
				    	$('#delUserForm').form('submit',{
							url:'user/user_del?id='+row.id,
							success:function(msg){
								$.messager.alert('Info',msg); 
								getTableData('user/query_list');
							}
						}); 
				    }  
				});
			}  
		}
		function assignGroup() {
			var privilege = document.getElementById('privilege').value;
			if(null == privilege || privilege == '' || privilege < 1) {
				$.messager.alert('Warning','You don\'t have privilege to do this.'); 
				return;
			}
			var row = $('#user_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			var $win;
		    $win = $('#assignGroup').window({
		            title:'assign group for user',
		            width: 400,
		            height: 200,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-ok',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'user/user_assign?username='+row.username
		         });
		   
		    $win.window('open');
		}
		
		function manageGroup() {
			var privilege = document.getElementById('privilege').value;
			if(null == privilege || privilege == '' || privilege < 0) {
				$.messager.alert('Warning','You don\'t have privilege to do this.'); 
				return;
			}
			var row = $('#user_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			var $win;
		    $win = $('#manageGroup').window({
		            title:'manage group for user',
		            width: 400,
		            height: 200,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-ok',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'user/manage_group?username='+row.username
		         });
		   
		    $win.window('open');
		}
</script>
