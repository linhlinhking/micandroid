package com.soningbo.website.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soningbo.core.service.impl.BaseServiceImpl;
import com.soningbo.website.entity.FirstCategory;
import com.soningbo.website.service.FirstCategoryService;

@Service("firstCategoryService")
@Transactional
public class FirstCategoryServiceImpl extends
		BaseServiceImpl<FirstCategory, Integer> implements FirstCategoryService {

}
