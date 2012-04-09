package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.FirstCategory;
import ningbo.media.core.service.BaseService;
import ningbo.media.rest.dto.FirstCategoryData;

public interface FirstCategoryService extends
		BaseService<FirstCategory, Integer> {

	public List<String> getAllCagegoryName(String local) ;
	
	public FirstCategoryData getFirstCategoryById(Integer id) ;
}
