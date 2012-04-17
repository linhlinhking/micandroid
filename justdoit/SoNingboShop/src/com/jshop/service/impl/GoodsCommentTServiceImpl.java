package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GoodsCommentTDaoImpl;
import com.jshop.entity.GoodsCommentT;
import com.jshop.service.GoodsCommentTService;
@Service("goodsCommentTServiceImpl")
@Scope("prototype")
public class GoodsCommentTServiceImpl implements GoodsCommentTService {
	@Resource(name="goodsCommentTDaoImpl")
	private GoodsCommentTDaoImpl goodsCommentTDaoImpl;


	public GoodsCommentTDaoImpl getGoodsCommentTDaoImpl() {
		return goodsCommentTDaoImpl;
	}

	public void setGoodsCommentTDaoImpl(GoodsCommentTDaoImpl goodsCommentTDaoImpl) {
		this.goodsCommentTDaoImpl = goodsCommentTDaoImpl;
	}

	public int delGoodsComment(String[] list) {
		return this.getGoodsCommentTDaoImpl().delGoodsComment(list);
	}

	public int updateGoodsCommentorReplyByState(String state, String[] list) {
		return this.getGoodsCommentTDaoImpl().updateGoodsCommentorReplyByState(state, list);
	}

	public int addGoodsComment(GoodsCommentT gct) {
		return this.getGoodsCommentTDaoImpl().addGoodsComment(gct);
	}

	public List<GoodsCommentT> findAllGoodsComment(int currentPage, int lineSize) {
		return this.getGoodsCommentTDaoImpl().findAllGoodsComment(currentPage, lineSize);
	}

	public int countfindGoodsCommentByGoodsid(String goodsid) {
		return this.getGoodsCommentTDaoImpl().countfindGoodsCommentByGoodsid(goodsid);
	}

	public List<GoodsCommentT> findGoodsCommentByGoodsid(String goodsid, int currentPage, int lineSize) {
		return this.getGoodsCommentTDaoImpl().findGoodsCommentByGoodsid(goodsid, currentPage, lineSize);
	}

	public int countfindAllGoodsComment() {
		return this.getGoodsCommentTDaoImpl().countfindAllGoodsComment();
	}

	public List<GoodsCommentT> sortAllGoodsComment(int currentPage, int lineSize, String queryString) {
		return this.getGoodsCommentTDaoImpl().sortAllGoodsComment(currentPage, lineSize, queryString);
	}
}
