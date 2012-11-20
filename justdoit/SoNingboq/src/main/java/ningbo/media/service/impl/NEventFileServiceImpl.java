package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.NEventFile;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.NEventFileDao;
import ningbo.media.service.NEventFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("nEventFileService")
public class NEventFileServiceImpl extends BaseServiceImpl<NEventFile, Integer>
		implements NEventFileService {

	private Logger logger = LoggerFactory.getLogger(getClass()) ;
	
	@Resource
	private NEventFileDao nEventFileDao ;
	
	@Autowired
	public NEventFileServiceImpl(@Qualifier("nEventFileDao") NEventFileDao nEventFileDao) {
		super(nEventFileDao);
	}

	public List<NEventFile> queryNEventFileByEventId(Integer eventId)
			throws ServiceException {
		try{
			String hql = "from NEventFile as bean where 1=1 and bean.eventId = ? " ;
			List<NEventFile> eventFiles = nEventFileDao.findByHql(hql, eventId) ;
			return eventFiles;
		}catch(Exception ex){
			logger.error("Get The Event Error.EventId is " + eventId, ex) ;
			return null ;
		}
		
	}


}
