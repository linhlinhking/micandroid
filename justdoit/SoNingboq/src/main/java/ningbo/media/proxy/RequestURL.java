package ningbo.media.proxy;

import java.util.List;
import ningbo.media.proxy.bean.FormParamter;
import ningbo.media.proxy.http.ProxyHttpClient;
import ningbo.media.proxy.util.ParamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestURL {
	
	ProxyHttpClient http = new ProxyHttpClient();// 使用同步HTTP方式
	private static Logger log = LoggerFactory.getLogger(RequestURL.class);

	/**
	 * get json or xml resource from remote api
	 * 
	 * @param url
	 * @param params
	 * @param oauth
	 * @return
	 * @throws Exception
	 */
	public String get(String url, List<FormParamter> parameters) throws Exception {
		ParamClient client = new ParamClient();
		String queryString = client.getParams(url, "GET",parameters);
		return http.httpGet(url, queryString);
	}

	/**
	 * submit a post request to remote api
	 * 
	 * @param url
	 * @param parameters
	 * @param oauth
	 * @return
	 * @throws Exception
	 */
	public String post(String url, List<FormParamter> parameters) throws Exception {
		ParamClient client = new ParamClient();
		String queryString = client.getParams(url, "POST",parameters);
		log.info("RequestAPI postContent queryString = " + queryString);
		return http.httpPost(url, queryString);
	}

	/**
	 * submit a post request with a file to remote api
	 * 
	 * @param url
	 * @param parameters
	 * @param filePath
	 * @param oauth
	 * @return
	 * @throws Exception
	 */
	public String postFile(String url, List<FormParamter> parameters,
			List<FormParamter> files) throws Exception {
		ParamClient client = new ParamClient();
		String queryString = client.getParams(url, "POST",parameters);
		return http.httpPostWithFile(url, queryString, files);
	}
}
