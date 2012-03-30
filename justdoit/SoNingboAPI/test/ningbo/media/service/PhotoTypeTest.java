package ningbo.media.service;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.PhotoType;

import org.junit.Test;

public class PhotoTypeTest extends BaseTest{

	@Resource
	private PhotoTypeService photoTypeService ;
	
	@Test
	public void testGet(){
		PhotoType t = photoTypeService.get(1) ;
		System.out.println(t.getType()) ;
	}
}
