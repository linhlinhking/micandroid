package ningbo.media.util;

import java.io.File;

public class FileTest {

	
	public static String makeTempFileDir() {
		String dir = Resources.getText(Resources.GLOBAL_PATH) + File.separator
				+ "temp" + File.separator;
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		return dir;
	}
	
	
	public static void main(String args[]){
		System.out.println(makeTempFileDir()) ;
	}
}
