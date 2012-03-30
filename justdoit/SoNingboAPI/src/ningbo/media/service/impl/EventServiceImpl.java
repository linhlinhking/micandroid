package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.Event;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.EventDao;
import ningbo.media.service.EventService;

@Service("eventService")
public class EventServiceImpl extends BaseServiceImpl<Event, Integer> implements EventService{

	@Autowired
	public EventServiceImpl(@Qualifier("eventDao")EventDao eventDao) {
		super(eventDao);
	}
	

}
