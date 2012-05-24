package ningbo.media.dao.impl;

import java.util.List;

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

	public List<SecondCategory> queryByFirstCategoryId(Integer id) {
		String hql = "from SecondCategory model where 1=1 and model.firstCategory.id = ? " ;
		return findByHql(hql, id); 
	}
}
