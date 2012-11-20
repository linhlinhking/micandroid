package ningbo.media.service;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.LocationExt;
import ningbo.media.core.service.BaseService;

public interface LocationExtService extends BaseService<LocationExt, Integer> {

	public LocationExt getLocationExtById(int id) throws ServiceException ;
}
