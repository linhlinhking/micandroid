package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Favorite;
import ningbo.media.bean.enums.FavoriteType;
import ningbo.media.core.service.BaseService;

public interface FavoriteService extends BaseService<Favorite, Integer> {

	public List<Favorite> getListFavoriteById(String id,FavoriteType type);
	
	public List<Favorite> findFavoriteByDeviceForUser(String deviceId);
	
	public Favorite findFavoriteByType(String locationId,String id,FavoriteType type);
	
	public Favorite getFavoriteByUserId(String userId,String locationId) ;
	
}
