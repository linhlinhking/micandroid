package com.soningbo.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.soningbo.core.dao.impl.BaseDaoImpl;
import com.soningbo.website.dao.SecondCategoryDao;
import com.soningbo.website.entity.SecondCategory;

@Repository("secondCategoryDao")
public class SecondCategoryDaoImpl extends BaseDaoImpl<SecondCategory, Integer>
		implements SecondCategoryDao {

}
