package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.Group;
import com.xero.website.dao.GroupDao;

@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group, Integer> implements
		GroupDao {

	public GroupDaoImpl(){
		super(Group.class);
	}
}
