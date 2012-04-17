package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.PageTDaoImpl;
import com.jshop.entity.PageT;
import com.jshop.service.PageTService;
@Service("pageTServiceImpl")
@Scope("prototype")
public class PageTServiceImpl implements PageTService {
	@Resource(name="pageTDaoImpl")
	private PageTDaoImpl pageTDaoImpl;

	public PageTDaoImpl getPageTDaoImpl() {
		return pageTDaoImpl;
	}

	public void setPageTDaoImpl(PageTDaoImpl pageTDaoImpl) {
		this.pageTDaoImpl = pageTDaoImpl;
	}

	public int delPaget(String[] list) {
		return this.getPageTDaoImpl().delPaget(list);
	}

	public int updatePaget(PageT pt) {
		return this.getPageTDaoImpl().updatePaget(pt);
	}

	public int addPaget(PageT pt) {
		return this.getPageTDaoImpl().addPaget(pt);
	}

	public int countfindAllPaget() {
		return this.getPageTDaoImpl().countfindAllPaget();
	}

	public List<PageT> findAllPaget(int currentPage, int lineSize) {
		return this.getPageTDaoImpl().findAllPaget(currentPage, lineSize);
	}

	public PageT findPagetById(String pageid) {
		return this.getPageTDaoImpl().findPagetById(pageid);
	}

	public List<PageT> findAllPagetforPageEdit() {
		return this.getPageTDaoImpl().findAllPagetforPageEdit();
	}
}
