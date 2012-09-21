<style type="text/css">
	.left{
		width:120px;
		float:left;
	}
	.left table{
		background:#E0ECFF;
	}
	.left td{
		background:#eee;
	}
	.right{
		float:right;
		width:800px;
	}
	.right table{
		background:#E0ECFF;
		width:100%;
	}
	.right td{
		background:#fafafa;
		text-align:center;
		padding:2px;
	}
	.right td{
		background:#E0ECFF;
	}
	.right td.drop{
		background:#fafafa;
		width:100px;
	}
	.right td.over{
		background:#FBEC88;
	}
	.item{
		text-align:center;
		border:1px solid #499B33;
		background:#fafafa;
		width:100px;
	}
	.assigned{
		border:1px solid #BC2A4D;
	}
	
</style>
<script type="text/javascript">
$('#departmentId').combobox({
	url:'department/query_all',  
    valueField:'id',  
    textField:'name', 
    panelHeight:'auto',
    onChange:function(newValue,oldValue){
    	setRoom(newValue);
    }
});
$('#roomId').combobox({
    valueField:'id',  
    textField:'name', 
    panelHeight:'auto'
});
$(function(){
	$('.left .item').draggable({
		revert:true,
		proxy:'clone'
	});
	$('.right td.drop').droppable({
		onDragEnter:function(e, source){
			$(this).addClass('over');
		},
		onDragLeave:function(e, source){
			$(this).removeClass('over');
		},
		onDrop:function(e,source){
			$(this).removeClass('over');
			if ($(source).hasClass('assigned')){
				$(this).append(source);
			} else {
				var c = $(source).clone().addClass('assigned');
				$(this).empty().append(c);
				c.draggable({
					revert:true
				});
			}
		}
	});
});
function setRoom(departmentId) {
	$('#roomId').combobox({
		url:'room/query_union?departmentId=' + departmentId
	});
}
</script>

<div region="north" border="false">
	<br/>
	<form id="roomForm" method="post">
	<table>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Department : </a></td>
			<td>
				<select class="easyui-combobox" style="width:204px;" id="departmentId" name="departmentId"></select>
			</td>
		</tr>
		<tr>
			<td><a href="#" class="easyui-linkbutton" plain="true">Room : </a></td>
			<td>
				<select class="easyui-combobox" style="width:204px;" id="roomId" name="roomId"></select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="submitRQuery()">Search</a></td>
		</tr>
	</table>
	</form>
	<br/>
</div>
<div region="center" border="false">
	<div style="width:950px;">
	<div class="left">
		<table>
			<tr>
				<td><div class="item">Testbed</div></td>
			</tr>
			<tr>
				<td><div class="item">Linecard</div></td>
			</tr>
			<tr>
				<td><div class="item">10K</div></td>
			</tr>
			<tr>
				<td><div class="item">Switcher</div></td>
			</tr>
			<tr>
				<td><div class="item">Model</div></td>
			</tr>
			<tr>
				<td><div class="item">Powercycler</div></td>
			</tr>
		</table>
	</div>
	<div class="right">
		<table>
			<tr>
				<td class="blank"></td>
				<td class="title">A</td>
				<td class="title">B</td>
				<td class="title">C</td>
				<td class="title">D</td>
				<td class="title">E</td>
				<td class="title">F</td>
				<td class="title">G</td>
			</tr>
			<tr>
				<td class="time">1</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="time">2</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="time">3</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="time">4</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="time">5</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="time">6</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="time">7</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="time">8</td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
				<td class="drop"></td>
			</tr>
		</table>
	</div>
</div>
</div>