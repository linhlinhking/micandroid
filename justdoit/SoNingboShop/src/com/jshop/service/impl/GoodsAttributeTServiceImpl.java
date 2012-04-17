package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GoodsAttributeTDaoImpl;
import com.jshop.entity.GoodsAttributeT;
import com.jshop.service.GoodsAttributeTService;
@Service("goodsAttributeTServiceImpl")
@Scope("prototype")
public class GoodsAttributeTServiceImpl implements GoodsAttributeTService {
	@Resource(name="goodsAttributeTDaoImpl")
	private GoodsAttributeTDaoImpl goodsAttributeTDaoImpl;

	
	public GoodsAttributeTDaoImpl getGoodsAttributeTDaoImpl() {
		return goodsAttributeTDaoImpl;
	}

	public void setGoodsAttributeTDaoImpl(GoodsAttributeTDaoImpl goodsAttributeTDaoImpl) {
		this.goodsAttributeTDaoImpl = goodsAttributeTDaoImpl;
	}

	public int updateGoodsAttributeT(GoodsAttributeT gat) {
		return this.getGoodsAttributeTDaoImpl().updateGoodsAttributeT(gat);
	}

	public int addGoodsAttributeT(GoodsAttributeT gat) {
		return this.getGoodsAttributeTDaoImpl().addGoodsAttributeT(gat);
	}

	public GoodsAttributeT findGoodsAttributeTBygoodstypeId(String goodstypeid) {
		return this.getGoodsAttributeTDaoImpl().findGoodsAttributeTBygoodstypeId(goodstypeid);
	}

	public int countfindAllGoodsAttributeT() {
		return this.getGoodsAttributeTDaoImpl().countfindAllGoodsAttributeT();
	}

	public List<GoodsAttributeT> findAllGoodsAttributeT(int currentPage, int lineSize) {
		return this.getGoodsAttributeTDaoImpl().findAllGoodsAttributeT(currentPage, lineSize);
	}

	public int delGoodsAttributeT(String[] list) {
		return this.getGoodsAttributeTDaoImpl().delGoodsAttributeT(list);
	}

	public List<GoodsAttributeT> findGoodsAttributeTByGoodsTypeName(String goodsTypeName) {
		return this.getGoodsAttributeTDaoImpl().findGoodsAttributeTByGoodsTypeName(goodsTypeName);
	}

	public List<GoodsAttributeT> findGoodsAttributeTBygoodsTypeId(String goodsTypeId) {
		return this.getGoodsAttributeTDaoImpl().findGoodsAttributeTBygoodsTypeId(goodsTypeId);
	}

	public List<GoodsAttributeT> sortAllGoodsAttributeT(int currentPage, int lineSize, String queryString) {

		return this.getGoodsAttributeTDaoImpl().sortAllGoodsAttributeT(currentPage, lineSize, queryString);
	}

	public int updateGoodsAttributeTgoodsTypeName(String goodsTypeName, String goodsTypeId) {
		return this.getGoodsAttributeTDaoImpl().updateGoodsAttributeTgoodsTypeName(goodsTypeName, goodsTypeId);
	}
	
	
	
	
}
