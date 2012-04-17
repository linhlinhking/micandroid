package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.SystemMailMDaoImpl;
import com.jshop.entity.SystemMailM;
import com.jshop.service.SystemMailMService;
@Service("systemMailMServiceImpl")
@Scope("prototype")
public class SystemMailMServiceImpl implements SystemMailMService {
	@Resource(name = "systemMailMDaoImpl")
	private SystemMailMDaoImpl systemMailMDaoImpl;
	public SystemMailMDaoImpl getSystemMailMDaoImpl() {
		return systemMailMDaoImpl;
	}

	public void setSystemMailMDaoImpl(SystemMailMDaoImpl systemMailMDaoImpl) {
		this.systemMailMDaoImpl = systemMailMDaoImpl;
	}

	public int addSystemMail(SystemMailM sm) {
		return this.getSystemMailMDaoImpl().addSystemMail(sm);
	}

	public int updateSystemMail(SystemMailM sm) {
		return this.getSystemMailMDaoImpl().updateSystemMail(sm);
	}

	public List<SystemMailM> findAllSystemMail(String taobaouserid) {
		return this.getSystemMailMDaoImpl().findAllSystemMail(taobaouserid);
	}

	public SystemMailM findSysmailBysmailid(String smialid) {

		return this.getSystemMailMDaoImpl().findSysmailBysmailid(smialid);
	}
}
