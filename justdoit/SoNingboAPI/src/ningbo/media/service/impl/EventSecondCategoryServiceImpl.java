package ningbo.media.service.impl;

import ningbo.media.bean.EventSecondCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.EventSecondCategoryDao;
import ningbo.media.service.EventSecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("eventSecondCategoryService")
public class EventSecondCategoryServiceImpl extends
		BaseServiceImpl<EventSecondCategory, Integer> implements
		EventSecondCategoryService {

	@Autowired
	public EventSecondCategoryServiceImpl(@Qualifier("eventSecondCategoryDao")
	EventSecondCategoryDao eventSecondCategoryDao) {
		super(eventSecondCategoryDao);
	}
}
