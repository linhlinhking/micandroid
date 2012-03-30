package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Favorite;
import ningbo.media.core.service.BaseService;

public interface FavoriteService extends BaseService<Favorite, Integer> {

	public Favorite findFavoriteById(String userId,String locationId) ;
	
	public Favorite findFavoriteByDeviceId(String deviceId,String locationId) ;
	
	public List<Favorite> getListFavoriteByUserId(Integer userId) ;
	
	public List<Favorite> getListFavoriteByDeviceId(String deviceId) ;
	
	public List<Favorite> findFavoriteByDeviceAndUser(String deviceId);
	
}
