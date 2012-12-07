package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.Plan;
import com.xero.website.dao.PlanDao;

@Repository("planDao")
public class PlanDaoImpl extends BaseDaoImpl<Plan, Integer> implements PlanDao {

	public PlanDaoImpl(){
		super(Plan.class);
	}
}
