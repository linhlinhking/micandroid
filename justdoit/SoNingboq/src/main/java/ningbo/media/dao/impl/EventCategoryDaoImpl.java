package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.EventCategory;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.EventCategoryDao;

@Repository("eventCategoryDao")
public class EventCategoryDaoImpl extends BaseDaoImpl<EventCategory, Integer>
		implements EventCategoryDao {

	public EventCategoryDaoImpl(){
		super(EventCategory.class);
	}
}
