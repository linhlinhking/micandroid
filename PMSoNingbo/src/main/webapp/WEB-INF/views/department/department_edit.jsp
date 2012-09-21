<%@page import="com.cisco.pmonitor.dao.dataobject.*, java.util.*"%>
<%
	DepartmentDo departmentDo = (DepartmentDo)request.getAttribute("departmentDo");
%>
<script type="text/javascript">
function doDepartmentEdit(){
	$('#departmentEditForm').form('submit',{
		url:'department/department_edit',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getDepartmentData('department/query_list');
			$('#editDepartment').window('close');
		}
	});
}
function closeDEditW() {
	$('#editDepartment').window('close');
}
</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="departmentEditForm" method="post">
			<input type="hidden" name="id" id="id" value="<%=departmentDo.getId()%>">
			<table>
				<tr>
					<td><label for="name">Name: </label></td>
					<td>
						<input id="name" name="name" required="true" style="width:200px;" value="<%=departmentDo.getName()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Description: </label></td>
					<td> <textarea rows="3" cols="40" id="description" name="description"><%=departmentDo.getDescription() %></textarea> </td>
				</tr>
			</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doDepartmentEdit();">Ok</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeDEditW();">Cancel</a>
		</div>
</div>