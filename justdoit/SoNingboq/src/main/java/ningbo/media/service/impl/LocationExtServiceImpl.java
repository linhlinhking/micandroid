package ningbo.media.service.impl;

import javax.annotation.Resource;

import ningbo.media.admin.exception.DaoException;
import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.LocationExt;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationExtDao;
import ningbo.media.service.LocationExtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("locationExtService")
public class LocationExtServiceImpl extends
		BaseServiceImpl<LocationExt, Integer> implements LocationExtService {

	private Logger logger = LoggerFactory.getLogger(getClass()) ;
	
	@Resource
	private LocationExtDao locationExtDao ;
	
	@Autowired
	public LocationExtServiceImpl(@Qualifier("locationExtDao")
	LocationExtDao locationExtDao) {
		super(locationExtDao);
	}

	
	public LocationExt getLocationExtById(int id) throws ServiceException {
		try {
			return locationExtDao.getLocationExtById(id) ;
		} catch (DaoException e) {
			logger.error("Get Location'extend information Error.The id is " + id,e) ;
			return null ;
		}
	}

}
