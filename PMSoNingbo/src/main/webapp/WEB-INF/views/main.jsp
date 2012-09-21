<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.PM_USER_SESSION != null }">
<c:set var="user" value="${sessionScope.PM_USER_SESSION }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Power Monitor</title>
	<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.nav-item{
			text-align:center;
			background:#fff;
			height:80px;
		}
		.nav-item img{
			border:0;
		}
	</style>
</head>
<body class="easyui-layout" style="text-align:left" onload="setCenter('Welcome', 'html/welcome.html')">
		<div region="north" border="false" style="background:#666;text-align:center">
			<div id="header-inner">
				<table cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td rowspan="2" style="width:20px;">
						</td>
						<td style="height:52px;">
							<div style="color:#fff;font-size:22px;font-weight:bold;">
								<a href="#" style="color:#fff;font-size:22px;font-weight:bold;text-decoration:none">Power Monitor</a>
							</div>
							<div style="color:#fff">
								<a href="#" style="color:#fff;text-decoration:none">PMonitor help you monitor the lab status!</a>
							</div>
						</td>
						<td style="padding-right:5px;text-align:right;vertical-align:bottom;">
							<div id="topmenu">
								<a href="#"><span style="font-weight:bold">Welcome <span style="color:red"><c:out value="${user.fullName }"/></span> </span></a>&nbsp;
								<a href="home"><span style="font-weight:bold">HomePage</span></a>&nbsp;
								<a href="logout"><span style="font-weight:bold">Logout</span></a>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
		</div>
		<div region="west" border="false" split="true" title="PMonitor" style="width:250px;padding:5px;">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="Operations" selected="true" style="overflow:auto;">
					<div class="nav-item">  
		                <a href="#" onclick="setCenter('PowerMonitor', 'monitor/monitor_view')">  
		                    <img src="images/package_settings.png"/><br/>  
		                </a>  
		                <span style="font-weight:bold">Power Monitor</span>  
		            </div> 
		            <div class="nav-item">  
		                <a href="#" onclick="setCenter('Equipment', 'equipment/equipment_view')">  
		                    <img src="images/kontact.png"/><br/>  
		                </a>  
		                <span style="font-weight:bold">Equipment</span>  
		            </div> 
		            <div class="nav-item">  
		                <a href="#" onclick="setCenter('Schedule', 'schedule/schedule_view')">
		                    <img src="images/kdmconfig.png"/><br/>
		                </a>  
		                <span style="font-weight:bold">Schedule</span>
		            </div>
		            <div class="nav-item">  
		                <a href="#" onclick="setCenter('Room/Dept', 'room/room_view')">  
		                    <img src="images/package.png"/><br/>  
		                </a>  
		                <span style="font-weight:bold">Room/Lab</span>  
		            </div>
		            <div class="nav-item">  
		                <a href="#" onclick="setCenter('Environment', 'environment/environment_view')">  
		                    <img src="images/print_class.png"/><br/>  
		                </a>  
		                <span style="font-weight:bold">Environment</span>  
		            </div>
				</div>
				<div title="Setting" selected="false" style="overflow:auto;">
					<div class="nav-item">  
		                <a href="#" onclick="setCenter('User Info', 'user/user_view')">  
		                    <img src="images/kdmconfig.png"/><br/>  
		                </a>  
		                <span style="font-weight:bold">User</span>  
		            </div> 
		             <div class="nav-item">
		                <a href="#" onclick="setCenter('Rules', 'user/user_view')">
		                    <img src="images/kdmconfig.png"/><br/>
		                </a>
		                <span style="font-weight:bold">Rules</span>
		            </div>
		            <div class="nav-item">  
		                <a href="#" onclick="setCenter('Topology', 'topology/topology_view')">  
		                    <img src="images/package.png"/><br/>  
		                </a>  
		                <span style="font-weight:bold">Topology</span>  
		            </div>
				</div>
			</div>
		</div>
		<div region="center" border="false"></div>
</body>
<script type="text/javascript">
		function setCenter(title,href){  
            var centerURL = href;  
            var centerTitle = title;  
            $('body').layout('panel','center').panel({  
                title:"Position："+centerTitle,  
                href:centerURL  
            });  
            $('body').layout('panel','center').panel('refresh');  
            return false;         
        }  
</script>
</c:if>