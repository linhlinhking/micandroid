package ningbo.media.service.impl;

import ningbo.media.bean.LocationProduct;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationProductDao;
import ningbo.media.service.LocationProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("locationProductService")
public class LocationProductServiceImpl extends
		BaseServiceImpl<LocationProduct, Integer> implements
		LocationProductService {

	@Autowired
	public LocationProductServiceImpl(
			@Qualifier("locationProductDao") LocationProductDao locationProductDao) {
		super(locationProductDao);
	}

}
