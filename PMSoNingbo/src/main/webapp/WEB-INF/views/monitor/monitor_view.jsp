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
		$('#powercyclerId').combobox({
			url:'powercycler/query_all',  
		    valueField:'id',  
		    textField:'name', 
		    panelHeight:'auto',
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
		$('#equipmentId').combobox({
		    valueField:'id',  
		    textField:'name', 
		    panelHeight:'auto',
		});
	});
	$(function(){
		getMonitorData('monitor/query_list');
	});
	function getMonitorData(linkUrl) {
		$('#monitor_view').datagrid({
			title:'',  
		    width:'auto',  
		    height:'auto',  
		    remoteSort:false,  
		    singleSelect:false,  
		    nowrap:false,  
		    fitColumns:false, 
		    pageList:[10, 20, 40, 50],
		    url:linkUrl,  
		    sortName: 'id',
			sortOrder: 'asc',
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{title:'ID',field:'id',width:50,sortable:true,hidden:true}
			]],
			columns:[[
				{field:'powercycler',title:'Powercycler',width:150,rowspan:2,resizable:false},
				{field:'equipment',title:'Equipment',width:150,rowspan:2,resizable:false},
				{field:'outlet',title:'Outlet/Line',width:80,rowspan:4,resizable:false},
				{field:'strStatus',title:'Status',width:60,rowspan:2,resizable:false,align:'center'},
				{field:'owner',title:'Owner',width:100,rowspan:2,resizable:false},
				{field:'operation',title:'Operation',width:60,rowspan:2,resizable:false,align:'center'}
			]],
			pagination:true,
			rownumbers:true,
			onLoadSuccess:function(){  
                $('#monitor_view').datagrid('clearSelections');
            }  
		});
	}
</script>
<div region="north" border="false">
	<br/>
	<form id="monitorForm" method="post">
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
			<td><a href="#" class="easyui-linkbutton" plain="true">Equipment : </a></td>
			<td>
				<select class="easyui-combobox" id="equipmentId" name="equipmentId" style="width:204px;"></select>
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
			<td><a href="#" class="easyui-linkbutton" plain="true">Owner : </a></td>
			<td>
				<input type="text" id="owner" name="owner" style="width:200px;" onkeypress="if(event.keyCode==13){doMonitorSearch();return false;}">
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Powercycler : </a></td>
			<td>
				<select class="easyui-combobox" id="powercyclerId" name="powercyclerId" style="width:204px;"></select>
			</td>
			<td></td>
			<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doMonitorSearch()">Search</a></td>
		</tr>
	</table>
	</form>
	<br/>
</div>
<div region="center" border="false">
	<div id="PowerMonitor" class="easyui-tabs" fit="false" border="false" plain="true">
		<div title="Monitor Information">
			<table id="monitor_view" toolbar="#toolbar_monitor"></table>
			<div id="toolbar_monitor">  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="powerOnAll();">On</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="powerOffAll();">Off</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="monitor();">Monitor</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="release();">Release</a>  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="setCenter('PowerCycler', 'powercycler/powercycler_view');">PowerCycler</a>  
			</div>
			<div id="monitor" class="easyui-window" closed="true"></div>
		</div>
	</div>
</div>
<form id="releaseMonitorForm" method="post"></form>
<form id="powerForm" method="post"></form>
<script type="text/javascript">
		function doMonitorSearch(){
			$('#monitorForm').form('submit',{
				url:'monitor/monitor_form',
				success:function(linkUrl){
					linkUrl = linkUrl.replace(new RegExp("&amp;","gm"),"&");
					getMonitorData(linkUrl);
				}
			});
		}
		function powerOnAll() {
			$.messager.confirm('Confirm','Are you sure you want to power on?',function(f){  
			    if (f){  
			    	var rows = $('#monitor_view').datagrid('getSelections');
					if(0 == rows.length) {
						$.messager.alert('Warning','You must select an asset at least.'); 
						return;
					}
					var ids = '';
					for(var i = 0; i < rows.length; i ++) {
						ids += rows[i].id;
						if(i != rows.length - 1) {
							ids += ',';
						}
					}
					$('#powerForm').form('submit',{
						url:'monitor/monitor_poweron?id='+ids,
						success:function(msg){
							$.messager.alert('Info',msg); 
							getMonitorData('monitor/query_list');
						}
					});
			    }  
			});
		}
		function powerOffAll() {
			$.messager.confirm('Confirm','Are you sure you want to power off?',function(f){  
			    if (f){  
			    	var rows = $('#monitor_view').datagrid('getSelections');
					if(0 == rows.length) {
						$.messager.alert('Warning','You must select an asset at least.'); 
						return;
					}
					var ids = '';
					for(var i = 0; i < rows.length; i ++) {
						ids += rows[i].id;
						if(i != rows.length - 1) {
							ids += ',';
						}
					}
					$('#powerForm').form('submit',{
						url:'monitor/monitor_poweroff?id='+ids,
						success:function(msg){
							$.messager.alert('Info',msg); 
							getMonitorData('monitor/query_list');
						}
					});
			    }  
			});
		}
		function powerOn() {
			$.messager.confirm('Confirm','Are you sure you want to power on?',function(f){  
			    if (f){  
			    	var row = $('#monitor_view').datagrid('getSelected');
			    	$('#powerForm').form('submit',{
						url:'monitor/monitor_poweron?id='+row.id,
						success:function(msg){
							$.messager.alert('Info',msg); 
							getMonitorData('monitor/query_list');
						}
					});
			    }  
			});
		}
		function powerOff() {
			$.messager.confirm('Confirm','Are you sure you want to power off?',function(f){  
			    if (f){  
			    	var row = $('#monitor_view').datagrid('getSelected');
			    	$('#powerForm').form('submit',{
						url:'monitor/monitor_poweroff?id='+row.id,
						success:function(msg){
							$.messager.alert('Info',msg); 
							getMonitorData('monitor/query_list');
						}
					});
			    }  
			});
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
			    onChange:function(newValue,oldValue){
			    	setEquipment(newValue);
			    }
			});
		}
		function setEquipment(groupId) {
			$('#equipmentId').combobox({
				url:'equipment/query_union?groupId=' + groupId,  
			});
		}
		function monitor() {
			var $win;
		    $win = $('#monitor').window({
		            title:'Monitor Equipment',
		            width: 400,
		            height: 225,
		            shadow: true,
		            modal:true,
		            iconCls:'icon-tip',
				    closed:true,
				    cache:false,
				    minimizable:false,
				    maximizable:false,
				    collapsible:false,
				    href:'monitor/monitor_monitor'
		         });
		   
		    $win.window('open');
		}
		function release() {
			var row = $('#monitor_view').datagrid('getSelected');
			if(null==row) {
				$.messager.alert('Warning','You must select a row firstly.'); 
				return;
			}
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to release the [' + row.id + '] '+ row.equipment + ' PowerCycler monitor?',function(f){  
				    if (f){  
				    	$('#releaseMonitorForm').form('submit',{
							url:'monitor/monitor_release?id='+row.id,
							success:function(msg){
								$.messager.alert('Info',msg); 
								getMonitorData('monitor/query_list');
							}
						}); 
				    }  
				});
			}  
		}
</script>
