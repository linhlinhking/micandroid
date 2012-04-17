package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.SiteNavigationTDaoImpl;
import com.jshop.entity.SiteNavigationT;
import com.jshop.service.SiteNavigationTService;
@Service("siteNavigationTServiceImpl")
@Scope("prototype")
public class SiteNavigationTServiceImpl implements SiteNavigationTService {
	@Resource(name = "siteNavigationTDaoImpl")
	private SiteNavigationTDaoImpl siteNavigationTDaoImpl;

	public SiteNavigationTDaoImpl getSiteNavigationTDaoImpl() {
		return siteNavigationTDaoImpl;
	}

	public void setSiteNavigationTDaoImpl(SiteNavigationTDaoImpl siteNavigationTDaoImpl) {
		this.siteNavigationTDaoImpl = siteNavigationTDaoImpl;
	}

	public int addSiteNavigationT(SiteNavigationT sn) {
		return this.getSiteNavigationTDaoImpl().addSiteNavigationT(sn);
	}

	public int countfindAllSiteNavigationT(String creatorid) {
		return this.getSiteNavigationTDaoImpl().countfindAllSiteNavigationT(creatorid);
	}

	public int delSiteNavigationT(String[] list) {
		return this.getSiteNavigationTDaoImpl().delSiteNavigationT(list);
	}

	public List<SiteNavigationT> findAllSiteNavigationT(int currentPage, int lineSize, String creatorid) {
		return this.getSiteNavigationTDaoImpl().findAllSiteNavigationT(currentPage, lineSize, creatorid);
	}

	public int updateSiteNavigationT(SiteNavigationT sn) {
		return this.getSiteNavigationTDaoImpl().updateSiteNavigationT(sn);
	}

	public SiteNavigationT findSiteNavigationBysnid(String snid) {
		return this.getSiteNavigationTDaoImpl().findSiteNavigationBysnid(snid);
	}

	public List<SiteNavigationT> findSiteNavigationByposition(String isVisible, String position, String creatorid) {
		return this.getSiteNavigationTDaoImpl().findSiteNavigationByposition(isVisible, position, creatorid);
	}

	public List<SiteNavigationT> findSiteNavigationByisVisible(String isVisible, String creatorid) {
		return this.getSiteNavigationTDaoImpl().findSiteNavigationByisVisible(isVisible, creatorid);
	}

	public List<SiteNavigationT> findSiteNavigationByisVisible(String isVisible) {
		return this.getSiteNavigationTDaoImpl().findSiteNavigationByisVisible(isVisible);
	}

	public List<SiteNavigationT> sortAllSiteNavigationT(int currentPage, int lineSize, String creatorid, String queryString) {

		return this.getSiteNavigationTDaoImpl().sortAllSiteNavigationT(currentPage, lineSize, creatorid, queryString);
	}
}
