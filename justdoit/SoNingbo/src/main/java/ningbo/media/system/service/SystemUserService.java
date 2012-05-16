package ningbo.media.system.service;

import java.util.Date;

import ningbo.media.core.service.BaseService;
import ningbo.media.entity.SystemUser;

public interface SystemUserService extends BaseService<SystemUser, Long> {

	public SystemUser getUserByLoginName(String loginName);

	public void updateLastLoginDate(Date lastDate, String username)throws Exception ;

}
