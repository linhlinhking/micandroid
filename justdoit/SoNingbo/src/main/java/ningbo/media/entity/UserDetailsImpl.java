package ningbo.media.entity;

import java.util.Collection;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl extends SystemUser implements UserDetails,
		CredentialsContainer {

	private static final long serialVersionUID = -5965956947778373742L;

	private Collection<GrantedAuthority> authorities;

	public UserDetailsImpl(SystemUser u) {
		setId(u.getId()) ;
		setUsername(u.getUsername()) ;
		setPassword(u.getPassword()) ;
		setPasswordRecoverKey(u.getPasswordRecoverKey()) ;
		setEmail(u.getEmail()) ;
		setIsAccountEnabled(u.getIsAccountEnabled()) ;
		setIsAccountExpired(u.getIsAccountExpired()) ;
		setIsAccountLocked(u.getIsAccountLocked()) ;
		setIsCredentialsExpired(u.getIsCredentialsExpired()) ;
		setLoginFailureCount(u.getLoginFailureCount()) ;
		setLoginDate(u.getLockedDate()) ;
		setLockedDate(u.getLockedDate()) ;
		setLoginIp(u.getLoginIp()) ;
		setRegisterIp(u.getRegisterIp()) ;
		setRoles(u.getRoles()) ;
		setAuthoritys(u.getAuthoritys()) ;
		setCreateDate(u.getCreateDate()) ;
		setModifyDate(u.getModifyDate()) ;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isEnabled() {
		return getIsAccountEnabled();
	}
	
	public boolean isAccountNonExpired() {
		return !getIsAccountExpired() ;
	}

	public boolean isAccountNonLocked() {
		return !getIsAccountLocked() ;
	}

	public boolean isCredentialsNonExpired() {
		return !getIsCredentialsExpired(); 
	}


	/** 用于清除敏感数据 */
	public void eraseCredentials() {
		setPassword(null);
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/** 获取当前登录用户详细信息必须重写次方法 */
	public int hashCode() {
		return getUsername().hashCode();
	}

	/** 获取当前登录用户详细信息必须重写次方法 */
	public boolean equals(Object obj) {
		if (obj instanceof UserDetails) {
			UserDetails ud = (UserDetails) obj;
			if (ud.getUsername().equals(this.getUsername())) {
				return true;
			}
		}
		return false;
	}

}
