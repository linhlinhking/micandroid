package ningbo.media.service;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.website.entity.FirstCategory;
import ningbo.media.website.service.FirstCategoryService;

import org.junit.Test;

public class FirstCategoryServiceTest extends BaseTest{

	@Resource
	private FirstCategoryService firstCategoryService ;
	
	@Test
	public void testGetFirstCategoryById(){
		FirstCategory fc = firstCategoryService.getFirstCategoryById(1) ;
		if(null != fc){
			System.out.println(fc.getName_cn()) ;
		}
	}
}
