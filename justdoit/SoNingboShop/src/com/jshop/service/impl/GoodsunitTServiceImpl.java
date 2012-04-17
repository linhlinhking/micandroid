package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GoodsunitTDaoImpl;
import com.jshop.entity.GoodsunitT;
import com.jshop.service.GoodsunitTService;
@Service("goodsunitTServiceImpl")
@Scope("prototype")
public class GoodsunitTServiceImpl implements GoodsunitTService {
	@Resource(name="goodsunitTDaoImpl")
	private GoodsunitTDaoImpl goodsunitTDaoImpl;


	public GoodsunitTDaoImpl getGoodsunitTDaoImpl() {
		return goodsunitTDaoImpl;
	}

	public void setGoodsunitTDaoImpl(GoodsunitTDaoImpl goodsunitTDaoImpl) {
		this.goodsunitTDaoImpl = goodsunitTDaoImpl;
	}

	public int delGoodsunit(String[] list) {
		return this.getGoodsunitTDaoImpl().delGoodsunit(list);
	}

	public int updateGoodsunit(GoodsunitT u) {
		return this.getGoodsunitTDaoImpl().updateGoodsunit(u);
	}

	public int addGoodsunit(GoodsunitT u) {
		return this.getGoodsunitTDaoImpl().addGoodsunit(u);
	}

	public int countfindAllGoodsunit() {
		return this.getGoodsunitTDaoImpl().countfindAllGoodsunit();
	}

	public List<GoodsunitT> findAllGoodsunit(int currentPage, int lineSize) {
		return this.getGoodsunitTDaoImpl().findAllGoodsunit(currentPage, lineSize);
	}

	public GoodsunitT findGoodsunitById(String unitid) {
		return this.getGoodsunitTDaoImpl().findGoodsunitById(unitid);
	}

	public List<GoodsunitT> findAllGoodsunitjson() {
		return this.getGoodsunitTDaoImpl().findAllGoodsunitjson();
	}

	public List<GoodsunitT> sortAllGoodsunit(int currentPage, int lineSize, String queryString) {

		return this.getGoodsunitTDaoImpl().sortAllGoodsunit(currentPage, lineSize, queryString);
	}
}
