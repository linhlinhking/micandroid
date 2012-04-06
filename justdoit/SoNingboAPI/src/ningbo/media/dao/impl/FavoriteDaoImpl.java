package ningbo.media.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Favorite;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FavoriteDao;

@Repository("favoriteDao")
public class FavoriteDaoImpl extends BaseDaoImpl<Favorite, Integer> implements
		FavoriteDao {

	public FavoriteDaoImpl() {
		super(Favorite.class);
	}

	public Favorite findFavoriteById(String userId, String locationId) {

		final String hql = "from Favorite model where 1=1 and model.userId = ? and model.locationId = ? ";
		Favorite favorite = null;
		if ("".equals(userId) || "".equals(locationId) || userId == null
				|| locationId == null) {
			return null;
		}
		try {
			favorite = (Favorite) findUnique(hql, Integer.valueOf(userId),
					Integer.valueOf(locationId));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return favorite;

	}

	public Favorite findFavoriteByDeviceId(String deviceId, String locationId) {
		final String hql = "from Favorite model where 1=1 and model.deviceId = ? and model.locationId = ? ";
		Favorite favorite = null;
		if ("".equals(deviceId) || "".equals(locationId) || deviceId == null
				|| locationId == null) {
			return null;
		}
		try {
			favorite = (Favorite) findUnique(hql, deviceId,
					Integer.valueOf(locationId));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return favorite;
	}

	public List<Favorite> getListFavoriteByUserId(Integer userId) {
		final String hql = "from Favorite model where 1=1 and model.userId = ? ";
		List<Favorite> list = findByHql(hql, userId);
		if (null == list || list.size() < 1) {
			return null;
		}
		return list;
	}

	public List<Favorite> getListFavoriteByDeviceId(String deviceId) {
		final String hql = "from Favorite model where 1=1 and model.deviceId = ? ";
		List<Favorite> list = findByHql(hql, deviceId);
		if (null == list || list.size() < 1) {
			return null;
		}
		return list;
	}

	public List<Favorite> findFavoriteByDeviceForUser(String deviceId) {
		List<Favorite> listFav = null;
		String hql = "from Favorite model where 1=1 and model.userId is null and model.deviceId = ? ";
		listFav = findByHql(hql, deviceId);
		if (null == listFav || listFav.size() < 1) {
			return null;
		}
		return listFav;
	}
	
	
}
