package ningbo.media.util;

import java.util.ArrayList;
import java.util.List;
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

		for (int i = 0; i < 6; i++) {// 26 97
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}

		return result;
	}

	public static List<String> getDateString(String s1, String s2) {
		List<String> lists = new ArrayList<String>();
		int l1 = s1.lastIndexOf("-");
		int l2 = s2.lastIndexOf("-");
		String sub = s1.substring(0, l1 + 1);
		Integer v1 = Integer.valueOf(s1.substring(l1 + 1));
		Integer v2 = Integer.valueOf(s2.substring(l2 + 1));
		
		if (v1 > v2) {
			return null;
		} else {
			for (; v1 <= v2; v1++) {
				StringBuffer b = new StringBuffer();
				b.append(sub);
				if (v1 < 10) {
					b.append("0" + v1);
				} else {
					b.append(v1);
				}
				lists.add(b.toString());
			}
		}

		return lists;
	}
	
	public static List<String> getCustomDateString(String dateString){
		List<String> tmpList = new ArrayList<String>();
		String[] strs = dateString.split(",") ;
		if(null != strs && strs.length > 0){
			for(int i=0,j=strs.length;i<j;i++){
				tmpList.add(strs[i]) ;
			}
			
			return tmpList ;
		}else{
			return null ;
		}
	}
}
