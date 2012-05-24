package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.SecondCategory;
import ningbo.media.core.dao.BaseDao;

public interface SecondCategoryDao extends BaseDao<SecondCategory, Integer>{

	public List<SecondCategory> queryByFirstCategoryId(Integer id);
}
