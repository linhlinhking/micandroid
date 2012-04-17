package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GoodsCategoryTDaoImpl;
import com.jshop.entity.GoodsCategoryT;
import com.jshop.service.GoodsCategoryTService;

@Service("goodsCategoryTServiceImpl")
@Scope("prototype")
public class GoodsCategoryTServiceImpl implements GoodsCategoryTService {
	@Resource(name="goodsCategoryTDaoImpl")
	private GoodsCategoryTDaoImpl goodsCategoryTDaoImpl;

	public GoodsCategoryTDaoImpl getGoodsCategoryTDaoImpl() {
		return goodsCategoryTDaoImpl;
	}

	public void setGoodsCategoryTDaoImpl(GoodsCategoryTDaoImpl goodsCategoryTDaoImpl) {
		this.goodsCategoryTDaoImpl = goodsCategoryTDaoImpl;
	}

	public int addGoodsCategory(GoodsCategoryT gct) {
		return this.getGoodsCategoryTDaoImpl().addGoodsCategory(gct);
	}

	public GoodsCategoryT findPathParentIdByParentId(String parentId) {
		return this.getGoodsCategoryTDaoImpl().findPathParentIdByParentId(parentId);
	}

	public List<GoodsCategoryT> findGoodsCategoryByGrade(String grade, String state) {
		return this.getGoodsCategoryTDaoImpl().findGoodsCategoryByGrade(grade, state);
	}

	public int checkGoodscategoryName(String name) {
		return this.getGoodsCategoryTDaoImpl().checkGoodscategoryName(name);
	}

	public int checkGoodscategorySign(String sign) {
		return this.getGoodsCategoryTDaoImpl().checkGoodscategorySign(sign);
	}

	public int countfindAllGoodsCategoryT(String state) {
		return this.getGoodsCategoryTDaoImpl().countfindAllGoodsCategoryT(state);
	}

	public int countfindAllGoodsCategoryTByGrade(String grade, String state) {
		return this.getGoodsCategoryTDaoImpl().countfindAllGoodsCategoryTByGrade(grade, state);
	}

	public int delGoodscategoryT(String goodsCategoryTid, String state) {
		return this.getGoodsCategoryTDaoImpl().delGoodscategoryT(goodsCategoryTid, state);
	}

	public List<GoodsCategoryT> findAllGoodsCategoryT(int currentPage, int lineSize, String state) {
		return this.getGoodsCategoryTDaoImpl().findAllGoodsCategoryT(currentPage, lineSize, state);
	}

	public List<GoodsCategoryT> findAllGoodsCategoryTByGrade(int currentPage, int lineSize, String grade, String state) {
		return this.getGoodsCategoryTDaoImpl().findAllGoodsCategoryTByGrade(currentPage, lineSize, grade, state);
	}

	public int updateGoodscategoryT(GoodsCategoryT gct) {
		return this.getGoodsCategoryTDaoImpl().updateGoodscategoryT(gct);
	}

	public GoodsCategoryT findGoodscategoryBygoodscategoryId(String goodsCategoryTid) {
		return this.getGoodsCategoryTDaoImpl().findGoodscategoryBygoodscategoryId(goodsCategoryTid);
	}

	public int checkGoodscategoryNamewithoutMe(String goodsCategoryTid, String name) {
		return this.getGoodsCategoryTDaoImpl().checkGoodscategoryNamewithoutMe(goodsCategoryTid, name);
	}

	public int checkGoodscategorySignwithoutMe(String goodsCategoryTid, String sign) {
		return this.getGoodsCategoryTDaoImpl().checkGoodscategorySignwithoutMe(goodsCategoryTid, sign);
	}

	public List<GoodsCategoryT> findGoodscategoryByparentId(String state, String parentId) {
		return this.getGoodsCategoryTDaoImpl().findGoodscategoryByparentId(state, parentId);
	}

	public List<GoodsCategoryT> findGoodscategoryByparentIdnull(String state) {
		return this.getGoodsCategoryTDaoImpl().findGoodscategoryByparentIdnull(state);
	}

	public List<GoodsCategoryT> findAllGoodsCategoryBycreatorid(String creatorid) {
		return this.getGoodsCategoryTDaoImpl().findAllGoodsCategoryBycreatorid(creatorid);
	}

	public int updateHtmlPath(String goodsCategoryTid, String htmlpath) {
		return this.getGoodsCategoryTDaoImpl().updateHtmlPath(goodsCategoryTid, htmlpath);
	}

	public List<GoodsCategoryT> findAllGoodsCategoryT(String state) {
		return this.getGoodsCategoryTDaoImpl().findAllGoodsCategoryT(state);
	}

	public List<GoodsCategoryT> sortAllGoodsCategoryT(int currentPage, int lineSize, String state, String queryString) {

		return this.getGoodsCategoryTDaoImpl().sortAllGoodsCategoryT(currentPage, lineSize, state, queryString);
	}
}
