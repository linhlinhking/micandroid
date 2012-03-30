package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Event;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.EventDao;

@Repository("eventDao")
public class EventDaoImpl extends BaseDaoImpl<Event, Integer> implements
		EventDao {
	
	
	public EventDaoImpl(){
		super(Event.class) ;
	}

}
