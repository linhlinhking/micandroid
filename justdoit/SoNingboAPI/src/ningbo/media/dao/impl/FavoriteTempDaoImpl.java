package ningbo.media.dao.impl;

import ningbo.media.bean.FavoriteTemp;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FavoriteTempDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("favoriteTempDao")
public class FavoriteTempDaoImpl extends BaseDaoImpl<FavoriteTemp, Integer>
		implements FavoriteTempDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public FavoriteTempDaoImpl() {
		super(FavoriteTemp.class);
	}

	public FavoriteTemp getFavoriteTempByDeviceId(String deviceId,
			Integer locationId) {
		if (null == deviceId || null == locationId) {
			return null;
		}
		try {
			FavoriteTemp favorite = null;
			String hql = "from Favorite model where 1=1 and model.userId = ? and model.locationId = ? ";
			favorite = (FavoriteTemp) findUnique(hql, deviceId, locationId);
			return favorite;
		} catch (Exception ex) {
			logger.error("No Data for the template favorite.{locationId:"
					+ locationId + ";userId:" + deviceId + "}");
			return null;
		}
	}
}
