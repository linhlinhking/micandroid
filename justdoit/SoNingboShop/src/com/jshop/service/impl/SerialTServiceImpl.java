package com.jshop.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.SerialTDaoImpl;
import com.jshop.entity.SerialT;
import com.jshop.service.SerialTService;
@Service("serialTServiceImpl")
@Scope("prototype")
public class SerialTServiceImpl implements SerialTService {
	@Resource(name = "serialTDaoImpl")
	private SerialTDaoImpl serialTDaoImpl;
	
	public SerialTDaoImpl getSerialTDaoImpl() {
		return serialTDaoImpl;
	}
	public void setSerialTDaoImpl(SerialTDaoImpl serialTDaoImpl) {
		this.serialTDaoImpl = serialTDaoImpl;
	}
	public SerialT findBybaseid(String biz) {
		return this.getSerialTDaoImpl().findBybaseid(biz);
	}

	public void save(SerialT transientInstance) {
		this.getSerialTDaoImpl().save(transientInstance);
	}

	public int updateBybaseid(SerialT transientInstance) {
		return this.getSerialTDaoImpl().updateBybaseid(transientInstance);
	}
}
