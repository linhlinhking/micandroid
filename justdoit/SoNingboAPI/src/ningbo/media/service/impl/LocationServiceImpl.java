package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.bean.Location;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationDao;
import ningbo.media.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("locationService")
public class LocationServiceImpl extends BaseServiceImpl<Location, Integer>
		implements LocationService {

	@Resource
	private LocationDao locationDao ;
	
	@Autowired
	public LocationServiceImpl(@Qualifier("locationDao")LocationDao  locationDao) {
		super(locationDao);
	}
	
	
	public List<Location> queryLocationByName(String locationName){
		return locationDao.queryLocationByName(locationName) ;
	}
	
	public Long getLocationCount(){
		return super.getTotalCount() ;
	}

}
