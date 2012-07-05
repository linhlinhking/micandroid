package ningbo.media.example.mapper;

import java.util.List;

import javax.inject.Inject;

import ningbo.media.BaseTest;
import ningbo.media.example.entity.FirstCategory;

import org.junit.Test;

public class FirstCategoryMapperTest extends BaseTest{

	@Inject
	private FirstCategoryMapper mapper ;
	
	
	public void testGetFirstCategoryById(){
		FirstCategory fc = mapper.getFirstCategoryById(String.valueOf(1)) ;
		System.out.println(fc.getName_en()) ;
	}
	
	@Test
	public void testGetAll(){
		List<FirstCategory> tempList = mapper.getAll() ;
		for(FirstCategory first : tempList){
			System.out.println(first.getName_cn()) ;
		}
	}
}
