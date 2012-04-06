package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.SystemUserDao;

@Repository("systemUserDao")
public class SystemUserDaoImpl extends BaseDaoImpl<SystemUser, Integer>
		implements SystemUserDao {

	public SystemUserDaoImpl() {
		super(SystemUser.class);
	}

	public Integer login(String username, String password) {
		SystemUser u = verification(username, password) ;
		if (null == u) {
			return 0 ;
		}
		return u.getId() ;
	}

	private SystemUser verification(String email, String password) {
		final String hql = "from SystemUser as model where 1=1 and model.email = ? and model.password = ? ";
		SystemUser u = (SystemUser) super.findUnique(hql, email, password);
		if (null == u) {
			return null;
		}
		return u;

	}

}
