package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Event;
import ningbo.media.bean.enums.EventType;
import ningbo.media.core.service.BaseService;

public interface EventService extends BaseService<Event, Integer>{

	public List<Event> getEventsByType(String mdValue,EventType type) ;
	
	public Event getEventByUser(String eMdValue,String uMdValue) ;
	
	public List<Event> getAllEventOrderByDate();
}
