package ningbo.media.service.impl;

import javax.annotation.Resource;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.SystemUserDao;
import ningbo.media.service.SystemUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("systemUserService")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Integer> implements
		SystemUserService {
	
	@Resource
	private SystemUserDao systemUserDao ;
	
	@Autowired(required = false)
	public SystemUserServiceImpl(@Qualifier("systemUserDao")SystemUserDao systemUserDao) {
		super(systemUserDao);
	}
	
	public Integer login(String username, String password) {
		return systemUserDao.login(username, password) ;
	}

}
