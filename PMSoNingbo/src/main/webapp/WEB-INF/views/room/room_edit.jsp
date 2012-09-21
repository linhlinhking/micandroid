<%@page import="com.cisco.pmonitor.dao.dataobject.*, java.util.*"%>
<%
	RoomDo roomDo = (RoomDo)request.getAttribute("roomDo");
%>
<script type="text/javascript">
function doRoomEdit(){
	$('#roomEditForm').form('submit',{
		url:'room/room_edit',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getRoomData('room/query_list');
			$('#editRoom').window('close');
		}
	});
}
function closeREditW() {
	$('#editRoom').window('close');
}
</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="roomEditForm" method="post">
			<input type="hidden" name="id" id="id" value="<%=roomDo.getId()%>">
			<table>
				<tr>
					<td><label for="name">Name: </label></td>
					<td>
						<input id="name" name="name" required="true" style="width:200px;" value="<%=roomDo.getName()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Owner: </label></td>
					<td>
						<input id="owner" name="owner" required="true" style="width:200px;" value="<%=roomDo.getOwner()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Location: </label></td>
					<td>
						<input id="location" name="location" required="true" style="width:200px;" value="<%=roomDo.getLocation()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Department: </label></td>
					<td>
						<select id="departmentId" class="easyui-combobox" name="departmentId" style="width:204px;" required="true" panelHeight="auto">
						<%
							List<DepartmentDo> departmentList = (List<DepartmentDo>)request.getAttribute("departmentList");
							if(null != departmentList) {
								for(DepartmentDo department : departmentList) {
									%>
								<option value="<%=department.getId() %>" <%if(roomDo.getDepartmentId() == department.getId()){ %>selected="selected"<%} %>><%=department.getName() %></option>			
									
									<%
								}
							}
						%>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="name">Description: </label></td>
					<td> <textarea rows="3" cols="40" id="description" name="description"><%=roomDo.getDescription() %></textarea> </td>
				</tr>
			</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doRoomEdit();">Ok</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeREditW();">Cancel</a>
		</div>
</div>