package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ExpresstempleteTDaoImpl;
import com.jshop.entity.ExpresstempleteT;
import com.jshop.service.ExpresstempleteTService;
@Service("expresstempleteTServiceImpl")
@Scope("prototype")
public class ExpresstempleteTServiceImpl implements ExpresstempleteTService {
	@Resource(name="expresstempleteTDaoImpl")
	private ExpresstempleteTDaoImpl expresstempleteTDaoImpl;


	public ExpresstempleteTDaoImpl getExpresstempleteTDaoImpl() {
		return expresstempleteTDaoImpl;
	}

	public void setExpresstempleteTDaoImpl(ExpresstempleteTDaoImpl expresstempleteTDaoImpl) {
		this.expresstempleteTDaoImpl = expresstempleteTDaoImpl;
	}

	public int delExpresstemplete(String[] list) {
		return this.getExpresstempleteTDaoImpl().delExpresstemplete(list);
	}

	public int updateExpresstemplete(ExpresstempleteT et) {
		return this.getExpresstempleteTDaoImpl().updateExpresstemplete(et);
	}

	public int addExpresstemplete(ExpresstempleteT et) {
		return this.getExpresstempleteTDaoImpl().addExpresstemplete(et);
	}

	public int countfindAllExpresstempleteT() {
		return this.getExpresstempleteTDaoImpl().countfindAllExpresstempleteT();
	}

	public List<ExpresstempleteT> findAllExpresstempleteT(int currentPage, int lineSize) {
		return this.getExpresstempleteTDaoImpl().findAllExpresstempleteT(currentPage, lineSize);
	}

	public ExpresstempleteT findExpresstempleteByLogisticsid(String logisticsid) {
		return this.getExpresstempleteTDaoImpl().findExpresstempleteByLogisticsid(logisticsid);
	}

	public ExpresstempleteT findExpresstempleteBytempleteid(String expresstempleteid) {
		return this.getExpresstempleteTDaoImpl().findExpresstempleteBytempleteid(expresstempleteid);
	}
}
