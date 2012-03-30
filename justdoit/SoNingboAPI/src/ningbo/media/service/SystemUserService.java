package ningbo.media.service;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.service.BaseService;

public interface SystemUserService extends BaseService<SystemUser, Integer> {
	
	public SystemUser verificationUser(String email,String password) ; 
	
	public boolean isExistsKey(String key,Integer userId) ;
	
	public Integer login(String username, String password);

}
