package ningbo.media.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import ningbo.media.entity.base.BaseEntity;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_system_user")
public class SystemUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5504187030110869013L;

	@Column(updatable = false,nullable = false,unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private Boolean isAccountEnabled = true;// 账号是否启用
	
	@Column(nullable = false)
	private Boolean isAccountLocked = true;// 账号是否锁定
	
	@Column(nullable = false)
	private Boolean isAccountExpired = true;// 账号是否过期
	
	@Column(nullable = false)
	private Boolean isCredentialsExpired = true;// 凭证是否过期

	@Column(nullable = false)
	private Integer loginFailureCount = 0;

	private Date lockedDate;

	@Column(updatable = false,nullable = false)
	private String registerIp = "0.0.0.0";

	private String loginIp;

	private Date loginDate;

	private String passwordRecoverKey;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@OrderBy("name asc")
	private Collection<SystemRole> roles ;//管理角色
	
	@Transient
	private Collection<GrantedAuthority> authoritys ;//角色信息

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getPasswordRecoverKey() {
		return passwordRecoverKey;
	}

	public void setPasswordRecoverKey(String passwordRecoverKey) {
		this.passwordRecoverKey = passwordRecoverKey;
	}

	public Collection<SystemRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<SystemRole> roles) {
		this.roles = roles;
	}

	public Collection<GrantedAuthority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Collection<GrantedAuthority> authoritys) {
		this.authoritys = authoritys;
	}

	public Boolean getIsAccountEnabled() {
		return isAccountEnabled;
	}

	public void setIsAccountEnabled(Boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}

	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public Boolean getIsAccountExpired() {
		return isAccountExpired;
	}

	public void setIsAccountExpired(Boolean isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
	}

	public Boolean getIsCredentialsExpired() {
		return isCredentialsExpired;
	}

	public void setIsCredentialsExpired(Boolean isCredentialsExpired) {
		this.isCredentialsExpired = isCredentialsExpired;
	}

}
