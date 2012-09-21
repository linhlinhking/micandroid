package com.cisco.pmonitor.dao.query;

import com.cisco.pmonitor.dao.utils.Constants;

public class PowerCyclerQuery extends BaseQuery {

	private static final long serialVersionUID = 8009781352382169377L;
	
	private String name;
	private int type;
	private String host;
	private int from;
	private int to;
	private int on;
	private int off;
	private int status = -1;
	private int protocol;
	private String loginUser;
	private String loginPwd;
	private String enablePwd;
	private String strType;
	private String strStatus;
	private String strProtocol;
	private String outlet;
	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}
	public String getOutlet() {
		this.outlet = from + "~" +to;
		return outlet;
	}
	public String getStrProtocol() {
		return strProtocol;
	}
	public void setStrProtocol(String strProtocol) {
		this.strProtocol = strProtocol;
	}
	public String getStrType() {
		return strType;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getName() {
		return name == null ? "" : name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
		if(type == Constants.DTR_POWERCYCLER){
			this.strType= "DTR";
		}
		if(type == Constants.DUALCOMM_POWERCYCLER){
			this.strType= "DualComm";
		}
		if(type == Constants.APC_POWERCYCLER){
			this.strType= "APC";
		}
		if(type == Constants.UBR10KLC_POWERCYCLER){
			this.strType= "uBR10kLC";
		}
	}
	public String getHost() {
		return host  == null ? "" : host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getOn() {
		return on;
	}
	public void setOn(int on) {
		this.on = on;
	}
	public int getOff() {
		return off;
	}
	public void setOff(int off) {
		this.off = off;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		if(status == Constants.POWER_ON) {
//			this.strStatus = "<span style='color:green'>On</span>";
			this.strStatus = "<img src='images/green-circle.png' style='width:16px;height:16px;'/>";
		}
		if(status == Constants.POWER_OFF) {
//			this.strStatus = "<span style='color:red'>Off</span>";
			this.strStatus = "<img src='images/red-circle.png' style='width:16px;height:16px;'/>";
		}
	}
	public int getProtocol() {
		return protocol;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
		if(protocol == Constants.SNMP_PROTOCOL) {
			this.strProtocol = "Snmp";
		}
		if(protocol == Constants.TELNET_PROTOCOL) {
			this.strProtocol = "Telnet";
		}
		if(protocol == Constants.SOCKET_PROTOCOL) {
			this.strProtocol = "Socket";
		}
	}
	public String getLoginUser() {
		return loginUser  == null ? "" : loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getLoginPwd() {
		return loginPwd  == null ? "" : loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getEnablePwd() {
		return enablePwd  == null ? "" : enablePwd;
	}
	public void setEnablePwd(String enablePwd) {
		this.enablePwd = enablePwd;
	}
}
