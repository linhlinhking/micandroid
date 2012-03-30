package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.SystemUserDao;

@Repository("systemUserDao")
public class SystemUserDaoImpl extends BaseDaoImpl<SystemUser, Integer>
		implements SystemUserDao {
	
	public SystemUserDaoImpl(){
		super(SystemUser.class);
	}

}
