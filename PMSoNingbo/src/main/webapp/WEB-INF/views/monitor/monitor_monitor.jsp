<script type="text/javascript">
	$(function() {
		$('#powercycler').combobox({
			url:'powercycler/query_all',  
		    valueField:'id',  
		    textField:'name', 
		    panelHeight:'auto'
		});
		$('#equipment').combobox({
			url:'equipment/query_all',  
		    valueField:'id',  
		    textField:'name', 
		    panelHeight:'auto'
		});
	});
	function doMonitor(){
		$('#monitor2Form').form('submit',{
			url:'monitor/monitor_monitor',
			onSubmit: function(){ 
			},
			success:function(msg){
				$.messager.alert('Info',msg); 
				getMonitorData('monitor/query_list');
				$('#monitor').window('close');
			}
		});
	}
	function doMonitorClose() {
		$('#monitor').window('close');
	}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="monitor2Form" method="post">
		<table>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Powercycler : </a></td>
				<td>
					<select class="easyui-combobox" id="powercycler" name="powercyclerId" style="width:204px;"></select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Equipment : </a></td>
				<td>
					<select class="easyui-combobox" id="equipment" name="equipmentId" style="width:204px;"></select>
				</td>
			</tr>
			<tr>
				<td><a href="#" class="easyui-linkbutton" plain="true">Outlet:</a></td>
				<td>
					<input id="outlet" name="outlet" required="true" style="width:200px;">
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
		</table>
		</form>
	</div>	
	<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doMonitor();">Ok&nbsp;</a>
		<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" href="javascript:void(0)" onclick="doMonitorClose();">Cancel</a>
	</div>	
</div>
