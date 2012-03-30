package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Favorite;
import ningbo.media.core.dao.BaseDao;

public interface FavoriteDao extends BaseDao<Favorite, Integer> {

	public Favorite findFavoriteById(String userId, String locationId);

	public Favorite findFavoriteByDeviceId(String deviceId, String locationId);

	public List<Favorite> getListFavoriteByUserId(Integer userId);

	public List<Favorite> getListFavoriteByDeviceId(String deviceId);
}
