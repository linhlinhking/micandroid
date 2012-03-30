package ningbo.media.bean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class TestPath {

	private static final String PATH = "/soningbo/fileUpload/head";

	private String getNameByDate(String pathName) {
		StringBuffer buffer = new StringBuffer();

		SimpleDateFormat simple = new SimpleDateFormat("yyMMddHHmmss");
		String diff = "_" + simple.format(new Date());

		int idx = pathName.lastIndexOf(".");
		if (idx != -1) {
			buffer.append(pathName.substring(0, idx));
			buffer.append(diff);
			buffer.append(pathName.substring(idx));
		} else {
			buffer.append(pathName);
			buffer.append(diff);
		}

		return buffer.toString();
	}


	private String getPath() {
		return File.separator + "soningbo12" + File.separator + "fileUpload"
				+ File.separator + "header"+File.separator;
	}
	
	private String createDir(String path){
		File dir = new File(path) ;
		if(dir.exists()){
			System.out.println("aleardy") ;
			return path ;
		}else{
			System.out.println("new") ;
			dir.mkdirs() ;
			return dir.getAbsolutePath() + File.separator ;
		}
	}

	@Test
	public void print() {
		System.out.println(this.getNameByDate(getPath()
				+ "eclipse.jpg"));
		
		String flag = createDir("C:" + getPath()) ;
		System.out.println(flag) ;
	}
}
