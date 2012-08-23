package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.TempUser;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.TempUserDao;

@Repository("tempUserDao")
public class TempUserDaoImpl extends BaseDaoImpl<TempUser, Integer> implements
		TempUserDao {

	public TempUserDaoImpl(){
		super(TempUser.class);
	}
	
}
