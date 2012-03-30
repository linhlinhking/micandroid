package ningbo.media.dao.impl;

import ningbo.media.bean.SecondCategory;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.SecondCategoryDao;

import org.springframework.stereotype.Repository;

@Repository("secondCategoryDao")
public class SecondCategoryDaoImpl extends BaseDaoImpl<SecondCategory, Integer>
		implements SecondCategoryDao {

	public SecondCategoryDaoImpl() {
		super(SecondCategory.class);
	}
}
