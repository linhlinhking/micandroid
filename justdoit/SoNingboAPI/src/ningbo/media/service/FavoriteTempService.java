package ningbo.media.service;

import ningbo.media.bean.FavoriteTemp;
import ningbo.media.core.service.BaseService;

public interface FavoriteTempService extends BaseService<FavoriteTemp, Integer> {

	public FavoriteTemp getFavoriteTempByDeviceId(String deviceId,Integer locationId) ;
}
