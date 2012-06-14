package ningbo.media.service.impl;

import ningbo.media.bean.LocationFile;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationFileDao;
import ningbo.media.service.LocationFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("locationFileService")
public class LocationFileServiceImpl extends BaseServiceImpl<LocationFile,Integer> implements LocationFileService{

	@Autowired
	public LocationFileServiceImpl(@Qualifier("locationFileDao")LocationFileDao locationFileDao){
		super(locationFileDao);
	}

	
}
