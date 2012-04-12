package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Location;
import ningbo.media.core.service.BaseService;

public interface LocationService extends BaseService<Location,Integer> {

	public List<Location> queryLocationByName(String locationName) ;
	
	public List<Location> queryLocationByPage(int pageNo,int pageSize) ;
}
