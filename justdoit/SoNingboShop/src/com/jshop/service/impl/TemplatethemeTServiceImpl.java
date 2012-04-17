package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.TemplatethemeTDaoImpl;
import com.jshop.entity.TemplatethemeT;
import com.jshop.service.TemplatethemeTService;
@Service("templatethemeTServiceImpl")
@Scope("prototype")
public class TemplatethemeTServiceImpl implements TemplatethemeTService {
	@Resource(name="templatethemeTDaoImpl")
	private TemplatethemeTDaoImpl templatethemeTDaoImpl;
	
	

	public TemplatethemeTDaoImpl getTemplatethemeTDaoImpl() {
		return templatethemeTDaoImpl;
	}

	public void setTemplatethemeTDaoImpl(TemplatethemeTDaoImpl templatethemeTDaoImpl) {
		this.templatethemeTDaoImpl = templatethemeTDaoImpl;
	}

	public int addTemplatetheme(TemplatethemeT tt) {
		return this.getTemplatethemeTDaoImpl().addTemplatetheme(tt);
	}

	public int checkTemplatethemeBythemenameandsign(String themename,String sign) {
		return this.getTemplatethemeTDaoImpl().checkTemplatethemeBythemenameandsign(themename,sign);
	}

	public int countfindAllTemplatetheme(String creatorid) {
		return this.getTemplatethemeTDaoImpl().countfindAllTemplatetheme(creatorid);
	}

	public int delTemplatetheme(String[] str) {
		return this.getTemplatethemeTDaoImpl().delTemplatetheme(str);
	}

	public List<TemplatethemeT> findAllTemplatetheme(int currentPage, int lineSize, String creatorid) {
		return this.getTemplatethemeTDaoImpl().findAllTemplatetheme(currentPage, lineSize, creatorid);
	}

	public List<TemplatethemeT> sortAllTemplatetheme(int currentPage, int lineSize, String creatorid, String queryString) {
		return this.getTemplatethemeTDaoImpl().sortAllTemplatetheme(currentPage, lineSize, creatorid, queryString);
	}

	public TemplatethemeT findTemplatethemeByttid(String ttid) {
		return this.getTemplatethemeTDaoImpl().findTemplatethemeByttid(ttid);
	}

	public void updateTemplatetheme(TemplatethemeT tt) {
		this.getTemplatethemeTDaoImpl().updateTemplatetheme(tt);
	}

	public List<TemplatethemeT> findAllTemplatethemeWithNoParam(String creatorid) {
		return this.getTemplatethemeTDaoImpl().findAllTemplatethemeWithNoParam(creatorid);
	}

	public int updateTemplatethemestatus(String ttid, String status) {
		return this.getTemplatethemeTDaoImpl().updateTemplatethemestatus(ttid, status);
	}

	public int checkTemplatethemeBythemenameandsign(String themename, String sign, String ttid) {
		return this.getTemplatethemeTDaoImpl().checkTemplatethemeBythemenameandsign(themename, sign, ttid);
	}

	public int delTemplatetheme(String ttid) {
		return this.getTemplatethemeTDaoImpl().delTemplatetheme(ttid);
	}

	public TemplatethemeT findTemplatethemeBystatus(String status) {
		return this.getTemplatethemeTDaoImpl().findTemplatethemeBystatus(status);
	}

}
