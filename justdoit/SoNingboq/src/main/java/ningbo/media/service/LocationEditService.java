package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.LocationEdit;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.service.BaseService;

public interface LocationEditService extends BaseService<LocationEdit, Integer> {

	public List<LocationEdit> getAllEditInfors(String locationMd5);
	
	public Pagination<LocationEdit> getAllEditsByPage(int pageNo,int pageSize);
	
	public Pagination<LocationEdit> getLocationEditsByPage(String locationMd5Value,int pageNo, int pageSize);
	
}
