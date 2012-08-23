package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.LocationExt;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LocationExtDao;

@Repository("locationExtDao")
public class LocationExtDaoImpl extends BaseDaoImpl<LocationExt, Integer>
		implements LocationExtDao {

	public LocationExtDaoImpl(){
		super(LocationExt.class);
	}
}
