package ningbo.media.website.service;

import java.util.List;

import ningbo.media.website.entity.FirstCategory;

public interface FirstCategoryService {
	
	public List<FirstCategory> getAll() ;
	
	public FirstCategory getFirstCategoryById(Integer id);

}
