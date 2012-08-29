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
			} else if (EventType.EVENTDATE.equals(type)) {
				hql = "from Event as model where 1=1 and model.startDate > ? order by model.startDate asc,model.id desc ";
			} else if(EventType.EVENTTODAY.equals(type)){
				hql = "from Event as model where 1=1 and model.startDate = ? order by model.startDate desc,model.id desc ";
			}else{
				return null;
			}
			List<Event> events = findByHql(hql, md5Value);
			if (null != events && events.size() > 0) {
				return events;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Query Events Error." + type);
		}
		return null;
	}

	public Event getEventByUser(String eMdValue, String uMdValue) {
		try {
			String hql = "from Event as model where 1=1 and model.userMd5Value = ? and model.md5Value = ? ";
			Event e = (Event) findUnique(hql, uMdValue, eMdValue);
			if (null != e) {
				return e;
			}
		} catch (Exception ex) {
			logger.error("Query Event by UserMdValue Fail." + uMdValue);
		}
		return null;
	}

	public List<Event> getAllEventOrderByDate() {
		try {
			String hql = "from Event as model where 1=1 order by model.startDate desc ,model.createDateTime desc ";
			List<Event> list = findByHql(hql);
			if(null != list && list.size() > 0){
				return list;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Query All Events,No data.");
		}
		
		return null;
	}

}
