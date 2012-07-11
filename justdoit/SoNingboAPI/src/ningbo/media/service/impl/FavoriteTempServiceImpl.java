package ningbo.media.service.impl;

import javax.annotation.Resource;

import ningbo.media.bean.FavoriteTemp;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FavoriteTempDao;
import ningbo.media.service.FavoriteTempService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("favoriteTempService")
public class FavoriteTempServiceImpl extends
		BaseServiceImpl<FavoriteTemp, Integer> implements FavoriteTempService {

	@Resource
	private FavoriteTempDao favoriteTempDao ;
	
	@Autowired
	public FavoriteTempServiceImpl(@Qualifier("favoriteTempDao")
	FavoriteTempDao favoriteTempDao) {
		super(favoriteTempDao);
	}
	
	public FavoriteTemp getFavoriteTempByDeviceId(Integer deviceId,Integer locationId){
		return favoriteTempDao.getFavoriteTempByDeviceId(deviceId, locationId) ;
	}
}
