package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.PageEditareaTDaoImpl;
import com.jshop.entity.PageEditareaT;
import com.jshop.service.PageEditareaTService;
@Service("pageEditareaTServiceImpl")
@Scope("prototype")
public class PageEditareaTServiceImpl implements PageEditareaTService {
	@Resource(name="pageEditareaTDaoImpl")
	private PageEditareaTDaoImpl pageEditareaTDaoImpl;

	public PageEditareaTDaoImpl getPageEditareaTDaoImpl() {
		return pageEditareaTDaoImpl;
	}

	public void setPageEditareaTDaoImpl(PageEditareaTDaoImpl pageEditareaTDaoImpl) {
		this.pageEditareaTDaoImpl = pageEditareaTDaoImpl;
	}

	public int updatePageEditareaT(PageEditareaT pea) {
		return this.getPageEditareaTDaoImpl().updatePageEditareaT(pea);
	}

	public int addPageEditareaT(PageEditareaT pea) {
		return this.getPageEditareaTDaoImpl().addPageEditareaT(pea);
	}

	public List<PageEditareaT> findPageEditareaTByPageidandTypeid(String pageid, String typeid) {
		return this.getPageEditareaTDaoImpl().findPageEditareaTByPageidandTypeid(pageid, typeid);
	}

	public List<PageEditareaT> findAllPageEditareaT(int currentPage, int lineSize, String creatorid) {
		return this.getPageEditareaTDaoImpl().findAllPageEditareaT(currentPage, lineSize, creatorid);
	}

	public int countfindAllPageEditareaT(String creatorid) {
		return this.getPageEditareaTDaoImpl().countfindAllPageEditareaT(creatorid);
	}

	public int updatePageEditareaTBystate(String[] list, String state, String creatorid) {
		return this.getPageEditareaTDaoImpl().updatePageEditareaTBystate(list, state, creatorid);
	}

	public PageEditareaT findPageEditareaById(String pageeditareaid) {
		return this.getPageEditareaTDaoImpl().findPageEditareaById(pageeditareaid);
	}

	public List<PageEditareaT> findPageEditareaTByPageidandTypeidandState(String pageid, String typeid, String state) {
		return this.getPageEditareaTDaoImpl().findPageEditareaTByPageidandTypeidandState(pageid, typeid, state);
	}

	public List<PageEditareaT> findPageEditareaTBySign(String sign, String state, String creatorid) {
		return this.getPageEditareaTDaoImpl().findPageEditareaTBySign(sign, state, creatorid);
	}

	public List<PageEditareaT> findPageEditareaTByTid(String tid, String creatorid) {
		return this.getPageEditareaTDaoImpl().findPageEditareaTByTid(tid, creatorid);
	}

	public int delPageEditareaTBypageeditareaid(String[] strs, String state) {
		return this.getPageEditareaTDaoImpl().delPageEditareaTBypageeditareaid(strs, state);
	}
	
	
	
	
}
