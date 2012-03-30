package ningbo.media.service.impl;

import ningbo.media.bean.SecondCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.SecondCategoryDao;
import ningbo.media.service.SecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("secondCategoryService")
public class SecondCategoryServiceImpl extends BaseServiceImpl<SecondCategory, Integer> implements SecondCategoryService {

	@Autowired
	public SecondCategoryServiceImpl(@Qualifier("secondCategoryDao")SecondCategoryDao secondCategoryDao) {
		super(secondCategoryDao);
	}

}
