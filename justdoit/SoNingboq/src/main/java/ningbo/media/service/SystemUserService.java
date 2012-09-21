package ningbo.media.service;

import java.util.List;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.admin.util.Result;
import ningbo.media.bean.SystemUser;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.service.BaseService;

public interface SystemUserService extends BaseService<SystemUser, Integer> {
	
	public SystemUser login(String username, String password);
	
	public List<SystemUser> querySystemUserByName(String name);
	
	public Pagination<SystemUser> getAllByPage(int pageNo,int pageSize);
	
	public Result<SystemUser> findUserByUsername(String username,String password) throws ServiceException;
}
