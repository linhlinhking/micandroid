package ningbo.media.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			// \\s*|\t|\r|\n
			Pattern p = Pattern.compile("\\s*|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String randomString() {
		String result = "";
		
		for (int i = 0; i < 6; i++) {//26 97
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}

		return result;
	}

	public static void main(String[] args) {

		for(int i=0 ;i<10;i++){
			System.out.println(randomString()) ;
		}
	}
}
