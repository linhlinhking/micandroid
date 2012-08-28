package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.EventDate;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.EventDateDao;

@Repository("eventDateDao")
public class EventDateDaoImpl extends BaseDaoImpl<EventDate, Integer> implements
		EventDateDao {

	public EventDateDaoImpl() {
		super(EventDate.class);
	}
}
