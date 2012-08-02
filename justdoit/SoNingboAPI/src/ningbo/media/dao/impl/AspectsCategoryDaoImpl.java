package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.AspectsCategory;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.AspectsCategoryDao;

@Repository("aspectsCategoryDao")
public class AspectsCategoryDaoImpl extends
		BaseDaoImpl<AspectsCategory, Integer> implements AspectsCategoryDao {

	public AspectsCategoryDaoImpl() {
		super(AspectsCategory.class);
	}
}
