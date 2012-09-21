<%@page import="com.cisco.pmonitor.dao.dataobject.*, java.util.*"%>
<%
	EquipmentDo equipmentDo = (EquipmentDo)request.getAttribute("equipmentDo");
%>
<script type="text/javascript">
function doEquipmentEdit(){
	$('#equipmentEditForm').form('submit',{
		url:'equipment/equipment_edit',
		onSubmit: function(){ 
			//alert('Do some checking...');
		},
		success:function(msg){
			$.messager.alert('Info',msg); 
			getTableData('equipment/query_list');
			$('#editEquipment').window('close');
		}
	});
}
function closeEEditW() {
	$('#editEquipment').window('close');
}
</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="equipmentEditForm" method="post">
			<input type="hidden" name="id" id="id" value="<%=equipmentDo.getId()%>">
			<table>
				<tr>
					<td><label for="name">Name: </label></td>
					<td>
						<input id="name" name="name" required="true" style="width:200px;" value="<%=equipmentDo.getName()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Owner: </label></td>
					<td>
						<input id="owner" name="owner" required="true" style="width:200px;" value="<%=equipmentDo.getOwner()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">Location: </label></td>
					<td>
						<input id="location" name="location" required="true" style="width:200px;" value="<%=equipmentDo.getLocation()%>">
					</td>
				</tr>
				<tr>
					<td><label for="name">GrossPower(W): </label></td>
					<td>
						<input id="grossPower" name="grossPower" required="true" style="width:200px;" value="<%=equipmentDo.getGrossPower()%>">
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
						<option value="<%=department.getId() %>" <%if(equipmentDo.getDepartmentId() == department.getId()){ %>selected="selected"<%} %>><%=department.getName() %></option>			
									
									<%
								}
							}
						%>
						</select>
		
					</td>
				</tr>
				<tr>
					<td><label for="name">Room/Lab: </label></td>
					<td>
						<select id="roomId" class="easyui-combobox" name="roomId" style="width:204px;" required="true" panelHeight="auto">
						<%
							List<RoomDo> roomList = (List<RoomDo>)request.getAttribute("roomList");
							if(null != roomList) {
								for(RoomDo room : roomList) {
									%>
						<option value="<%=room.getId() %>" <%if(equipmentDo.getRoomId() == room.getId()){ %>selected="selected"<%} %>><%=room.getName() %></option>			
									
									<%
								}
							}
						%>
						</select>
		
					</td>
				</tr>
				<tr>
					<td><label for="name">Group: </label></td>
					<td>
						<select id="groupId" class="easyui-combobox" name="groupId" style="width:204px;" required="true" panelHeight="auto">
						<%
							List<GroupDo> groupList = (List<GroupDo>)request.getAttribute("groupList");
							if(null != groupList) {
								for(GroupDo group : groupList) {
									%>
						<option value="<%=group.getId() %>" <%if(equipmentDo.getGroupId() == group.getId()){ %>selected="selected"<%} %>><%=group.getName() %></option>			
									
									<%
								}
							}
						%>
						</select>
					</td>
				</tr>
				<tr>
					<td><a href="#" class="easyui-linkbutton" plain="true">Status : </a></td>
					<td>
						<select id="status" class="easyui-combobox" name="status" style="width:204px;" required="true" panelHeight="auto">
							<option value="0" <%if(equipmentDo.getStatus() == 0){ %>selected="selected"<%} %>>Idle</option>
							<option value="1" <%if(equipmentDo.getStatus() == 1){ %>selected="selected"<%} %>>Reserved</option>
						</select>
		
					</td>
				</tr>
				<tr>
					<td><label for="name">Description: </label></td>
					<td> <textarea rows="3" cols="40" id="description" name="description"><%=equipmentDo.getDescription()%></textarea> </td>
				</tr>
			</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doEquipmentEdit();">Ok</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeEEditW();">Cancel</a>
		</div>
</div>