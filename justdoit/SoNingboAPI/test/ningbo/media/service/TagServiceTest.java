package ningbo.media.service;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Tags;

import org.junit.Test;


public class TagServiceTest extends BaseTest{

	@Resource
	private TagsService tagsService;
	
	
	public void testTag(){
		Tags t = new Tags();
		
		t.setTagName("china");
		tagsService.save(t) ;
		System.out.println("saved") ;
		
	}
	
	@Test
	public void testTagUpdate(){
		Tags t = tagsService.get(1) ;
		
		System.out.println(t.getTagName()) ;
		
		t.setTagName("wds");
		
		tagsService.update(t) ;
		
		System.out.println("update") ;
		
	}
}
