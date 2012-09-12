package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Location;
import ningbo.media.core.dao.BaseDao;
import ningbo.media.core.page.Pagination;

public interface LocationDao extends BaseDao<Location, Integer>{

	public List<Location> queryLocationByName(String locationName) ;
	
	public Pagination<Location> queryLocationByPage(int pageNo,int pageSize) ;
	
	public List<Location> queryLocationsById(Integer categoryId) ;
	
	public Pagination<Location> getAllByPage(int pageNo,int pageSize);
}
