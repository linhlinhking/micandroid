package ningbo.media.service;

import ningbo.media.bean.TempUser;
import ningbo.media.core.service.BaseService;

public interface TempUserService extends BaseService<TempUser, Integer>{

	public boolean isExistsDeviceId(String deviceId) ;
}
