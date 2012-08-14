package com.soningbo.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.soningbo.core.dao.impl.BaseDaoImpl;
import com.soningbo.website.dao.AspectsCategoryDao;
import com.soningbo.website.entity.AspectsCategory;

@Repository("aspectsCategoryDao")
public class AspectsCategoryDaoImpl extends BaseDaoImpl<AspectsCategory, Integer>
		implements AspectsCategoryDao {

}
