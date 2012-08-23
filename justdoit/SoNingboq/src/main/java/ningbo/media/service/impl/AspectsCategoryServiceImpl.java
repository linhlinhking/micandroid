package ningbo.media.service.impl;

import ningbo.media.bean.AspectsCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.AspectsCategoryDao;
import ningbo.media.service.AspectsCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("aspectsCategoryService")
public class AspectsCategoryServiceImpl extends
		BaseServiceImpl<AspectsCategory, Integer> implements
		AspectsCategoryService {

	@Autowired
	public AspectsCategoryServiceImpl(@Qualifier("aspectsCategoryDao")
	AspectsCategoryDao aspectsCategoryDao) {
		super(aspectsCategoryDao);
	}
}
