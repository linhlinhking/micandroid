package ningbo.media.service;



import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.FirstCategory;

import org.junit.Test;


public class FirstCategoryServiceTest extends BaseTest {

	@Resource
	private FirstCategoryService firstCategoryService ;
	
	@Test
	public void test1(){
		FirstCategory fc = firstCategoryService.get(1) ;
		
		System.out.println(fc.getName_cn());
	}
}
