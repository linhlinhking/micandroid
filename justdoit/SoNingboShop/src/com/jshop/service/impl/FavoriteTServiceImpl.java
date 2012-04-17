package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.FavoriteTDaoImpl;
import com.jshop.entity.FavoriteT;
import com.jshop.service.FavoriteTService;
@Service("favoriteTServiceImpl")
@Scope("prototype")
public class FavoriteTServiceImpl implements FavoriteTService {
	@Resource(name="favoriteTDaoImpl")
	private FavoriteTDaoImpl favoriteTDaoImpl;

	
	public FavoriteTDaoImpl getFavoriteTDaoImpl() {
		return favoriteTDaoImpl;
	}

	public void setFavoriteTDaoImpl(FavoriteTDaoImpl favoriteTDaoImpl) {
		this.favoriteTDaoImpl = favoriteTDaoImpl;
	}

	public int delFavorite(String[] list) {
		return this.getFavoriteTDaoImpl().delFavorite(list);
	}

	public int addFavorite(FavoriteT f) {
		return this.getFavoriteTDaoImpl().addFavorite(f);
	}

	public int countfindAllFavoriteByUserid(String userid) {
		return this.getFavoriteTDaoImpl().countfindAllFavoriteByUserid(userid);
	}

	public List<FavoriteT> findAllFavoriteByUserid(String userid, int currentPage, int lineSize) {
		return this.getFavoriteTDaoImpl().findAllFavoriteByUserid(userid, currentPage, lineSize);
	}
}
