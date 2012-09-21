<%@page import="com.cisco.pmonitor.dao.dataobject.*, java.util.*"%>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="manageGroupForm" method="post">
		<input type="hidden" id="username" name="username" value="<%=request.getAttribute("username")%>">
		<table id="manageTable">
			<%
				List<GroupDo> list = (List<GroupDo>)request.getAttribute("list");
				if(null != list && list.size() > 0){
					for(GroupDo g : list) {
			%>
			<tr>
				<td><label>Group:</label></td>
				<td>
					<input type="hidden" id="id" name="id" value="<%=g.getId()%>">
					<input type="text" id="name" name="name" style="width:200px;" disabled="disabled" value="<%=g.getName()%>">
				</td>
				<td>
					<a href="#" id="remove" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:dynamicRemove(this);">Remove</a>
				</td>
			</tr>
			<%}}%>
		</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doManage();">Ok</a>
		<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeManageW();">Cancel</a>
	</div>
</div>
<script type="text/javascript">
function doManage(){
	$('#manageGroupForm').form('submit',{
		url:'user/manage_group',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getTableData('user/query_list');
			$('#manageGroup').window('close');
		}
	});
}
function closeManageW() {
	$('#manageGroup').window('close');
}
function dynamicRemove(row){
	var i=row.parentNode.parentNode.rowIndex;
	document.getElementById('manageTable').deleteRow(i);
}
</script>