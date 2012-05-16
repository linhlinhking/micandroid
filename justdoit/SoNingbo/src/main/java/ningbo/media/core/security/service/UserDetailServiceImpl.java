package ningbo.media.core.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ningbo.media.core.utils.DateUtils;
import ningbo.media.entity.Resource;
import ningbo.media.entity.SystemRole;
import ningbo.media.entity.SystemUser;
import ningbo.media.entity.UserDetailsImpl;
import ningbo.media.system.service.ResourceService;
import ningbo.media.system.service.SystemRoleService;
import ningbo.media.system.service.SystemUserService;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailServiceImpl implements UserDetailsService{

	@javax.annotation.Resource
	private SystemUserService systemUserService ;
	
	@javax.annotation.Resource
	private ResourceService resourceService ;
	
	@javax.annotation.Resource
	private SystemRoleService systemRoleService ;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		SystemUser user = systemUserService.getUserByLoginName(username) ;
		if(user == null){
			throw new UsernameNotFoundException(username + " Is Not Exists,Sign Failure.") ;
		}
		List<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>() ;
		GrantedAuthority gAuth = null ;
		Collection<Resource> ress = resourceService.getResourcesByUserName(username) ;
		for(Resource res : ress){
			gAuth = new SimpleGrantedAuthority(res.getName()) ;
			authoritys.add(gAuth) ;
		}
		user.setAuthoritys(authoritys) ;
		Collection<SystemRole> roles = systemRoleService.getRolesByUserName(username) ;
		user.setRoles(roles) ;
		try{
			Date lastLogintime = DateUtils.getCurrentDateTime() ;
			systemUserService.updateLastLoginDate(lastLogintime, user.getUsername()) ;
		}catch(Exception ex){
			
		}
		UserDetails userDetails = new UserDetailsImpl(user);
		return userDetails;
	}

}
