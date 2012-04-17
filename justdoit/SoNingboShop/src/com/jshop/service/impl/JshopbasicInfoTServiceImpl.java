package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.JshopbasicInfoTDaoImpl;
import com.jshop.entity.JshopbasicInfoT;
import com.jshop.service.JshopbasicInfoTService;
@Service("jshopbasicInfoTServiceImpl")
@Scope("prototype")
public class JshopbasicInfoTServiceImpl implements JshopbasicInfoTService {
	@Resource(name="jshopbasicInfoTDaoImpl")
	private JshopbasicInfoTDaoImpl jshopbasicInfoTDaoImpl;

	
	public JshopbasicInfoTDaoImpl getJshopbasicInfoTDaoImpl() {
		return jshopbasicInfoTDaoImpl;
	}

	public void setJshopbasicInfoTDaoImpl(JshopbasicInfoTDaoImpl jshopbasicInfoTDaoImpl) {
		this.jshopbasicInfoTDaoImpl = jshopbasicInfoTDaoImpl;
	}

	public int delJshopbasicInfo(String[] list, String creatorid) {
		return this.getJshopbasicInfoTDaoImpl().delJshopbasicInfo(list, creatorid);
	}

	public int updateJshopbasicInfo(JshopbasicInfoT jbit) {
		return this.getJshopbasicInfoTDaoImpl().updateJshopbasicInfo(jbit);
	}

	public int addJshopbasicInfoT(JshopbasicInfoT jbit) {
		return this.getJshopbasicInfoTDaoImpl().addJshopbasicInfoT(jbit);
	}

	public int countfindAllJshopbasicInfo(String creatorid) {
		return this.getJshopbasicInfoTDaoImpl().countfindAllJshopbasicInfo(creatorid);
	}

	public List<JshopbasicInfoT> findAllJshopbasicInfo(int currentPage, int lineSize, String creatorid) {
		return this.getJshopbasicInfoTDaoImpl().findAllJshopbasicInfo(currentPage, lineSize, creatorid);
	}

	public JshopbasicInfoT findJshopbasicInfoTById(String basicinfoid) {
		return this.getJshopbasicInfoTDaoImpl().findJshopbasicInfoTById(basicinfoid);
	}

	public int updateJshopbasicInfostate(String basicinfoid, String state) {
		return this.getJshopbasicInfoTDaoImpl().updateJshopbasicInfostate(basicinfoid, state);
	}

	public JshopbasicInfoT findJshopbasicInfoSingleForExpress(String creatorid) {
		return this.getJshopbasicInfoTDaoImpl().findJshopbasicInfoSingleForExpress(creatorid);
	}

	public List<JshopbasicInfoT> findJshopbasicInfoBycreatorid(String creatorid) {
		return this.getJshopbasicInfoTDaoImpl().findJshopbasicInfoBycreatorid(creatorid);
	}

	public List<JshopbasicInfoT> findAllJshopbasicInfoNoParam(int currentPage, int lineSize, String state) {
		return this.getJshopbasicInfoTDaoImpl().findAllJshopbasicInfoNoParam(currentPage, lineSize, state);
	}

	public JshopbasicInfoT findJshopbasicInfoBystateandopstate(String creatorid, String state, String openstate) {
		return this.getJshopbasicInfoTDaoImpl().findJshopbasicInfoBystateandopstate(creatorid, state, openstate);
	}

	public JshopbasicInfoT findJshopbasicInfoBystateandopstate(String state, String openstate) {
		return this.getJshopbasicInfoTDaoImpl().findJshopbasicInfoBystateandopstate(state, openstate);
	}
}
