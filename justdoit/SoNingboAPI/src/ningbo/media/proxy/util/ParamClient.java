package ningbo.media.proxy.util;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import ningbo.media.proxy.bean.FormParamter;

public class ParamClient {

	/**
	 * 
	 * @param url
	 * @param httpMethod
	 * @param parameters
	 * @return
	 */
	public String getParams(String url, String httpMethod,
			List<FormParamter> parameters) {
		if (null == parameters) {
			return url;
		}
		Collections.sort(parameters);
		String urlWithParameter = url;

		String parameterString = encodeParams(parameters);
		if (parameterString != null && !parameterString.equals("")) {
			urlWithParameter += "?" + parameterString;
		}

		return parameterString;
	}

	/**
	 * 处理请求URL
	 * 
	 * @param url
	 * @return
	 */
	public static String getNormalizedUrl(URL url) {
		try {
			StringBuilder buf = new StringBuilder();
			buf.append(url.getProtocol());
			buf.append("://");
			buf.append(url.getHost());
			if ((url.getProtocol().equals("http") || url.getProtocol().equals(
					"https"))
					&& url.getPort() != -1) {
				buf.append(":");
				buf.append(url.getPort());
			}
			buf.append(url.getPath());
			return buf.toString();
		} catch (Exception e) {
		}
		return null;
	}
	
	
	/**
	 * 编码请求参数
	 * 
	 * @param params
	 * @return
	 */
	private static String encodeParams(List<FormParamter> params) {
		StringBuilder result = new StringBuilder();
		for (FormParamter param : params) {
			if (result.length() != 0) {
				result.append("&");
			}
			result.append(StringUtil.encode(param.getName()));
			result.append("=");
			result.append(StringUtil.encode(param.getValue()));
		}
		return result.toString();
	}
}
