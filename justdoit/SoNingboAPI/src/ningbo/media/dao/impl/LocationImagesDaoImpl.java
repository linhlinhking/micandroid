package ningbo.media.dao.impl;

import ningbo.media.bean.LocationImages;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LocationImagesDao;

import org.springframework.stereotype.Repository;

@Repository("locationImagesDao")
public class LocationImagesDaoImpl extends BaseDaoImpl<LocationImages, Integer> implements
		LocationImagesDao {

	public LocationImagesDaoImpl() {
		super(LocationImages.class);
	}
}
