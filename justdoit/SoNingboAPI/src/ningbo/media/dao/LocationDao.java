package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Location;
import ningbo.media.core.dao.BaseDao;

public interface LocationDao extends BaseDao<Location, Integer>{

	public List<Location> queryLocationByName(String locationName) ;
}
