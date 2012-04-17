package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GoodsTypeTNDaoImpl;
import com.jshop.entity.GoodsTypeTN;
import com.jshop.service.GoodsTypeTNService;
@Service("goodsTypeTNServiceImpl")
@Scope("prototype")
public class GoodsTypeTNServiceImpl implements GoodsTypeTNService {
	@Resource(name="goodsTypeTNDaoImpl")
	private GoodsTypeTNDaoImpl goodsTypeTNDaoImpl;

	

	public GoodsTypeTNDaoImpl getGoodsTypeTNDaoImpl() {
		return goodsTypeTNDaoImpl;
	}

	public void setGoodsTypeTNDaoImpl(GoodsTypeTNDaoImpl goodsTypeTNDaoImpl) {
		this.goodsTypeTNDaoImpl = goodsTypeTNDaoImpl;
	}

	public int updateGoodsTypeTN(GoodsTypeTN gtn) {
		return this.getGoodsTypeTNDaoImpl().updateGoodsTypeTN(gtn);
	}

	public int addGoodsTypeTN(GoodsTypeTN gtn) {
		return this.getGoodsTypeTNDaoImpl().addGoodsTypeTN(gtn);
	}

	public int countfindAllGoodsTypeTN() {
		return this.getGoodsTypeTNDaoImpl().countfindAllGoodsTypeTN();
	}

	public List<GoodsTypeTN> findAllGoodsTypeTN(int currentPage, int lineSize) {
		return this.getGoodsTypeTNDaoImpl().findAllGoodsTypeTN(currentPage, lineSize);
	}

	public List<GoodsTypeTN> findGoodsTypeTNById(String goodsTypeId) {
		return this.getGoodsTypeTNDaoImpl().findGoodsTypeTNById(goodsTypeId);
	}

	public int delGoodsTypeTN(String[] list) {
		return this.getGoodsTypeTNDaoImpl().delGoodsTypeTN(list);
	}

	public List<GoodsTypeTN> findAllGoodsTypeTNNopage() {
		return this.getGoodsTypeTNDaoImpl().findAllGoodsTypeTNNopage();
	}

	public List<GoodsTypeTN> sortAllGoodsTypeTN(int currentPage, int lineSize, String queryString) {

		return this.getGoodsTypeTNDaoImpl().sortAllGoodsTypeTN(currentPage, lineSize, queryString);
	}
}
