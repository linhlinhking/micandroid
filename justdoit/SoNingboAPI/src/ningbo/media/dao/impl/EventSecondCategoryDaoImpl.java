package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.EventSecondCategory;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.EventSecondCategoryDao;

@Repository("eventSecondCategoryDao")
public class EventSecondCategoryDaoImpl extends
		BaseDaoImpl<EventSecondCategory, Integer> implements
		EventSecondCategoryDao {
	
	public EventSecondCategoryDaoImpl(){
		super(EventSecondCategory.class) ;
	}

}
