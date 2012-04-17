package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.TemplateTDaoImpl;
import com.jshop.entity.TemplateT;
import com.jshop.service.TemplateTService;
@Service("templateTServiceImpl")
@Scope("prototype")
public class TemplateTServiceImpl implements TemplateTService {
	@Resource(name="templateTDaoImpl")
	private TemplateTDaoImpl templateTDaoImpl;

	
	public TemplateTDaoImpl getTemplateTDaoImpl() {
		return templateTDaoImpl;
	}

	public void setTemplateTDaoImpl(TemplateTDaoImpl templateTDaoImpl) {
		this.templateTDaoImpl = templateTDaoImpl;
	}

	public int addTemplate(TemplateT tt) {
		return this.getTemplateTDaoImpl().addTemplate(tt);
	}

	public int countfindAllTemplate(String creatorid) {
		return this.getTemplateTDaoImpl().countfindAllTemplate(creatorid);
	}

	public List<TemplateT> findAllTemplate(int currentPage, int lineSize, final String creatorid) {
		return this.getTemplateTDaoImpl().findAllTemplate(currentPage, lineSize, creatorid);
	}

	public int delTemplate(String[] list) {
		return this.getTemplateTDaoImpl().delTemplate(list);
	}

	public TemplateT findTemplateByTid(String tid) {
		return this.getTemplateTDaoImpl().findTemplateByTid(tid);
	}

	public int updateTemplate(TemplateT tt) {
		return this.getTemplateTDaoImpl().updateTemplate(tt);
	}

	public List<TemplateT> findAllTemplateWithNoParam(String creatorid,String status) {
		return this.getTemplateTDaoImpl().findAllTemplateWithNoParam(creatorid,status);
	}

	public int findTemplateBynameandnote(String creatorid, String note, String name) {
		return this.getTemplateTDaoImpl().findTemplateBynameandnote(creatorid, note, name);
	}

	public TemplateT findTemplateByname(String creatorid, String name) {
		return this.getTemplateTDaoImpl().findTemplateByname(creatorid, name);
	}

	public TemplateT findTemplateBytype(String type) {
		return this.getTemplateTDaoImpl().findTemplateBytype(type);
	}

	public TemplateT checkTemplatetheme(String themeid) {
		return this.getTemplateTDaoImpl().checkTemplatetheme(themeid);
	}

	public int updateTemplatetBystatus(String themeid, String status) {
		return this.getTemplateTDaoImpl().updateTemplatetBystatus(themeid, status);
	}

	public TemplateT findTemplateBysign(String sign, String status) {
		return this.getTemplateTDaoImpl().findTemplateBysign(sign, status);
	}
	
	
}
