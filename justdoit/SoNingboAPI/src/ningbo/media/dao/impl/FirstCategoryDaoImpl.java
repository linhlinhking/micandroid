package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.FirstCategory;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FirstCategoryDao;

@Repository("firstCategoryDao")
public class FirstCategoryDaoImpl extends BaseDaoImpl<FirstCategory, Integer>
		implements FirstCategoryDao {

	public FirstCategoryDaoImpl() {
		super(FirstCategory.class);
	}
}
