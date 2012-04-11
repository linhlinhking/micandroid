package ningbo.media.service;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.service.BaseService;

public interface SystemUserService extends BaseService<SystemUser, Integer> {
	
	
	public Integer login(String username, String password);
	
	public boolean isContainTool(Integer toolId) ;

}
