package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.dao.BaseDao;
import ningbo.media.core.page.Pagination;

public interface SystemUserDao extends BaseDao<SystemUser, Integer>{
	
	public SystemUser login(String username, String password);

	public SystemUser getSystemUserByMD5Value(String value);
	
	public List<SystemUser> querySystemUserByName(String name);
	
	public Pagination<SystemUser> getAllByPage(int pageNo,int pageSize);
}
