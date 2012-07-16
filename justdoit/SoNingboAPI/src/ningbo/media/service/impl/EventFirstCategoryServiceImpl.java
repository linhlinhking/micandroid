package ningbo.media.service.impl;

import ningbo.media.bean.EventFirstCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.EventFirstCategoryDao;
import ningbo.media.service.EventFirstCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("eventFirstCategoryService")
public class EventFirstCategoryServiceImpl extends
		BaseServiceImpl<EventFirstCategory, Integer> implements
		EventFirstCategoryService {

	@Autowired
	public EventFirstCategoryServiceImpl(@Qualifier("eventFirstCategoryDao")
	EventFirstCategoryDao eventFirstCategoryDao) {
		super(eventFirstCategoryDao);
	}
}
