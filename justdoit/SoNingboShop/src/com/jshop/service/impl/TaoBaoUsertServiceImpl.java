package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.TaoBaoUserTDaoImpl;
import com.jshop.entity.TaobaoUserT;
import com.jshop.service.TaoBaoUsertService;
@Service("taoBaoUsertServiceImpl")
@Scope("prototype")
public class TaoBaoUsertServiceImpl implements TaoBaoUsertService {
	@Resource(name = "taoBaoUserTDaoImpl")
	private TaoBaoUserTDaoImpl taoBaoUserTDaoImpl;

	public TaoBaoUserTDaoImpl getTaoBaoUserTDaoImpl() {
		return taoBaoUserTDaoImpl;
	}

	public void setTaoBaoUserTDaoImpl(TaoBaoUserTDaoImpl taoBaoUserTDaoImpl) {
		this.taoBaoUserTDaoImpl = taoBaoUserTDaoImpl;
	}

	public int addTaoBaoUserT(TaobaoUserT taobao) {
		return this.getTaoBaoUserTDaoImpl().addTaoBaoUserT(taobao);
	}

	public List<TaobaoUserT> findAllTaobaoUserT(int currentPage, int lineSize) {
		return this.getTaoBaoUserTDaoImpl().findAllTaobaoUserT(currentPage, lineSize);
	}
}
