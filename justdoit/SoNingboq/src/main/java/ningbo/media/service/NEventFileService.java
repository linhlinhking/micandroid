package ningbo.media.service;

import java.util.List;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.NEventFile;
import ningbo.media.core.service.BaseService;

public interface NEventFileService extends BaseService<NEventFile, Integer> {

	public List<NEventFile> queryNEventFileByEventId(Integer eventId)
			throws ServiceException;
}
