package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.service.BaseService;

public interface SystemUserService extends BaseService<SystemUser, Integer> {
	
	public SystemUser login(String username, String password);
	
	public SystemUser getSystemUserByMd5Value(String md5Value);
	
	public List<SystemUser> querySystemUserByName(String name);
	

}
