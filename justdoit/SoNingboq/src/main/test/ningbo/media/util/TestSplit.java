package ningbo.media.util;

import org.junit.Test;

public class TestSplit {

	
	@Test
	public void test1(){
		//String str = "123.jpg,124.jpg,123_deba-56&%#wet.jpg,我是忠问.jpg" ;
		
		String[] arry = StringUtil.parseString("", ",");
		
		for(int i=0,j=arry.length;i<j;i++){
			System.out.println(arry[i]) ;
		}
	}
}
