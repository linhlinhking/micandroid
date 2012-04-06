package ningbo.media.dao;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.dao.BaseDao;

public interface SystemUserDao extends BaseDao<SystemUser, Integer>{
	
	public Integer login(String username, String password);
	
}
