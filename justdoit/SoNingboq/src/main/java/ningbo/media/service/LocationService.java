package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Location;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.service.BaseService;
import ningbo.media.data.entity.LocationDetail;

public interface LocationService extends BaseService<Location,Integer> {

	public List<Location> queryLocationsById(Integer categoryId);
	
	public List<Location> queryLocationByName(String locationName) ;
	
	public List<Location> queryLocationByPage(int pageNo,int pageSize) ;
	
	public Location queryLocationByMd5(String md5Value) ;
	
	public List<LocationDetail> queryLoctionsByLat(Double latitude,Double longitude,Double distance) ;
	
	public Pagination<Location> getAllByPage(int pageNo,int pageSize);
	
	public Pagination<Location> getLocationsById(int pageNo,int pageSize,int categoryId);
	
	
}
