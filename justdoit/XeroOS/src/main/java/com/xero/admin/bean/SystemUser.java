package com.xero.admin.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.xero.core.bean.BaseEntity;

@Table(name = "tb_user")
@Entity
public class SystemUser extends BaseEntity {

	private static final long serialVersionUID = 2925504992230656454L;

	public final static String UEMAIL = "uemail" ;
	
	private String username ;
	
	private String uemail;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private Integer planId;

	private Date expiredDateTime;

	private String registerIpAddress;
	
	private Date lastSeen ;

	private String language;
	
	@JsonIgnore
	private String xeroId ;

	@JsonIgnore
	@Column(name = "join_in_type")
	private String joinInType;

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpiredDateTime() {
		return expiredDateTime;
	}

	public void setExpiredDateTime(Date expiredDateTime) {
		this.expiredDateTime = expiredDateTime;
	}

	public String getRegisterIpAddress() {
		return registerIpAddress;
	}

	public void setRegisterIpAddress(String registerIpAddress) {
		this.registerIpAddress = registerIpAddress;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getJoinInType() {
		return joinInType;
	}

	public void setJoinInType(String joinInType) {
		this.joinInType = joinInType;
	}

	public String getXeroId() {
		return xeroId;
	}

	public void setXeroId(String xeroId) {
		this.xeroId = xeroId;
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
