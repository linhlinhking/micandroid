<%@page import="com.cisco.pmonitor.dao.dataobject.*,com.cisco.pmonitor.dao.utils.*, java.util.*"%>
<%
	EquipmentDo equipmentDo = (EquipmentDo)request.getAttribute("equipmentDo");
%>
<script type="text/javascript">
$(function() {
	getReserveData('reserve/query_list?equipmentId=' + <%=equipmentDo.getId()%>);
});
function getReserveData(linkUrl) {
	$('#reserve_view').datagrid({
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
			{field:'equipmentName',title:'Equipment',width:120,rowspan:2,resizable:false},
			{field:'reserver',title:'Reserver',width:80,rowspan:2,resizable:false},
			{field:'startTime',title:'StartTime',width:150,rowspan:4,resizable:false},
			{field:'endTime',title:'EndTime',width:150,rowspan:4,resizable:false},
			{field:'strStatus',title:'Status',width:80,rowspan:2,resizable:false},
			{field:'description',title:'Description',width:180,rowspan:4,resizable:false}
		]],
		pagination:true,
		rownumbers:true,
		onLoadSuccess:function(){  
            $('#reserve_view').datagrid('clearSelections');
        }  
	});
}
function delReserve() {
	var row = $('#reserve_view').datagrid('getSelected');
	if(null==row) {
		$.messager.alert('Warning','You must select a row firstly.'); 
		return;
	}
	if (row){
		$.messager.confirm('Confirm','Are you sure you want to delete the [' + row.id + '] '+ row.equipmentName + ' reserve history?',function(f){  
		    if (f){  
		    	$('#delReserveForm').form('submit',{
					url:'reserve/reserve_del?id='+row.id,
					success:function(msg){
						$.messager.alert('Info',msg); 
						getReserveData('reserve/query_list?equipmentId=' + <%=equipmentDo.getId()%>);
					}
				}); 
		    }  
		});
	}  
}
function doEquipmentEdit(){
	$('#reserveEquipmentForm').form('submit',{
		url:'reserve/equipment_reserve',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getReserveData('reserve/query_list?equipmentId=' + <%=equipmentDo.getId()%>);
		}
	});
}
</script>
<div region="north" border="false">
	<form id="reserveEquipmentForm" method="post">
	<input type="hidden" name="equipmentId" id="equipmentId" value="<%=equipmentDo.getId()%>">
	<table>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Equipment:</a></td>
			<td>
				<span style="font-weight:bold"><%=equipmentDo.getName()%></span>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Owner:</a></td>
			<td>
				<span style="font-weight:bold"><%=equipmentDo.getOwner()%></span>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Status:</a></td>
			<td>
				<span style="font-weight:bold">
					<%
					int status = equipmentDo.getStatus();
					if(status == Constants.IDLE_EQUIPMENT_STATUS) {
						%><span style='color:green'>Idle</span><%
					}
					if(status == Constants.RESERVE_EQUIPMENT_STATUS) {
						%><span style='color:red'>Reserved</span><%
					}
					%>
				</span>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">From:</a></td>
			<td>
				<input id="startTime" name="startTime" class="easyui-datetimebox" style="width:204px;"></input>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">To:</a></td>
			<td>
				<input id="endTime" name="endTime" class="easyui-datetimebox" style="width:204px;"></input>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Reserver:</a></td>
			<td>
				<input id="reserver" name="reserver" required="true" style="width:200px;" value="<%=request.getAttribute("reserver")%>">
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Description:</a></td>
			<td> <textarea rows="2" cols="40" id="description" name="description"></textarea> </td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doEquipmentEdit();">Reserve</a>
				<a class="easyui-linkbutton" plain="true" iconCls="icon-back" href="javascript:void(0)" onclick="setCenter('Equipment', 'equipment/equipment_view')">Cancel</a>
			</td>
		</tr>
	</table>
	</form>
</div>		
<div region="center" border="false">
	<div id="reserve" class="easyui-tabs" fit="false" border="false" plain="true">
		<div title="Reserve Information">
			<table id="reserve_view" toolbar="#toolbar_reserve"></table>
			<div id="toolbar_reserve">  
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delReserve();">Delete</a>  
			</div>
		</div>
	</div>
</div>
<form id="delReserveForm" method="post"></form>