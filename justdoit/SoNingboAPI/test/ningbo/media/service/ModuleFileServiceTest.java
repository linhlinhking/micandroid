package ningbo.media.service;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.ModuleFile;

import org.junit.Test;

public class ModuleFileServiceTest extends BaseTest {
	
	@Resource
	private ModuleFileService moduleFileService ;
	
	@Test
	public void testQuery(){
		List<ModuleFile> files = moduleFileService.queryModuleFileByType(27, 2, 1) ;
		if(null != files && files.size() > 0){
			for(ModuleFile f : files){
				System.out.println(f.getFileHash()) ;
			}
		}
		
	}
	
	
}
