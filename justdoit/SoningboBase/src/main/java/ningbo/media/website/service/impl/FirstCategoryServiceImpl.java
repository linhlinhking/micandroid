package ningbo.media.website.service.impl;

import java.util.List;

import javax.inject.Inject;
import ningbo.media.website.entity.FirstCategory;
import ningbo.media.website.mapper.FirstCategoryMapper;
import ningbo.media.website.service.FirstCategoryService;

import org.springframework.stereotype.Service;

@Service("firstCategoryService")
public class FirstCategoryServiceImpl implements FirstCategoryService{

	@Inject
	private FirstCategoryMapper fistMapper ;
	
	public List<FirstCategory> getAll() {
		return fistMapper.getAll();
	}

	public FirstCategory getFirstCategoryById(Integer id) {
		return fistMapper.getFirstCategoryById(id);
	}

}
