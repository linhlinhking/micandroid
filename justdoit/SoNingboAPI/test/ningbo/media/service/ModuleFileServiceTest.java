package ningbo.media.service;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.rest.dto.ModuleFileData;

import org.junit.Test;

public class ModuleFileServiceTest extends BaseTest {
	
	@Resource
	private ModuleFileService moduleFileService ;
	
	@Test
	public void testQuery(){
		List<ModuleFileData> files = moduleFileService.queryModuleFileByUserHeader(1) ;
		if(null != files && files.size() > 0){
			for(ModuleFileData f : files){
				System.out.println(f.getFileName()) ;
				System.out.println(f.getWidth()) ;
			}
		}
		
	}
	
	
}
