package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.TemplatesetTDaoImpl;
import com.jshop.entity.TemplatesetT;
import com.jshop.service.TemplatesetTService;
@Service("templatesetTServiceImpl")
@Scope("prototype")
public class TemplatesetTServiceImpl implements TemplatesetTService {
	@Resource(name="templatesetTDaoImpl")
	private TemplatesetTDaoImpl templatesetTDaoImpl;

	
	public TemplatesetTDaoImpl getTemplatesetTDaoImpl() {
		return templatesetTDaoImpl;
	}

	public void setTemplatesetTDaoImpl(TemplatesetTDaoImpl templatesetTDaoImpl) {
		this.templatesetTDaoImpl = templatesetTDaoImpl;
	}

	public int addTemplatesetT(TemplatesetT tst) {
		return this.getTemplatesetTDaoImpl().addTemplatesetT(tst);
	}

	public int countfindAllTemplatesetT(String creatorid) {
		return this.getTemplatesetTDaoImpl().countfindAllTemplatesetT(creatorid);
	}

	public int delTemplatesetT(String[] list) {
		return this.getTemplatesetTDaoImpl().delTemplatesetT(list);
	}

	public List<TemplatesetT> findAllTemplatesetT(int currentPage, int lineSize, String creatorid) {
		return this.getTemplatesetTDaoImpl().findAllTemplatesetT(currentPage, lineSize, creatorid);
	}

	public TemplatesetT findTemplatesetTBytsid(String tsid) {
		return this.getTemplatesetTDaoImpl().findTemplatesetTBytsid(tsid);
	}

	public int updateTemplatesetT(TemplatesetT tst) {
		return this.getTemplatesetTDaoImpl().updateTemplatesetT(tst);
	}

	public List<TemplatesetT> findAllTemplatesetWithNoParam(String creatorid) {
		return this.getTemplatesetTDaoImpl().findAllTemplatesetWithNoParam(creatorid);
	}

	public TemplatesetT findTemplatesetTBysystemcontent(String systemcontent, String creatorid) {
		return this.getTemplatesetTDaoImpl().findTemplatesetTBysystemcontent(systemcontent, creatorid);
	}

	public TemplatesetT findTemplatesetTBysign(String sign, String creatorid) {
		return this.getTemplatesetTDaoImpl().findTemplatesetTBysign(sign, creatorid);
	}

	public int updateTemplatesetBystatus(String themeid, String status) {
		return this.getTemplatesetTDaoImpl().updateTemplatesetBystatus(themeid, status);
	}

	public TemplatesetT findTemplatesetTBysign(String sign) {
		return this.getTemplatesetTDaoImpl().findTemplatesetTBysign(sign);
	}
	
	
	
}
