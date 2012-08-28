package ningbo.media.service.impl;

import ningbo.media.bean.EventDate;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.EventDateDao;
import ningbo.media.service.EventDateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("eventDateService")
public class EventDateServiceImpl extends BaseServiceImpl<EventDate, Integer>
		implements EventDateService {

	@Autowired
	public EventDateServiceImpl(
			@Qualifier("eventDateDao") EventDateDao eventDateDao) {
		super(eventDateDao);
	}
}
