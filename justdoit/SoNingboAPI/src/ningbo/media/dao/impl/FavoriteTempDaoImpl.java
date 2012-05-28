package ningbo.media.dao.impl;

import ningbo.media.bean.FavoriteTemp;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FavoriteTempDao;

import org.springframework.stereotype.Repository;

@Repository("favoriteTempDao")
public class FavoriteTempDaoImpl extends BaseDaoImpl<FavoriteTemp, Integer> implements FavoriteTempDao {

	public FavoriteTempDaoImpl(){
		super(FavoriteTemp.class) ;
	}
}
