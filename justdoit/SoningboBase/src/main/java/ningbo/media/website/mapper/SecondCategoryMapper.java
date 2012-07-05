package ningbo.media.website.mapper;

import java.util.List;

import ningbo.media.core.mapper.SqlMapper;
import ningbo.media.website.entity.SecondCategory;

public interface SecondCategoryMapper extends SqlMapper{

	public SecondCategory getSecondCategoryById(String id);
	
	public List<SecondCategory> getAll();
	
	public List<SecondCategory> getSecondCategoryByFirstId(Integer firstId) ;
	
	public void addSecondCategory(SecondCategory second);
}
