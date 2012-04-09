package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.SecondCategory;
import ningbo.media.core.service.BaseService;
import ningbo.media.rest.dto.SecondCategoryData;

public interface SecondCategoryService extends
		BaseService<SecondCategory, Integer> {
	
	public List<SecondCategoryData> querySecondCategoryData(Integer id) ;

}
