package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.LocationProduct;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LocationProductDao;

@Repository("locationProductDao")
public class LocationProductDaoImpl extends
		BaseDaoImpl<LocationProduct, Integer> implements LocationProductDao {

	public LocationProductDaoImpl() {
		super(LocationProduct.class);
	}
}
