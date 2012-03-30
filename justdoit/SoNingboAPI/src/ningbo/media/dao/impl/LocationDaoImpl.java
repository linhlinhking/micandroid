package ningbo.media.dao.impl;

import java.util.List;

import ningbo.media.bean.Location;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LocationDao;

import org.springframework.stereotype.Repository;

@Repository("locationDao")
public class LocationDaoImpl extends BaseDaoImpl<Location, Integer> implements
		LocationDao {

	
	public LocationDaoImpl() {
		super(Location.class);
	}
	
	public List<Location> queryLocationByName(String locationName){
		if(locationName == null){
			return null ;
		}
		String hql = "from Location m where m.name_cn like ? " ;
		List<Location> list = this.findByHql(hql,true,locationName) ;
		return list ;
	}
}
