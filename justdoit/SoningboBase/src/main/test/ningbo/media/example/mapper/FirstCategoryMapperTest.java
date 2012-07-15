package ningbo.media.example.mapper;

import java.util.List;

import javax.inject.Inject;

import ningbo.media.BaseTest;
import ningbo.media.website.entity.FirstCategory;
import ningbo.media.website.mapper.FirstCategoryMapper;

import org.junit.Test;

public class FirstCategoryMapperTest extends BaseTest{

	@Inject
	private FirstCategoryMapper mapper ;
	
	@Test
	public void testGetFirstCategoryById(){
		FirstCategory fc = mapper.getFirstCategoryById(1) ;
		System.out.println(fc.getName_en()) ;
	}
	
	public void testGetAll(){
		List<FirstCategory> tempList = mapper.getAll() ;
		for(FirstCategory first : tempList){
			System.out.println(first.getName_cn()) ;
		}
	}
	
	
	public void getFirstCategoryByName(){
		FirstCategory temp = mapper.getFirstCategoryByName("服务");
		if(null != temp){
			System.out.println(temp.getName_cn() + "->" + temp.getKeywords_cn()) ;
		}
	}
}
