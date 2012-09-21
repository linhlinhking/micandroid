<%@page import="com.cisco.pmonitor.dao.dataobject.*, java.util.*"%>
<%
	UserDo userDo = (UserDo)request.getAttribute("userDo");
%>
<script type="text/javascript">
function doUserEdit(){
	$('#userEditForm').form('submit',{
		url:'user/user_edit',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getTableData('user/query_list');
			$('#editUser').window('close');
		}
	});
}
function closeEditW() {
	$('#editUser').window('close');
}
</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="userEditForm" method="post">
			<input type="hidden" id="id" name="id" value="<%=userDo.getId()%>">
			<table>
				<tr>
					<td><label for="name">User ID:</label></td>
					<td>
						<input id="username" name="username" required="true" style="width:200px;" value="<%=userDo.getUsername()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Full Name:</label></td>
					<td>
						<input id="fullName" name="fullName" required="true" style="width:200px;" value="<%=userDo.getFullName()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Email:</label></td>
					<td>
						<input id="email" name="email" required="true" style="width:200px;" value="<%=userDo.getEmail()%>">
					</td>
				</tr>
				<tr>
					<td><label>Role Type:</label></td>
					<td>
						<select id="role" class="easyui-combobox" name="role" style="width:204px;" required="true">
							<option value="0" <%if(userDo.getRole() == 0) {%>selected="selected"<%} %>>User</option>
							<option value="1" <%if(userDo.getRole() == 1) {%>selected="selected"<%} %>>Administrator</option>
						</select>
					</td>
				</tr>
			</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" href="javascript:void(0)" onclick="doUserEdit();">Ok</a>
			<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeEditW();">Cancel</a>
		</div>
</div>