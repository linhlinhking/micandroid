package ningbo.media.system.dao.impl;


import org.springframework.stereotype.Repository;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.entity.SystemUser;
import ningbo.media.system.dao.SystemUserDao;

@Repository("systemUserDao")
public class SystemUserDaoImpl extends BaseDaoImpl<SystemUser, Long> implements
		SystemUserDao {
	public SystemUserDaoImpl() {
		super(SystemUser.class);
	}

	
}
