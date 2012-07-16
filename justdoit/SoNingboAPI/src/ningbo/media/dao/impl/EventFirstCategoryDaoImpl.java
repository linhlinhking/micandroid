package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.EventFirstCategory;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.EventFirstCategoryDao;

@Repository("eventFirstCategoryDao")
public class EventFirstCategoryDaoImpl extends
		BaseDaoImpl<EventFirstCategory, Integer> implements
		EventFirstCategoryDao {
	
	public EventFirstCategoryDaoImpl(){
		super(EventFirstCategory.class);
	}

}
