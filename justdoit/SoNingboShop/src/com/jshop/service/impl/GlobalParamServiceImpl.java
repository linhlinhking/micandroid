package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GlobalParamMDaoImpl;
import com.jshop.entity.GlobalParamM;
import com.jshop.service.GlobalParamService;

@Service("globalParamServiceImpl")
@Scope("prototype")
public class GlobalParamServiceImpl implements GlobalParamService {
	@Resource(name="globalParamMDaoImpl")
	private GlobalParamMDaoImpl globalParamMDaoImpl;

	public GlobalParamMDaoImpl getGlobalParamMDaoImpl() {
		return globalParamMDaoImpl;
	}

	public void setGlobalParamMDaoImpl(GlobalParamMDaoImpl globalParamMDaoImpl) {
		this.globalParamMDaoImpl = globalParamMDaoImpl;
	}

	public int updateGolbalParamByKey(GlobalParamM gm) {
		return this.getGlobalParamMDaoImpl().updateGolbalParamByKey(gm);
	}

	public GlobalParamM findValueByKey(String key) {
		return this.getGlobalParamMDaoImpl().findValueByKey(key);
	}

	public List<GlobalParamM> findAllGlobalParam() {
		return this.getGlobalParamMDaoImpl().findAllGlobalParam();
	}
}
