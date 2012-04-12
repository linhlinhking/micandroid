package ningbo.media.web.api;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import ningbo.media.web.bean.QParameter;
import ningbo.media.web.http.SoNingboHttpClient;
import ningbo.media.web.util.OAuthClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class RequestAPI {
	SoNingboHttpClient http = new SoNingboHttpClient();// 使用同步HTTP方式
	private static Log log = LogFactory.getLog(RequestAPI.class);

	// SoNingboHttpClient http = new AsyncHttpClient();//使用异步HTTP方式
	// AsyncCallBack callBack = new AsyncCallBack();

	/**
	 * get json or xml resource from remote api
	 * 
	 * @param url
	 * @param params
	 * @param oauth
	 * @return
	 * @throws Exception
	 */
	public String getResource(String url, List<QParameter> parameters) throws Exception {
		OAuthClient oac = new OAuthClient();
		String queryString = oac.getOauthParams(url, "GET",parameters);
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
	public String postContent(String url, List<QParameter> parameters) throws Exception {
		OAuthClient oac = new OAuthClient();
		String queryString = oac.getOauthParams(url, "POST",parameters);
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
	public String postFile(String url, List<QParameter> parameters,
			List<QParameter> files) throws Exception {
		

		OAuthClient oac = new OAuthClient();
		String queryString = oac.getOauthParams(url, "POST",parameters);

		return http.httpPostWithFile(url, queryString, files);
	}

	public String[] message(String result) {
		String[] arr = new String[2];

		try {
			SAXReader saxReader = new SAXReader();
			Document xml = saxReader.read(new ByteArrayInputStream(result
					.getBytes("UTF-8")));
			arr[0] = xml.selectSingleNode("root/ret").getText();
			// arr[1] = xml.selectSingleNode("root/msg").getText();
			switch (Integer.parseInt(arr[0])) {
			case 0:
				arr[1] = "操作成功";
				break;
			case 1:
				arr[1] = "参数错误";
				break;
			case 2:
				arr[1] = "频率受限";
				break;
			case 3:
				arr[1] = "鉴权失败";
				break;
			case 4:
				arr[1] = "服务器内部错误";
				break;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			arr[0] = "4";
			arr[1] = e.toString();
		} catch (DocumentException e) {
			e.printStackTrace();
			arr[0] = "4";
			arr[1] = e.toString();
		}

		return arr;
	}
}
