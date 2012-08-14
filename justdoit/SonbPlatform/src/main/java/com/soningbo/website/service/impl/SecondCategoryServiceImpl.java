package com.soningbo.website.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soningbo.core.service.impl.BaseServiceImpl;
import com.soningbo.website.entity.SecondCategory;
import com.soningbo.website.service.SecondCategoryService;

@Service("secondCategoryService")
@Transactional
public class SecondCategoryServiceImpl extends
BaseServiceImpl<SecondCategory, Integer> implements SecondCategoryService{

}
