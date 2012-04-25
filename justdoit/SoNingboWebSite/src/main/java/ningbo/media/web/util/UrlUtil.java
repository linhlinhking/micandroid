package ningbo.media.web.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtil {

	private static final String ESCAPE_0 = "/";

	public static int getDeep(String path) {
		if (null == path) {
			return -1;
		}
		String[] temp = getUrlElement(path) ;
		return temp.length ;
	}

	private static String getUrlPath(String url) {
		try {
			URL u = new URL(url);
			return u.getPath();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[] getUrlElement(String url){
		String path = getUrlPath(url) ;
		if (null == path) {
			return null;
		}
		String temp = path.substring(path.indexOf(ESCAPE_0) + 1, path.length());
		String[] arrs = temp.split(ESCAPE_0) ;
		return arrs ;
	}
}
