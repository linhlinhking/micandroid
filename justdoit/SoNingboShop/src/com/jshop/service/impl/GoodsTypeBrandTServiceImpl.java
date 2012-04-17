package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GoodsTypeBrandTDaoImpl;
import com.jshop.entity.GoodsTypeBrandT;
import com.jshop.service.GoodsTypeBrandTService;
@Service("goodsTypeBrandTServiceImpl")
@Scope("prototype")
public class GoodsTypeBrandTServiceImpl implements GoodsTypeBrandTService {
	@Resource(name="goodsTypeBrandTDaoImpl")
	private GoodsTypeBrandTDaoImpl goodsTypeBrandTDaoImpl;


	public GoodsTypeBrandTDaoImpl getGoodsTypeBrandTDaoImpl() {
		return goodsTypeBrandTDaoImpl;
	}

	public void setGoodsTypeBrandTDaoImpl(GoodsTypeBrandTDaoImpl goodsTypeBrandTDaoImpl) {
		this.goodsTypeBrandTDaoImpl = goodsTypeBrandTDaoImpl;
	}

	public int delGoodsTypeBrand(String[] list) {
		return this.getGoodsTypeBrandTDaoImpl().delGoodsTypeBrand(list);
	}

	public int addGoodsTypeBrand(GoodsTypeBrandT gtbt) {
		return this.getGoodsTypeBrandTDaoImpl().addGoodsTypeBrand(gtbt);
	}

	public List<GoodsTypeBrandT> findAllGoodsTypeBrand(int currentPage, int lineSize) {
		return this.getGoodsTypeBrandTDaoImpl().findAllGoodsTypeBrand(currentPage, lineSize);
	}

	public int countfindAllGoodsTypeBrand() {
		return this.getGoodsTypeBrandTDaoImpl().countfindAllGoodsTypeBrand();
	}

	public GoodsTypeBrandT findGoodsTypeBrandByBrandid(String brandid, String goodsTypeId) {
		return this.getGoodsTypeBrandTDaoImpl().findGoodsTypeBrandByBrandid(brandid, goodsTypeId);
	}

	public List<GoodsTypeBrandT> sortAllGoodsTypeBrand(int currentPage, int lineSize, String queryString) {

		return this.getGoodsTypeBrandTDaoImpl().sortAllGoodsTypeBrand(currentPage, lineSize, queryString);
	}

	public int updateGoodsTypeBrandTname(String name, String goodsTypeId) {
		return this.getGoodsTypeBrandTDaoImpl().updateGoodsTypeBrandTname(name, goodsTypeId);
	}
	
	
}
