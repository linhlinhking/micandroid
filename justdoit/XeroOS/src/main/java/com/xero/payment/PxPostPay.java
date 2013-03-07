package com.xero.payment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.xml.sax.SAXException;

public class PxPostPay {

	private static Logger logger = LoggerFactory.getLogger(PxPay.class);

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

	public static PxPostResponse pareseResponseXml(String xml,String url) {
		PxPostResponse px = new PxPostResponse();
		try {
			String responseXml = submitXml(xml,url) ;
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(
					responseXml.getBytes("UTF-8"));
			Document doc = docBuilder.parse(is);
			NodeList nodes = doc.getElementsByTagName("Txn");
			//nodes.item(0).getChildNodes() ;
			Element element = (Element)nodes.item(0) ;
			
			
			NodeList name;
			Element line;
			String value ;
			TxnTransaction txnBean = new TxnTransaction() ;
			
			NodeList transNodes = element.getElementsByTagName("Transaction") ;
			Element transElement = (Element)transNodes.item(0) ;
			
			name = transElement.getElementsByTagName("Authorized") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAuthorized(value) ;
			
			name = transElement.getElementsByTagName("ReCo") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setReCo(value) ;
			
			name = transElement.getElementsByTagName("RxDate") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setRxDate(value) ;
			
			name = transElement.getElementsByTagName("RxDateLocal") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setRxDateLocal(value) ;
			
			name = transElement.getElementsByTagName("LocalTimeZone") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setLocalTimeZone(value) ;
			
			name = transElement.getElementsByTagName("MerchantReference") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setMerchantReference(value) ;
			
			name = transElement.getElementsByTagName("CardName") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCardName(value) ;
			
			name = transElement.getElementsByTagName("Retry") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setRetry(value) ;
			
			name = transElement.getElementsByTagName("StatusRequired") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setStatusRequired(value) ;
			
			name = transElement.getElementsByTagName("AuthCode") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAuthCode(value) ;
			
			name = transElement.getElementsByTagName("AmountBalance") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAmountBalance(value) ;
			
			name = transElement.getElementsByTagName("Amount") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAmount(value) ;
			
			name = transElement.getElementsByTagName("CurrencyId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCurrencyId(value) ;
			
			name = transElement.getElementsByTagName("InputCurrencyId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setInputCurrencyId(value) ;
			
			name = transElement.getElementsByTagName("InputCurrencyName") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setInputCurrencyName(value) ;
			
			name = transElement.getElementsByTagName("CurrencyRate") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCurrencyRate(value) ;
			
			name = transElement.getElementsByTagName("CurrencyName") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCurrencyName(value) ;
			
			name = transElement.getElementsByTagName("CardHolderName") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCardHolderName(value) ;
			
			name = transElement.getElementsByTagName("DateSettlement") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setDateSettlement(value) ;
			
			name = transElement.getElementsByTagName("TxnType") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setTxnType(value) ;
			
			name = transElement.getElementsByTagName("CardNumber") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCardNumber(value) ;
			
			name = transElement.getElementsByTagName("TxnMac") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setTxnMac(value) ;
			
			name = transElement.getElementsByTagName("DateExpiry") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setDateExpiry(value) ;
			
			name = transElement.getElementsByTagName("ProductId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setProductId(value) ;
			
			name = transElement.getElementsByTagName("AcquirerDate") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirerDate(value) ;
			
			
			name = transElement.getElementsByTagName("AcquirerTime") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirerTime(value) ;
			
			name = transElement.getElementsByTagName("AcquirerId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirerId(value) ;
			
			name = transElement.getElementsByTagName("Acquirer") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirer(value) ;
			
			name = transElement.getElementsByTagName("AcquirerReCo") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirerReCo(value) ;
			
			name = transElement.getElementsByTagName("AcquirerResponseText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirerResponseText(value) ;
			
			name = transElement.getElementsByTagName("TestMode") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setTestMode(value) ;
			
			name = transElement.getElementsByTagName("CardId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCardId(value) ;
			
			name = transElement.getElementsByTagName("CardHolderResponseText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCardHolderResponseText(value) ;
			
			name = transElement.getElementsByTagName("CardHolderHelpText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCardHolderHelpText(value) ;
			
			name = transElement.getElementsByTagName("CardHolderResponseDescription") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCardHolderResponseDescription(value) ;
			
			name = transElement.getElementsByTagName("MerchantResponseText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setMerchantResponseText(value) ;
			
			name = transElement.getElementsByTagName("MerchantHelpText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setMerchantHelpText(value) ;
			
			name = transElement.getElementsByTagName("MerchantResponseDescription") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setMerchantResponseDescription(value) ;
			
			name = transElement.getElementsByTagName("UrlFail") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setUrlFail(value) ;
			
			name = transElement.getElementsByTagName("UrlSuccess") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setUrlSuccess(value) ;
			
			name = transElement.getElementsByTagName("EnablePostResponse") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setEnablePostResponse(value) ;
			
			name = transElement.getElementsByTagName("PxPayName") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setPxPayName(value) ;
			
			name = transElement.getElementsByTagName("PxPayLogoSrc") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setPxPayLogoSrc(value) ;
			
			name = transElement.getElementsByTagName("PxPayUserId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setPxPayUserId(value) ;
			
			name = transElement.getElementsByTagName("PxPayXsl") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setPxPayXsl(value) ;
			
			name = transElement.getElementsByTagName("PxPayBgColor") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setPxPayBgColor(value) ;
			
			name = transElement.getElementsByTagName("PxPayOptions") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setPxPayOptions(value) ;
			
			name = transElement.getElementsByTagName("Cvc2ResultCode") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setCvc2ResultCode(value) ;
			
			name = transElement.getElementsByTagName("AcquirerPort") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirerPort(value) ;
			
			name = transElement.getElementsByTagName("AcquirerTxnRef") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAcquirerTxnRef(value) ;
			
			name = transElement.getElementsByTagName("GroupAccount") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setGroupAccount(value) ;
			
			name = transElement.getElementsByTagName("DpsTxnRef") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setDpsTxnRef(value) ;
			
			name = transElement.getElementsByTagName("AllowRetry") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setAllowRetry(value) ;
			
			name = transElement.getElementsByTagName("DpsBillingId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setDpsBillingId(value) ;
			
			name = transElement.getElementsByTagName("BillingId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setBillingId(value) ;
			
			name = transElement.getElementsByTagName("TransactionId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setTransactionId(value) ;
			
			name = transElement.getElementsByTagName("PxHostId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setPxHostId(value) ;
			
			name = transElement.getElementsByTagName("RmReason") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setRmReason(value) ;
			
			name = transElement.getElementsByTagName("RmReasonId") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setRmReasonId(value) ;
			
			name = transElement.getElementsByTagName("RiskScore") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setRiskScore(value) ;
			
			name = transElement.getElementsByTagName("RiskScoreText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			txnBean.setRiskScoreText(value) ;
			
			px.setTransaction(txnBean) ;
			
			px.setResponseXml(responseXml) ;
			
			name = element.getElementsByTagName("ReCo") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			px.setReCo(value) ;
			
			name = element.getElementsByTagName("ResponseText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			px.setResponseText(value) ;
			
			name = element.getElementsByTagName("HelpText") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			px.setHelpText(value) ;
			
			name = element.getElementsByTagName("Success") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			px.setSuccess(value) ;
			
			name = element.getElementsByTagName("DpsTxnRef") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			px.setDpsTxnRef(value) ;
			
			name = element.getElementsByTagName("TxnRef") ;
			line = (Element)name.item(0);
			value = getCharacterDataFromElement(line);
			px.setTxnRef(value) ;
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return px;

	}
	
	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
}
