package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Event;
import ningbo.media.core.dao.BaseDao;

public interface EventDao extends BaseDao<Event, Integer> {

	public List<Event> getUserEventsList(Integer userId) ;
	
	public List<Event> getLocationEventsList(Integer locationId) ;
	
	
}
