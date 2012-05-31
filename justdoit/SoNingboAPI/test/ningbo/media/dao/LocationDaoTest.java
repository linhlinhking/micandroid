package ningbo.media.dao;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Location;
import ningbo.media.util.Pinyin;

import org.junit.Test;

public class LocationDaoTest extends BaseTest{

	@Resource
	private LocationDao locationDao ;
	
	@Test
	public void updateLocation(){
		Location loc = locationDao.get(1) ;
		
		loc.setName_py(Pinyin.getPinYin(loc.getName_cn())) ;
		try{
			
			locationDao.update(loc); 
		}catch(Exception ex){
			ex.printStackTrace() ;
		}
		
		System.out.println(Pinyin.getPinYin(loc.getName_cn())) ;
	}
}
