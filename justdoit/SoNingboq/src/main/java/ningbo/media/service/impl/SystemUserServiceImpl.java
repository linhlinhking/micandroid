package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.admin.util.Result;
import ningbo.media.bean.SystemUser;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.SystemUserDao;
import ningbo.media.service.SystemUserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("systemUserService")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Integer>
		implements SystemUserService {

	@Resource
	private SystemUserDao systemUserDao;

	@Autowired(required = false)
	public SystemUserServiceImpl(
			@Qualifier("systemUserDao") SystemUserDao systemUserDao) {
		super(systemUserDao);
	}

	public SystemUser login(String username, String password) {
		return systemUserDao.login(username, password);
	}

	public List<SystemUser> querySystemUserByName(String name) {
		return systemUserDao.querySystemUserByName(name);
	}

	public Pagination<SystemUser> getAllByPage(int pageNo, int pageSize) {
		if (pageNo < 1) {
			pageNo = 1;
		}
		if (pageSize < 1) {
			pageSize = 20;
		}
		return systemUserDao.getAllByPage(pageNo, pageSize);
	}

	public Result<SystemUser> findUserByUsername(String username,
			String password) throws ServiceException {
		Result<SystemUser> rs = new Result<SystemUser>();
		rs.setSuccess(false) ;
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			return rs ;
		}
		SystemUser u = systemUserDao.login(username, password) ;
		if(null != u){
			rs.setSuccess(true) ;
			rs.setDefaultModel(u) ;
		}
		return rs;
	}

}
