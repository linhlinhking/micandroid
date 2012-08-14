package com.soningbo.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.soningbo.core.dao.impl.BaseDaoImpl;
import com.soningbo.website.dao.FirstCategoryDao;
import com.soningbo.website.entity.FirstCategory;

@Repository("firstCategoryDao")
public class FirstCategoryDaoImpl extends BaseDaoImpl<FirstCategory, Integer>
		implements FirstCategoryDao {

}
