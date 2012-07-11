package ningbo.media.dao;

import ningbo.media.bean.FavoriteTemp;
import ningbo.media.core.dao.BaseDao;

public interface FavoriteTempDao extends BaseDao<FavoriteTemp, Integer> {

	public FavoriteTemp getFavoriteTempByDeviceId(Integer deviceId,Integer locationId) ;
}
