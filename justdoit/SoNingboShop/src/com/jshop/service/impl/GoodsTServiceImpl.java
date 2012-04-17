package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GoodsTDaoImpl;
import com.jshop.entity.GoodsT;
import com.jshop.service.GoodsTService;
@Service("goodsTServiceImpl")
@Scope("prototype")
public class GoodsTServiceImpl implements GoodsTService {
	@Resource(name="goodsTDaoImpl")
	private GoodsTDaoImpl goodsTDaoImpl;

	
	public GoodsTDaoImpl getGoodsTDaoImpl() {
		return goodsTDaoImpl;
	}

	public void setGoodsTDaoImpl(GoodsTDaoImpl goodsTDaoImpl) {
		this.goodsTDaoImpl = goodsTDaoImpl;
	}
	
	public int delGoods(String[] list, String creatorid) {
		return getGoodsTDaoImpl().delGoods(list, creatorid);
	}

	public int updateGoods(GoodsT g) {
		return getGoodsTDaoImpl().updateGoods(g);
	}

	public int updateGoodsSaleState(String[] goodsid, String salestate, String creatorid) {
		return getGoodsTDaoImpl().updateGoodsSaleState(goodsid, salestate, creatorid);
	}

	public int updateGoodsbargainprice(String[] goodsid, String bargainprice, String creatorid) {
		return getGoodsTDaoImpl().updateGoodsbargainprice(goodsid, bargainprice, creatorid);
	}

	public int updateGoodshotsale(String[] goodsid, String hotsale, String creatorid) {
		return getGoodsTDaoImpl().updateGoodshotsale(goodsid, hotsale, creatorid);
	}

	public int updateGoodsreadcount(String goodsid) {
		return getGoodsTDaoImpl().updateGoodsreadcount(goodsid);
	}

	public int updateGoodsrecommended(String[] goodsid, String recommended, String creatorid) {
		return getGoodsTDaoImpl().updateGoodsrecommended(goodsid, recommended, creatorid);
	}

	public int updateGoodsisNew(String[] goodsid, String isNew, String creatorid) {
		return getGoodsTDaoImpl().updateGoodsisNew(goodsid, isNew, creatorid);
	}

	public int updateGoodsismobileplatformgoods(String[] goodsid, String ismobileplatformgoods, String creatorid) {
		return getGoodsTDaoImpl().updateGoodsismobileplatformgoods(goodsid, ismobileplatformgoods, creatorid);
	}

	public int updateGoodsrelatedfit(String goodsid, String list) {
		return getGoodsTDaoImpl().updateGoodsrelatedfit(goodsid, list);
	}

	public int updateGoodsrelatedgoods(String goodsid, String list) {
		return getGoodsTDaoImpl().updateGoodsrelatedgoods(goodsid, list);
	}

	public int updateGoodsreplycount(String goodsid) {
		return getGoodsTDaoImpl().updateGoodsreplycount(goodsid);
	}

	public int updateGoodssortid(String goodsid, String goodsortid) {
		return getGoodsTDaoImpl().updateGoodssortid(goodsid, goodsortid);
	}

	public int updateGoodssortname(String goodsid, String goodssortname) {
		return getGoodsTDaoImpl().updateGoodssortname(goodsid, goodssortname);
	}

	public int updateSort(String goodsid, Integer sort) {
		return getGoodsTDaoImpl().updateSort(goodsid, sort);
	}

	public int updateFiveGoodsState(String[] goodsid, String recommended, String hotsale, String bargainprice, String isNew, String ismobileplatformgoods) {
		return getGoodsTDaoImpl().updateFiveGoodsState(goodsid, recommended, hotsale, bargainprice, isNew, ismobileplatformgoods);
	}

	public int addGoods(GoodsT g) {
		return getGoodsTDaoImpl().addGoods(g);
	}

	public int countAllGoods(String creatorid) {
		return getGoodsTDaoImpl().countAllGoods(creatorid);
	}

	public List<GoodsT> findAllGoods(int currentPage, int lineSize, String creatorid) {
		return getGoodsTDaoImpl().findAllGoods(currentPage, lineSize, creatorid);
	}

	public GoodsT findGoodsById(String goodsid) {
		return getGoodsTDaoImpl().findGoodsById(goodsid);
	}

	public List<GoodsT> findGoodsByKeyword(String keywordid, int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findGoodsByKeyword(keywordid, currentPage, lineSize);
	}

	public int countfindGoodsByKeyword(String keywordid) {
		return getGoodsTDaoImpl().countfindGoodsByKeyword(keywordid);
	}

	public List<GoodsT> findGoodsByLtypeid(String ltypeid, String salestate, int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findGoodsByLtypeid(ltypeid, salestate, currentPage, lineSize);
	}

	public List<GoodsT> findGoodsByNavid(String navid, String salestate, final int currentPage, final int lineSize) {
		return getGoodsTDaoImpl().findGoodsByNavid(navid, salestate, currentPage, lineSize);
	}

	public int countfindGoodsByNavid(String navid, String salestate) {
		return getGoodsTDaoImpl().countfindGoodsByNavid(navid, salestate);
	}

	public int countfindGoodsByLtypeid(String ltypeid, String salestate) {
		return getGoodsTDaoImpl().countfindGoodsByLtypeid(ltypeid, salestate);
	}

	public List<GoodsT> findGoodsByStypeid(String stypeid, String salestate) {
		return getGoodsTDaoImpl().findGoodsByStypeid(stypeid, salestate);
	}

	public List<GoodsT> findGoodsBybrand(String brand) {
		return getGoodsTDaoImpl().findGoodsBybrand(brand);
	}

	public List<GoodsT> findGoodsBymodel(String model) {
		return getGoodsTDaoImpl().findGoodsBymodel(model);
	}

	public List<GoodsT> findGoodsByprice(String price) {
		return getGoodsTDaoImpl().findGoodsByprice(price);
	}

	public List<GoodsT> findGoodsByusersetnum(String usersetnum) {
		return getGoodsTDaoImpl().findGoodsByusersetnum(usersetnum);
	}

	public List<GoodsT> findGoodsForoptiontransferselect(String navid, String ltypeid, String stypeid, String goodsname) {
		return getGoodsTDaoImpl().findGoodsForoptiontransferselect(navid, ltypeid, stypeid, goodsname);
	}

	public List<GoodsT> findAllGoodstWithoutSplitpage() {
		return getGoodsTDaoImpl().findAllGoodstWithoutSplitpage();
	}

	public List<GoodsT> findGoodsLimitByGoodsType(String nlstypeid, int limit) {
		return getGoodsTDaoImpl().findGoodsLimitByGoodsType(nlstypeid, limit);
	}

	public List<GoodsT> findSamepriceGoods(int limit, double minprice, double maxprice, String goodsid) {
		return getGoodsTDaoImpl().findSamepriceGoods(limit, minprice, maxprice, goodsid);
	}

	public List<GoodsT> findGoodsByGoodsname(int currentPage, int lineSize, String goodsname) {
		return getGoodsTDaoImpl().findGoodsByGoodsname(currentPage, lineSize, goodsname);
	}

	public List<GoodsT> findMoreGoodsByGoodsType(String nlstypeid, int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findMoreGoodsByGoodsType(nlstypeid, currentPage, lineSize);
	}

	public int countfindMoreGoodsByGoodsType(String nlstypeid) {
		return getGoodsTDaoImpl().countfindMoreGoodsByGoodsType(nlstypeid);
	}

	public int countfindSearchGoods(String goodsname) {
		return getGoodsTDaoImpl().countfindSearchGoods(goodsname);
	}

	public List<GoodsT> findSearchGoods(String goodsname, int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findSearchGoods(goodsname, currentPage, lineSize);
	}

	public int countfindAllGoodslistMore(String salestate) {
		return getGoodsTDaoImpl().countfindAllGoodslistMore(salestate);
	}

	public List<GoodsT> findAllGoodslistMore(int currentPage, int lineSize, String salestate) {
		return getGoodsTDaoImpl().findAllGoodslistMore(currentPage, lineSize, salestate);
	}

	public int countfindMoreBargainPriceGoods() {
		return getGoodsTDaoImpl().countfindMoreBargainPriceGoods();
	}

	public int countfindMoreBargainPriceGoodsByGoodsType(String nlstypeid) {
		return getGoodsTDaoImpl().countfindMoreBargainPriceGoodsByGoodsType(nlstypeid);
	}

	public int countfindMoreHotSaleGoodsByGoodsType(String nlstypeid) {
		return getGoodsTDaoImpl().countfindMoreHotSaleGoodsByGoodsType(nlstypeid);
	}

	public int countfindMoreRecommendedGoodsByGoodsType(String nlstypeid) {
		return getGoodsTDaoImpl().countfindMoreRecommendedGoodsByGoodsType(nlstypeid);
	}

	public List<GoodsT> findMoreBargainPriceGoods(int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findMoreBargainPriceGoods(currentPage, lineSize);
	}

	public List<GoodsT> findMoreBargainPriceGoodsByGoodsType(String nlstypeid, int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findMoreBargainPriceGoodsByGoodsType(nlstypeid, currentPage, lineSize);
	}

	public List<GoodsT> findMoreHotSaleGoodsByGoodsType(String nlstypeid, int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findMoreHotSaleGoodsByGoodsType(nlstypeid, currentPage, lineSize);
	}

	public List<GoodsT> findMoreRecommendedGoodsByGoodsType(String nlstypeid, int currentPage, int lineSize) {
		return getGoodsTDaoImpl().findMoreRecommendedGoodsByGoodsType(nlstypeid, currentPage, lineSize);
	}

	public List<GoodsT> findAllGoodsByismobileplatformgoods(int currentPage, int lineSize, String creatorid) {
		return getGoodsTDaoImpl().findAllGoodsByismobileplatformgoods(currentPage, lineSize, creatorid);
	}

	public int countfindAllGoodsByismobileplatformgoods(String creatorid) {
		return getGoodsTDaoImpl().countfindAllGoodsByismobileplatformgoods(creatorid);
	}

	public List<GoodsT> findAllGoodsForImgT() {
		return getGoodsTDaoImpl().findAllGoodsForImgT();
	}

	public List<GoodsT> findAllGoodsBynavid(String navid, String salestate) {
		return getGoodsTDaoImpl().findAllGoodsBynavid(navid, salestate);
	}

	public int updateHtmlPath(String goodsid, String htmlPath) {
		return getGoodsTDaoImpl().updateHtmlPath(goodsid, htmlPath);
	}

	public int updatecommentsumBygoodsid(String goodsid, int totalcomment) {
		return getGoodsTDaoImpl().updatecommentsumBygoodsid(goodsid, totalcomment);
	}

	public int updatestarsumBygoodsid(String goodsid, int star) {
		return getGoodsTDaoImpl().updatestarsumBygoodsid(goodsid, star);
	}

	public List<GoodsT> finaAllGoodsT(String salestate) {
		return getGoodsTDaoImpl().finaAllGoodsT(salestate);
	}

	public List<GoodsT> findGoodsByLtypeid(String ltypeid, String salestate) {
		return getGoodsTDaoImpl().findGoodsByLtypeid(ltypeid, salestate);
	}

	public int updatestarusersumBygoodsid(String goodsid, int staruser) {
		return getGoodsTDaoImpl().updatestarusersumBygoodsid(goodsid, staruser);
	}

	public List<GoodsT> findAllGoodsBynavidorderbyParams(String navid, String salestate, String sales, String memberprice, String totalcomment, String bargainprice, String hotsale, String recommended, String isNew, String value) {
		return getGoodsTDaoImpl().findAllGoodsBynavidorderbyParams(navid, salestate, sales, memberprice, totalcomment, bargainprice, hotsale, recommended, isNew, value);
	}

	public List<GoodsT> findAllGoodsByLtypeidorderbyParams(String ltypeid, String salestate, String sales, String memberprice, String totalcomment, String bargainprice, String hotsale, String recommended, String isNew, String value) {
		return getGoodsTDaoImpl().findAllGoodsByLtypeidorderbyParams(ltypeid, salestate, sales, memberprice, totalcomment, bargainprice, hotsale, recommended, isNew, value);
	}

	public List<GoodsT> sortAllGoods(int currentPage, int lineSize, String creatorid, String queryString) {
		return this.getGoodsTDaoImpl().sortAllGoods(currentPage, lineSize, creatorid, queryString);
	}

	public int updateGoodsTypeNameBygoodsTypeId(String goodsTypeName, String goodsTypeId) {
		return this.getGoodsTDaoImpl().updateGoodsTypeNameBygoodsTypeId(goodsTypeName, goodsTypeId);
	}

}
