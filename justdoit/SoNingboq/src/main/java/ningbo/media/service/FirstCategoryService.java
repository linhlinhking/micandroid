package ningbo.media.service;

import java.util.List;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.admin.util.Result;
import ningbo.media.bean.FirstCategory;
import ningbo.media.core.service.BaseService;
import ningbo.media.rest.dto.FirstCategoryData;
import ningbo.media.rest.dto.SecondCategoryData;

public interface FirstCategoryService extends
		BaseService<FirstCategory, Integer> {

	public FirstCategoryData getFirstCategoryById(Integer id);

	public List<SecondCategoryData> getFristCategoryByName(String name);
	
	public List<FirstCategoryData> getAllFirstCategory() ;
	
	public Result<FirstCategory> getCategoryById(Integer id) throws ServiceException;
}
