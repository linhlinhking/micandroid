package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.NEvents;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.NEventsDao;

@Repository("nEventsDao")
public class NEventsDaoImpl extends BaseDaoImpl<NEvents, Integer> implements
		NEventsDao {
	
	public NEventsDaoImpl(){
		super(NEvents.class);
	}
}
