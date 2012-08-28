package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.NEvents;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.NEventsDao;
import ningbo.media.service.NEventsService;

@Service("nEventsService")
public class NEventsServiceImpl extends BaseServiceImpl<NEvents, Integer>
		implements NEventsService {

	@Autowired
	public NEventsServiceImpl(@Qualifier("nEventsDao") NEventsDao nEventsDao) {
		super(nEventsDao);
	}
}
