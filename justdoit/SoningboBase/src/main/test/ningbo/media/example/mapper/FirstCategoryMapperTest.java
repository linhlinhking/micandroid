package ningbo.media.example.mapper;

import javax.inject.Inject;

import ningbo.media.BaseTest;
import ningbo.media.example.entity.FirstCategory;

import org.junit.Test;

public class FirstCategoryMapperTest extends BaseTest{

	@Inject
	private FirstCategoryMapper mapper ;
	
	@Test
	public void testGetFirstCategoryById(){
		FirstCategory fc = mapper.getFirstCategoryById(String.valueOf(1)) ;
		System.out.println(fc.getName_en()) ;
	}
}
