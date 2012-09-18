package ningbo.media.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.util.MD5;
import ningbo.media.util.Pinyin;

import org.junit.Test;

public class UpdateDatabaseTest extends BaseTest {

	@Resource
	private LocationService locationService;

	@Resource
	private SecondCategoryService secondCategoryService;

	@Test
	public void testSaveAll(){
		try{
			for(int i=47597;i<=47706;i++){
				Location loc = locationService.get(i);
				List<SecondCategory> lists = new ArrayList<SecondCategory>();
				SecondCategory sc = secondCategoryService.get(290) ;
				lists.add(sc) ;
				
				Integer id = loc.getId() ;
				String name_cn = loc.getName_cn() ;
				String md5Value = MD5.calcMD5(String.valueOf(id)) ;
				String py = Pinyin.getPinYin(name_cn) ;
				
				
				loc.setMd5Value(md5Value) ;
				loc.setName_py(py) ;
				loc.setSecondCategorys(lists) ;
				
				locationService.update(loc) ;
				
			}
			System.out.println("Update Successfully.") ;
		}catch(Exception ex){
			ex.printStackTrace() ;
			System.out.println("Update Error.") ;
		}
	}
}
