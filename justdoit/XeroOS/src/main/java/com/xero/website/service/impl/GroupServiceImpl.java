package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.Group;
import com.xero.website.dao.GroupDao;
import com.xero.website.service.GroupService;

@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<Group, Integer> implements
		GroupService {

	@Autowired
	public GroupServiceImpl(@Qualifier("groupDao") GroupDao groupDao) {
		super(groupDao);
	}
}
