package ningbo.media.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import ningbo.media.core.mapper.SqlMapper;
import ningbo.media.example.entity.FirstCategory;

public interface FirstCategoryMapper extends SqlMapper{

	public FirstCategory getFirstCategoryById(String id);
	
	public List<FirstCategory> getAll();
	
	@Select("select * from firstCategory where 1=1 and name_cn = #{name}")
	public FirstCategory getFirstCategoryByName(String name);
}
