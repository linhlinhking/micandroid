package ningbo.media.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestArray {

	
	@Test
	public void testArr(){
		
		List<Integer> lists = new ArrayList<Integer>();
		for(int i=0;i<10000;i++){
			lists.add(i) ;
		}
		
		long s1 = System.currentTimeMillis() ;
		for(Integer t : lists){
			System.out.println(t) ;
		}
		long e1 = System.currentTimeMillis() ;
		System.out.println("for each耗时：" + String.valueOf(e1-s1));
		
		
		long s2 = System.currentTimeMillis() ;
		for(int i=0,j=lists.size();i < j;i++){
			Integer tmp = lists.get(i);
			System.out.println(String.valueOf(tmp)) ;
		}
		long e2 = System.currentTimeMillis() ;
		System.out.println("for耗时：" + String.valueOf(e2-s2));
		
	}
}
