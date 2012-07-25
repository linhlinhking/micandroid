package ningbo.media.service.impl;

import ningbo.media.bean.LocationExt;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationExtDao;
import ningbo.media.service.LocationExtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("locationExtService")
public class LocationExtServiceImpl extends
		BaseServiceImpl<LocationExt, Integer> implements LocationExtService {

	@Autowired
	public LocationExtServiceImpl(@Qualifier("locationExtDao")
	LocationExtDao locationExtDao) {
		super(locationExtDao);
	}

}
