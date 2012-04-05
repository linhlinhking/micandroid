package ningbo.media.util;

import java.util.Locale;

import org.junit.Test;

public class ResourcesTest {

	
	public void testGetText(){
		System.out.println(Resources.getText(Resources.GLOBAL_PATH)) ;
	}
	
	@Test
	public void testLocale(){
		System.out.println(Locale.getDefault().getCountry()) ;
		
	}
}
