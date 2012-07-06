package ningbo.media.dao.impl;

import java.util.List;

import ningbo.media.bean.Event;
import ningbo.media.bean.enums.EventType;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.EventDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("eventDao")
public class EventDaoIImpl extends BaseDaoImpl<Event, Integer> implements
		EventDao {
	private static Logger logger = LoggerFactory.getLogger(EventDaoIImpl.class);

	public EventDaoIImpl() {
		super(Event.class);
	}

	public List<Event> getEventsByType(String md5Value, EventType type) {
		try {
			String hql = "";
			if (EventType.SYSTEMUSER.equals(type)) {
				hql = "from Event as model where 1=1 and model.userMd5Value = ? ";
			} else if (EventType.LOCATION.equals(type)) {
				hql = "from Event as model where 1=1 and model.locationMd5Value = ? ";
			} else {
				return null;
			}
			List<Event> events = findByHql(hql, md5Value) ;
			if(null != events && events.size() >0){
				return events ;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Query Event Error." + type);
		}
		return null;
	}

}
