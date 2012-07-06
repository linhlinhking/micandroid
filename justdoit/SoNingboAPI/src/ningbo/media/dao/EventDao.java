package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Event;
import ningbo.media.bean.enums.EventType;
import ningbo.media.core.dao.BaseDao;

public interface EventDao extends BaseDao<Event, Integer>{

	public List<Event> getEventsByType(String mdValue, EventType type);
}
