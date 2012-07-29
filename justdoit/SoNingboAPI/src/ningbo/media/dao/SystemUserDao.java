package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.dao.BaseDao;

public interface SystemUserDao extends BaseDao<SystemUser, Integer>{
	
	public SystemUser login(String username, String password);

	public SystemUser getSystemUserByMD5Value(String value);
	
	public List<SystemUser> querySystemUserByName(String name);
}
