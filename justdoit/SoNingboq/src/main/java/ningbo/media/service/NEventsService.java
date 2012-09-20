package ningbo.media.service;

import java.util.List;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.NEvents;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.service.BaseService;

public interface NEventsService extends BaseService<NEvents, Integer>{

	public List<NEvents> getEventsByType(String todayString) throws ServiceException;
	
	public Pagination<NEvents> getAllByPage(int pageNo,int pageSize) throws ServiceException;
}
