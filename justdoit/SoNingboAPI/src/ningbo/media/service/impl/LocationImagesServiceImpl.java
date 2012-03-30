package ningbo.media.service.impl;

import ningbo.media.bean.LocationImages;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationImagesDao;
import ningbo.media.service.LocationImagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("locationImagesService")
public class LocationImagesServiceImpl extends BaseServiceImpl<LocationImages, Integer>
		implements LocationImagesService {

	@Autowired
	public LocationImagesServiceImpl(@Qualifier("locationImagesDao")LocationImagesDao  locationImagesDao) {
		super(locationImagesDao);
	}

}
