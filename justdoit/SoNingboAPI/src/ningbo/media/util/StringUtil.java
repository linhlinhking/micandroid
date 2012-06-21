package ningbo.media.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			//\\s*|\t|\r|\n
			Pattern p = Pattern.compile("\\s*|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static void main(String[] args) {

		System.out.println(replaceBlank(" /Njust/n do/t it! "));
	}
}
