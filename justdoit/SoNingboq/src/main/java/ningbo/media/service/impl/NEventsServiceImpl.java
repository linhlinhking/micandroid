package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.NEvents;
import ningbo.media.core.page.Finder;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.NEventsDao;
import ningbo.media.service.NEventsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("nEventsService")
public class NEventsServiceImpl extends BaseServiceImpl<NEvents, Integer>
		implements NEventsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass()) ;

	@Resource
	private NEventsDao nEventsDao;

	@Autowired
	public NEventsServiceImpl(@Qualifier("nEventsDao") NEventsDao nEventsDao) {
		super(nEventsDao);
	}

	public List<NEvents> getEventsByType(String todayString) throws ServiceException {
		String hql = "select model from NEvents as model left join model.eventDates as bean where 1=1 and bean.endDate >= ? order by bean.startDate asc,bean.startTime asc ";
		List<NEvents> lists = nEventsDao.findByHql(hql, todayString) ;
		if(null == lists){
			logger.error("No "+todayString+" Events' Data") ;
			return null ;
		}
		return lists;
	}

	public Pagination<NEvents> getAllByPage(int pageNo, int pageSize)
			throws ServiceException {
		final Finder f = Finder.create("from NEvents as bean where 1=1 ") ;
		f.append(" order by bean.id desc ") ;
		return nEventsDao.findByPage(f, pageNo, pageSize);
	}

}
