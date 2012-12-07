package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.Plan;
import com.xero.website.dao.PlanDao;
import com.xero.website.service.PlanService;

@Service("planService")
public class PlanServiceImpl extends BaseServiceImpl<Plan, Integer> implements
		PlanService {

	@Autowired
	public PlanServiceImpl(@Qualifier("planDao") PlanDao planDao) {
		super(planDao);
	}
}
