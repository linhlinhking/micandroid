package ningbo.media.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.SystemUserDao;
import ningbo.media.util.MD5;

@Repository("systemUserDao")
public class SystemUserDaoImpl extends BaseDaoImpl<SystemUser, Integer>
		implements SystemUserDao {

	public SystemUserDaoImpl() {
		super(SystemUser.class);
	}

	public SystemUser login(String username, String password) {
		return verification(username, password);
	}

	private SystemUser verification(String email, String password) {
		String encodePassword = MD5.calcMD5(password);
		final String hql = "from SystemUser as model where 1=1 and model.email = ? and model.password = ? ";
		SystemUser u = (SystemUser) findUnique(hql, email, encodePassword);
		if (null == u) {
			return null;
		}
		return u;

	}

	public SystemUser getSystemUserByMD5Value(String value) {
		if(null == value){
			return null ;
		}
		String hql = "from SystemUser as model where 1=1 and model.md5Value = ? ";
		SystemUser u = (SystemUser)findUnique(hql, value);
		if (null != u) {
			return u;
		}
		return null;
	}

	public List<SystemUser> querySystemUserByName(String name) {
		try{
			String hql = "from SystemUser as model where 1=1 and model.username like ? " ;
			List<SystemUser> list = findByHql(hql, name) ;
			if(null != list){
				return list ;
			}
		}catch(Exception ex){
			ex.printStackTrace() ;
		}
		return null;
	}

}
