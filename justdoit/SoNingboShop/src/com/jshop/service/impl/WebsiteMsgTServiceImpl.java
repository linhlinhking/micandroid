package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.WebsiteMsgTDaoImpl;
import com.jshop.entity.WebsiteMsgT;
import com.jshop.service.WebsiteMsgTService;
@Service("websiteMsgTServiceImpl")
@Scope("prototype")
public class WebsiteMsgTServiceImpl implements WebsiteMsgTService {
	@Resource(name="websiteMsgTDaoImpl")
	private WebsiteMsgTDaoImpl websiteMsgTDaoImpl;

	public WebsiteMsgTDaoImpl getWebsiteMsgTDaoImpl() {
		return websiteMsgTDaoImpl;
	}

	public void setWebsiteMsgTDaoImpl(WebsiteMsgTDaoImpl websiteMsgTDaoImpl) {
		this.websiteMsgTDaoImpl = websiteMsgTDaoImpl;
	}

	public int delWebsiteMsgT(String[] list) {
		return this.getWebsiteMsgTDaoImpl().delWebsiteMsgT(list);
	}

	public int addWebsiteMsgT(WebsiteMsgT wm) {
		return this.getWebsiteMsgTDaoImpl().addWebsiteMsgT(wm);
	}

	public int countfindAllWebsiteMsgByFromUserid(String userid) {
		return this.getWebsiteMsgTDaoImpl().countfindAllWebsiteMsgByFromUserid(userid);
	}

	public int countfindAllWebsiteMsgByToUsername(String msgtousername) {
		return this.getWebsiteMsgTDaoImpl().countfindAllWebsiteMsgByToUsername(msgtousername);
	}

	public List<WebsiteMsgT> findAllWebsiteMsgByFromUserid(int currentPage, int lineSize, String userid) {
		return this.getWebsiteMsgTDaoImpl().findAllWebsiteMsgByFromUserid(currentPage, lineSize, userid);
	}

	public List<WebsiteMsgT> findAllWebsiteMsgByToUsername(int currentPage, int lineSize, String msgtousername) {
		return this.getWebsiteMsgTDaoImpl().findAllWebsiteMsgByToUsername(currentPage, lineSize, msgtousername);
	}

	public int updateWebsiteMsgstate(String[] list, String state) {
		return this.getWebsiteMsgTDaoImpl().updateWebsiteMsgstate(list, state);
	}
}
