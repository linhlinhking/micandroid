package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.PlaceoforiginTDaoImpl;
import com.jshop.entity.PlaceoforiginT;
import com.jshop.service.PlaceoforiginTService;

@Service("placeoforiginTServiceImpl")
@Scope("prototype")
public class PlaceoforiginTServiceImpl implements PlaceoforiginTService {
	@Resource(name="placeoforiginTDaoImpl")
	private PlaceoforiginTDaoImpl placeoforiginTDaoImpl;

	
	public PlaceoforiginTDaoImpl getPlaceoforiginTDaoImpl() {
		return placeoforiginTDaoImpl;
	}

	public void setPlaceoforiginTDaoImpl(PlaceoforiginTDaoImpl placeoforiginTDaoImpl) {
		this.placeoforiginTDaoImpl = placeoforiginTDaoImpl;
	}

	public int delPlaceoforigint(String[] list) {
		return this.getPlaceoforiginTDaoImpl().delPlaceoforigint(list);
	}

	public int updatePlaceoforigint(PlaceoforiginT p) {
		return this.getPlaceoforiginTDaoImpl().updatePlaceoforigint(p);
	}

	public int addPlaceoforigint(PlaceoforiginT p) {
		return this.getPlaceoforiginTDaoImpl().addPlaceoforigint(p);
	}

	public int countfindAllPlaceoforigint() {
		return this.getPlaceoforiginTDaoImpl().countfindAllPlaceoforigint();
	}

	public List<PlaceoforiginT> findAllPlaceoforigint(int currentPage, int lineSize) {
		return this.getPlaceoforiginTDaoImpl().findAllPlaceoforigint(currentPage, lineSize);
	}

	public PlaceoforiginT findPlaceoforigintById(String placeid) {
		return this.getPlaceoforiginTDaoImpl().findPlaceoforigintById(placeid);
	}

	public List<PlaceoforiginT> findAllPlaceoforigintjson() {
		return this.getPlaceoforiginTDaoImpl().findAllPlaceoforigintjson();
	}
}
