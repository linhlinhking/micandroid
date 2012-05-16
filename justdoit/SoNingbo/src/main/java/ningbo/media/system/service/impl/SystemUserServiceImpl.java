package ningbo.media.system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.entity.SystemUser;
import ningbo.media.system.dao.SystemUserDao;
import ningbo.media.system.service.SystemUserService;

@Service("systemUserService")
@Transactional
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Long>
		implements SystemUserService {

	@javax.annotation.Resource
	private SystemUserDao systemUserDao ;
	
	@Autowired
	public SystemUserServiceImpl(@Qualifier("systemUserDao")
	SystemUserDao systemUserDao) {
		super(systemUserDao);
	}



	public SystemUser getUserByLoginName(String loginName) {
		SystemUser u = systemUserDao.get("username", loginName) ;
		return u;
	}



	public void updateLastLoginDate(Date lastDate, String username)
			throws Exception {
		
	}


	
	
}
