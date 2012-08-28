package ningbo.media.service.impl;

import ningbo.media.bean.EventCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.EventCategoryDao;
import ningbo.media.service.EventCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("eventCategoryService")
public class EventCategoryServiceImpl extends BaseServiceImpl<EventCategory, Integer> implements EventCategoryService{

	@Autowired
	public EventCategoryServiceImpl(@Qualifier("eventCategoryDao")EventCategoryDao eventCategoryDao){
		super(eventCategoryDao);
	}
}
