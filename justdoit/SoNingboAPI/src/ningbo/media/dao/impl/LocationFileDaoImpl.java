package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.LocationFile;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LocationFileDao;

@Repository("locationFileDao")
public class LocationFileDaoImpl extends BaseDaoImpl<LocationFile, Integer> implements LocationFileDao {

	public LocationFileDaoImpl(){
		super(LocationFile.class);
	}
}
