<script type="text/javascript">
	$(function(){
		getPowerclyerData('powercycler/query_list');
	});
	function getPowerclyerData(linkUrl) {
		$('#powercycler_view').datagrid({
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
				{field:'strType',title:'Type',width:80,rowspan:2,resizable:false},
				{field:'strProtocol',title:'Protocol',width:80,rowspan:2,resizable:false},
				{field:'host',title:'HostAddress',width:180,rowspan:4,resizable:false},
				{field:'strStatus',title:'Status',width:120,rowspan:4,resizable:false,align:'center'},
				{field:'loginUser',title:'LoginUser',width:100,rowspan:4,resizable:false},
				{field:'loginPwd',title:'LoginPwd',width:100,rowspan:4,resizable:false},
				{field:'enablePwd',title:'EnablePwd',width:100,rowspan:4,resizable:false},
				{field:'outlet',title:'Outlet/Line',width:100,rowspan:2,resizable:false}
			]],
			pagination:true,
			rownumbers:true,
			onLoadSuccess:function(){  
	            $('#powercycler_view').datagrid('clearSelections');
	        }  
		});
	}	
</script>
<div region="north" border="false">
	<form id="powercyclerForm" method="post">
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
					<option value="1" selected="selected">On</option>
					<option value="0">Off</option>
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
			<td></td>
			<td>
				<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doPowerCyclerSearch();">Search</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" plain="true" iconCls="icon-back" href="javascript:void(0)" onclick="setCenter('PowerMonitor', 'monitor/monitor_view')">Back</a>
			</td>
		</tr>
	</table>
	</form>
</div>		
<div region="center" border="false">
	<div id="powercycler" class="easyui-tabs" fit="false" border="false" plain="true">
		<div title="PowerCycler">
			<table id="powercycler_view" toolbar="#toolbar_powercycler"></table>
			<div id="toolbar_powercycler">  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addPowercycler();">Add</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPowercycler();">Edit</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delPowercycler();">Delete</a>  
			</div>
			<div id="addPowercycler" class="easyui-window" closed="true"></div>
			<div id="editPowercycler" class="easyui-window" closed="true"></div>
		</div>
	</div>
</div>
<form id="delPowercyclerForm" method="post"></form>
<script type="text/javascript">
	function doPowerCyclerSearch(){
		$('#powercyclerForm').form('submit',{
			url:'powercycler/powercycler_form',
			success:function(linkUrl){
				linkUrl = linkUrl.replace(new RegExp("&amp;","gm"),"&");
				getPowerclyerData(linkUrl);
			}
		});
	}
	function addPowercycler() {
		var $win;
	    $win = $('#addPowercycler').window({
	            title:'Add PowerCycler',
	            width: 500,
	            height: 400,
	            shadow: true,
	            modal:true,
	            iconCls:'icon-add',
			    closed:true,
			    cache:false,
			    minimizable:false,
			    maximizable:false,
			    collapsible:false,
			    href:'powercycler/powercycler_add'
	         });
	   
	    $win.window('open');
	}
	function editPowercycler() {
		var row = $('#powercycler_view').datagrid('getSelected');
		if(null==row) {
			$.messager.alert('Warning','You must select a row firstly.'); 
			return;
		}
		var $win;
	    $win = $('#editPowercycler').window({
	            title:'Edit PowerCycler',
	            width: 500,
	            height: 400,
	            shadow: true,
	            modal:true,
	            iconCls:'icon-edit',
			    closed:true,
			    cache:false,
			    minimizable:false,
			    maximizable:false,
			    collapsible:false,
			    href:'powercycler/powercycler_edit?id=' + row.id
	         });
	   
	    $win.window('open');
	}
	
	function delPowercycler() {
		var row = $('#powercycler_view').datagrid('getSelected');
		if(null==row) {
			$.messager.alert('Warning','You must select a row firstly.'); 
			return;
		}
		if (row){
			$.messager.confirm('Confirm','Are you sure you want to delete the [' + row.id + '] '+ row.name + ' PowerCycler?',function(f){  
			    if (f){  
			    	$('#delPowercyclerForm').form('submit',{
						url:'powercycler/powercycler_del?id='+row.id,
						success:function(msg){
							$.messager.alert('Info',msg); 
							getPowerclyerData('powercycler/query_list');
						}
					}); 
			    }  
			});
		}  
	}
</script>