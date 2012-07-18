package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Favorite;
import ningbo.media.bean.enums.FavoriteType;
import ningbo.media.core.dao.BaseDao;

public interface FavoriteDao extends BaseDao<Favorite, Integer> {

	public List<Favorite> getListFavoriteById(String id,FavoriteType type);
	
	public List<Favorite> findFavoriteByDeviceForUser(String deviceId) ;
	
	public Favorite findFavoriteByType(String locationId,String id,FavoriteType type);
	
	public Favorite getFavoriteByUserId(String userId,String locationId) ;
}
