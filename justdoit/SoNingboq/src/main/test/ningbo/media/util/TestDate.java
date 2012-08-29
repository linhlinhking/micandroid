package ningbo.media.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestDate {

	
	
	public void test1(){
		String s1 = "2012-08-01" ;
		String s2 = "2012-08-10" ;
		List<String> tmpList = getDateString(s1,s2) ;
		
		for(String str : tmpList){
			System.out.println(str) ;
		}
	}
	
	@Test
	public void test2(){
		String dateString = "2012-08-01,2012-08-09,2012-08-26" ;
		List<String> tmpList = StringUtil.getCustomDateString(dateString) ;
		
		for(String str : tmpList){
			System.out.println(str) ;
		}
	}
	
	public List<String> getDateString(String s1,String s2){
		List<String> lists = new ArrayList<String>() ;
		int l1 = s1.lastIndexOf("-") ;
		int l2 = s2.lastIndexOf("-") ; 
		String sub = s1.substring(0,l1+1) ;
		Integer v1 = Integer.valueOf(s1.substring(l1 + 1)) ;
		Integer v2 = Integer.valueOf(s2.substring(l2 + 1)) ;
		
		if(v1 > v2){
			return null ;
		}else{
			for(;v1<=v2;v1++){
				StringBuffer b = new StringBuffer();
				b.append(sub) ;
				if(v1 < 10){
					b.append("0"+v1) ;
				}else{
					b.append(v1) ;
				}
				
				lists.add(b.toString()) ;
			}
		}
		
		return lists ;
	}
}
