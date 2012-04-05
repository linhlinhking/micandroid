package ningbo.media.dao.impl;

import java.util.List;

import ningbo.media.bean.Event;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.EventDao;

import org.springframework.stereotype.Repository;

@Repository("eventDao")
public class EventDaoImpl extends BaseDaoImpl<Event, Integer> implements
		EventDao {

	public EventDaoImpl() {
		super(Event.class);
	}

	public List<Event> getUserEventsList(Integer id) {
		try {
			String hql = "from Event m where 1=1 and m.systemUser.id = ? ";
			List<Event> lists = findByHql(hql, id);
			if(null == lists || lists.size() < 0){
				return null ;
			}
			return lists ;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Event> getLocationEventsList(Integer locationId) {
		try {
			String hql = "from Event m where 1=1 and m.location.id = ? ";
			List<Event> lists = findByHql(hql, locationId);
			if(null == lists || lists.size() < 0){
				return null ;
			}
			return lists ;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
