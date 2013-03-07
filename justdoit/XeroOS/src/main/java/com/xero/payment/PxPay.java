package com.xero.payment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PxPay {

	private static Logger logger = LoggerFactory.getLogger(PxPay.class);

	public static String createRequest(String userId, String key,
			PxRequest pxRequest, String url) {
		try {
			pxRequest.setPxPayUserId(userId);
			pxRequest.setPxPayKey(key);

			String inputXml = pxRequest.getXml();
			String responseXml = PxPay.submitXml(inputXml, url);

			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			InputStream is = new ByteArrayInputStream(
					responseXml.getBytes("UTF-8"));
			Document doc = docBuilder.parse(is);
			NodeList nodes = doc.getElementsByTagName("Request");
			Element element = (Element) nodes.item(0);

			NodeList name;
			Element line;

			name = element.getElementsByTagName("URI");
			line = (Element) name.item(0);
			String uri = PxPay.getCharacterDataFromElement(line);
			return uri;
		} catch (Exception e) {
			logger.error("Create Request Error", e);
			return null;
		}
	}

	public static PxResponse processResponse(String userId, String key,
			String response, String url) {
		try {
			String inputXml = "<ProcessResponse><PxPayUserId>" + userId
					+ "</PxPayUserId><PxPayKey>" + key
					+ "</PxPayKey><Response>" + response
					+ "</Response></ProcessResponse>";

			String outputXml;
			outputXml = PxPay.submitXml(inputXml, url);
			PxResponse res = new PxResponse(outputXml);
			return res;
		} catch (Exception ex) {
			logger.error("Get Payment Response Error. url:" + url, ex);
			return null;
		}
	}

	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	private static String submitXml(String xml, String url) {
		HttpClient client = new DefaultHttpClient();
		try {
			// Prepare the POST Request
			HttpPost pxpayRequest = new HttpPost(url);
			pxpayRequest.setEntity(new StringEntity(xml));

			// Execute the Request And extract the Response
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			String resBody = client.execute(pxpayRequest, resHandler);

			return resBody;
		} catch (UnsupportedEncodingException e) {
			logger.error("Submit Xml UnsupportedEncodingException", e);
		} catch (ClientProtocolException e) {
			logger.error("Submit Xml ClientProtocolException", e);
		} catch (IOException e) {
			logger.error("Submit Xml IOException", e);
		}
		return null;
	}

}
