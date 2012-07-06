package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.bean.Event;
import ningbo.media.bean.enums.EventType;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.EventDao;
import ningbo.media.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("eventService")
public class EventServiceImpl extends BaseServiceImpl<Event, Integer> implements
		EventService {

	@Resource
	private EventDao eventDao ;
	
	@Autowired
	public EventServiceImpl(@Qualifier("eventDao")
	EventDao eventDao) {
		super(eventDao);
	}

	public List<Event> getEventsByType(String mdValue, EventType type) {
		return eventDao.getEventsByType(mdValue, type) ;
	}
	
	
}
