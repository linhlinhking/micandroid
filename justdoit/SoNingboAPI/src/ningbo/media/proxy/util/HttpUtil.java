package ningbo.media.proxy.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ningbo.media.proxy.bean.FormParamter;

public class HttpUtil {

	/**
	 * Return the list of query parameters based on the specified query string.
	 * 
	 * @param queryString
	 * @return the list of query parameters.
	 */
	public static List<FormParamter> getQueryParameters(String queryString) {
		if (queryString.startsWith("?")) {
			queryString = queryString.substring(1);
		}

		List<FormParamter> result = new ArrayList<FormParamter>();
		if (queryString != null && !queryString.equals("")) {
			String[] p = queryString.split("&");
			for (String s : p) {
				if (s != null && !s.equals("")) {
					if (s.indexOf('=') > -1) {
						String[] pv = s.split("=");
						if (pv.length > 1) {
							result.add(new FormParamter(pv[0], pv[1]));
						}
					}
				}
			}
		}

		return result;

	}

	public static String formParamDecode(String value) {
		int nCount = 0;
		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) == '%') {
				i += 2;
			}
			nCount++;
		}

		byte[] sb = new byte[nCount];

		for (int i = 0, index = 0; i < value.length(); i++) {
			if (value.charAt(i) != '%') {
				sb[index++] = (byte) value.charAt(i);
			} else {
				StringBuilder sChar = new StringBuilder();
				sChar.append(value.charAt(i + 1));
				sChar.append(value.charAt(i + 2));
				sb[index++] = Integer.valueOf(sChar.toString(), 16).byteValue();
				i += 2;
			}
		}
		String decode = "";
		try {
			decode = new String(sb, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}
}
