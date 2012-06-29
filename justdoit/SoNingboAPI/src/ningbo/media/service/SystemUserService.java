package ningbo.media.service;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.service.BaseService;

public interface SystemUserService extends BaseService<SystemUser, Integer> {
	
	
	public SystemUser login(String username, String password);
	
	public SystemUser getSystemUserByMd5Value(String md5Value);
	

}
