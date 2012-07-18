package ningbo.media.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ningbo.media.bean.Favorite;
import ningbo.media.bean.enums.FavoriteType;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FavoriteDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("favoriteDao")
public class FavoriteDaoImpl extends BaseDaoImpl<Favorite, Integer> implements
		FavoriteDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public FavoriteDaoImpl() {
		super(Favorite.class);
	}

	public List<Favorite> getListFavoriteById(String id, FavoriteType type) {
		try {
			String hql = "";
			List<Favorite> list = new ArrayList<Favorite>();
			if (type == FavoriteType.REALUSER) {
				hql = "from Favorite model where 1=1 and model.userId = ? ";
				list = findByHql(hql, Integer.valueOf(id));

			} else if (type == FavoriteType.TEMPUSER) {
				hql = "from Favorite model where 1=1 and model.deviceId = ? ";
				list = findByHql(hql, id);
			} else {
				return null;
			}

			if (null == list || list.size() < 1) {
				return null;
			}
			return list;
		} catch (Exception ex) {
			logger.error("No Data for this id:" + id);
			return null;
		}

	}

	public List<Favorite> findFavoriteByDeviceForUser(String deviceId) {
		try {
			List<Favorite> listFav = null;
			String hql = "from Favorite model where 1=1 and model.userId is null and model.deviceId = ? ";
			listFav = findByHql(hql, deviceId);
			if (null == listFav || listFav.size() < 1) {
				return null;
			}
			return listFav;
		} catch (Exception ex) {
			logger
					.error("No Data for this DeviceId.(Throws by FavariteDaoImpl findFavoriteByDeviceForUser)");
			return null;
		}

	}

	public Favorite findFavoriteByType(String locationId, String id,
			FavoriteType type) {
		if ("".equals(id) || "".equals(locationId) || id == null
				|| locationId == null) {
			return null;
		}
		try {
			Favorite favorite = null;
			String hql = "";
			if (type == FavoriteType.REALUSER) {
				hql = "from Favorite model where 1=1 and model.userId = ? and model.locationId = ? ";
				favorite = (Favorite) findUnique(hql, Integer.valueOf(id),
						Integer.valueOf(locationId));
			} else if (type == FavoriteType.TEMPUSER) {
				hql = "from Favorite model where 1=1 and model.deviceId = ? and model.locationId = ? ";
				favorite = (Favorite) findUnique(hql, id, Integer
						.valueOf(locationId));
			} else {
				return null;
			}
			return favorite;
		} catch (Exception ex) {
			logger.error("No Data for this LocationId:" + locationId);
			return null;
		}
	}

	public Favorite getFavoriteByUserId(String userId, String locationId) {
		if (null == userId || null == locationId) {
			return null;
		}
		try {
			Favorite favorite = null;
			String hql = "from Favorite model where 1=1 and model.userId = ? and model.locationId = ? ";
			favorite = (Favorite) findUnique(hql, userId, locationId);
			return favorite;
		} catch (Exception ex) {
			logger.error("No Data for this favorite.{locationId:" + locationId
					+ ";userId:" + userId + "}");
			return null;
		}
	}

}
