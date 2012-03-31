package ningbo.media.service;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;

import org.junit.Test;

public class FirstCategoryServiceTest extends BaseTest {

	@Resource
	private FirstCategoryService firstCategoryService;

	@Test
	public void testGetAllCagegoryName() {
		List<String> list = firstCategoryService.getAllCagegoryName("zh");
		for (String temp : list) {
			System.out.println(temp);
		}

	}
}
