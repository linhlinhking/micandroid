package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.Favorite;
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
		this.favoriteDao = favoriteDao ;
	}

	public Favorite findFavoriteById(String userId, String locationId) {
		return favoriteDao.findFavoriteById(userId, locationId);
	}

	public Favorite findFavoriteByDeviceId(String deviceId, String locationId) {
		return favoriteDao.findFavoriteByDeviceId(deviceId, locationId) ;
	}

	public List<Favorite> getListFavoriteByUserId(Integer userId) {
		return favoriteDao.getListFavoriteByUserId(userId);
	}

	public List<Favorite> getListFavoriteByDeviceId(String deviceId) {
		return favoriteDao.getListFavoriteByDeviceId(deviceId);
	}

	public List<Favorite> findFavoriteByDeviceForUser(String deviceId) {
		return favoriteDao.findFavoriteByDeviceForUser(deviceId) ;
	}
	
}
