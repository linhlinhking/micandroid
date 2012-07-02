package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.Favorite;
import ningbo.media.bean.enums.FavoriteType;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FavoriteDao;
import ningbo.media.service.FavoriteService;

@Service("favoriteService")
public class FavoriteServiceImpl extends BaseServiceImpl<Favorite, Integer>
		implements FavoriteService {

	@Resource
	private FavoriteDao favoriteDao ;
	
	@Autowired
	public FavoriteServiceImpl(@Qualifier("favoriteDao")FavoriteDao favoriteDao) {
		super(favoriteDao);
	}



	public List<Favorite> findFavoriteByDeviceForUser(String deviceId) {
		return favoriteDao.findFavoriteByDeviceForUser(deviceId) ;
	}


	public Favorite findFavoriteByType(String locationId, String id,
			FavoriteType type) {
		return favoriteDao.findFavoriteByType(locationId, id, type);
	}



	public List<Favorite> getListFavoriteById(String id, FavoriteType type) {
		return favoriteDao.getListFavoriteById(id, type);
	}



	public Favorite getFavoriteByUserId(Integer userId, Integer locationId) {
		return favoriteDao.getFavoriteByUserId(userId, locationId);
	}
	
}
