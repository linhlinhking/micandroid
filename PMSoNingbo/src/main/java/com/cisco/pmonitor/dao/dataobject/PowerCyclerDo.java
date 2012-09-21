package com.cisco.pmonitor.dao.dataobject;

public class PowerCyclerDo extends BaseDo {

	private static final long serialVersionUID = 6458917538138208726L;
	
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
	}
	public int getProtocol() {
		return protocol;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
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
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PowerCyclerDo) {
			PowerCyclerDo pco = (PowerCyclerDo)obj;
			if(pco.getId() == this.getId()) {
				return true;
			}
		}
		return false;
	}
	

}
