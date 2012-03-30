package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.TempUser;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.TempUserDao;
import ningbo.media.service.TempUserService;

@Service("tempUserService")
public class TempUserServiceImpl extends BaseServiceImpl<TempUser, Integer>
		implements TempUserService {

	
	
	@Autowired(required = false)
	public TempUserServiceImpl(@Qualifier("tempUserDao") TempUserDao tempUserDao) {
		super(tempUserDao);
	}

	public boolean isExistsDeviceId(String deviceId) {
		boolean flag = false ;
		String hql = "from TempUser as model where model.deviceId = ? " ;
		TempUser tu = (TempUser)super.findUnique(hql, deviceId) ;
		if(tu != null){
			flag = true ;
		}
		return flag;
	}

}
