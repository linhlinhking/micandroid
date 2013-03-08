package com.xero.payment.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xero.core.Response.ResponseEntity;
import com.xero.core.Response.ResponseMessage;
import com.xero.payment.PxPay;
import com.xero.payment.PxPostPay;
import com.xero.payment.PxPostRequest;
import com.xero.payment.PxPostResponse;
import com.xero.payment.PxRequest;
import com.xero.payment.PxResponse;
import com.xero.payment.bean.PxParam;
import com.xero.payment.bean.TransactionLogger;
import com.xero.payment.service.TransactionLoggerService;

@Controller
public class PxPaymentController {
	
	private Logger logger = LoggerFactory.getLogger(getClass()) ;

	@Resource
	private PxParam pxParamConfig;

	@Resource
	private TransactionLoggerService transactionLoggerService;
	

	@RequestMapping(value = "/auth/index", method = RequestMethod.GET)
	public ModelAndView toPayment() {
		ModelAndView model = new ModelAndView();

		model.setViewName("/pay");
		return model;
	}

	@RequestMapping(value = "/auth/payment2", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PxPostResponse> paymentRequest(
			HttpServletRequest request, @RequestParam("amount") String amount,
			@RequestParam("cardHolderName") String cardHolderName,
			@RequestParam("cardNumber") String cardNumber,
			@RequestParam("merchantReference") String merchantReference,
			@RequestParam("dateExpiry") String dateExpiry,
			@RequestParam("cvc2") String cvc2,
			@RequestParam("cvc2Presence") String cvc2Presence,
			@RequestParam("currencyInput") String currencyInput,
			@RequestParam("txnId") String txnId,
			@RequestParam("txnType") String txnType,
			@RequestParam("enableBillingCard") String enableBillingCard,
			@RequestParam("enableAvsData") String enableAvsData,
			@RequestParam("avsAction") String avsAction,
			@RequestParam("avsPostCode") String avsPostCode,
			@RequestParam("avsStreetAddress") String avsStreetAddress,
			@RequestParam("startDate")String startDate,
			@RequestParam("issueNumber")String issueNumber,
			@RequestParam("currencyUserId") Integer userId,
			@RequestParam("companyId") Integer companyId) {

		ResponseEntity<PxPostResponse> resMsg = new ResponseEntity<PxPostResponse>(
				false);

		String postUsername = pxParamConfig.getPostUsername();
		String postPassword = pxParamConfig.getPostPassword();
		String postUrl = pxParamConfig.getPostUrl();

		PxPostRequest pxReq = new PxPostRequest();
		pxReq.setAmount(amount);
		pxReq.setInputCurrency(currencyInput);
		pxReq.setMerchantReference(merchantReference);
		pxReq.setTxnId(txnId);
		pxReq.setTxnType(txnType);
		pxReq.setPostUsername(postUsername);
		pxReq.setPostPassword(postPassword);
		pxReq.setDateExpiry(dateExpiry);
		pxReq.setCvc2(cvc2);
		pxReq.setCvc2Presence(cvc2Presence);
		pxReq.setCardHolderName(cardHolderName);
		pxReq.setCardNumber(cardNumber);
		pxReq.setEnableAddBillCard(enableBillingCard) ;
		pxReq.setEnableAvsData(enableAvsData) ;
		pxReq.setAvsAction(avsAction) ;
		pxReq.setAvsPostCode(avsPostCode) ;
		pxReq.setAvsStreetAddress(avsStreetAddress) ;
		pxReq.setDateStart(startDate);
		pxReq.setIssueNumber(issueNumber) ;

		String xml = pxReq.getXml();

		// String xmlResponse = PxPostPay.submitXml(xml, postUrl) ;
		// System.out.println(xmlResponse);
		PxPostResponse postRes = PxPostPay.pareseResponseXml(xml, postUrl);
		if (null != postRes) {
			try{
				// Save Transaction Log
				String resXml = postRes.getResponseXml();
				TransactionLogger transLogger = new TransactionLogger();
				transLogger.setTxn(resXml);
				transLogger.setUserId(userId);
				transLogger.setCompanyId(companyId);
				transLogger.setCreateDateTime(new Date());

				transactionLoggerService.saveOrUpdate(transLogger);
			}catch(Exception ex){
				logger.error("Save Transaction Log Error.",ex) ;
			}
			

			resMsg.setData(postRes);
			resMsg.setResult(true);
		} else {
			resMsg.setData(null);
			resMsg.setResult(false);
		}

		return resMsg;
	}

	@RequestMapping(value = "/auth/payment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage getContextParmas(HttpServletRequest request,
			@RequestParam("amount") String amount,
			@RequestParam("billingId") String billingId,
			@RequestParam("currencyInput") String currencyInput,
			@RequestParam("emailAddress") String emailAddress,
			@RequestParam("enableAddBillCard") String enableAddBillCard,
			@RequestParam("merchantReference") String merchantReference,
			@RequestParam("opt") String opt,
			@RequestParam("txnData1") String txnData1,
			@RequestParam("txnData2") String txnData2,
			@RequestParam("txnData3") String txnData3,
			@RequestParam("txnId") String txnId,
			@RequestParam("txnType") String txnType) {

		ResponseMessage resMsg = new ResponseMessage();

		String currentURL = request.getRequestURL().toString();
		String pxUserId = pxParamConfig.getPxUserId();
		String pxKey = pxParamConfig.getPxKey();
		String pxUrl = pxParamConfig.getPxUrl();

		PxRequest pxReq = new PxRequest();
		pxReq.setAmountInput(amount);
		pxReq.setBillingId(billingId);
		pxReq.setCurrencyInput(currencyInput);
		pxReq.setEmailAddress(emailAddress);
		pxReq.setEnableAddBillCard(enableAddBillCard);
		pxReq.setMerchantReference(merchantReference);
		pxReq.setOpt(opt);
		pxReq.setTxnData1(txnData1);
		pxReq.setTxnData2(txnData2);
		pxReq.setTxnData3(txnData3);
		pxReq.setTxnId(txnId);
		pxReq.setTxnType(txnType);
		pxReq.setUrlFail(currentURL);
		pxReq.setUrlSuccess(currentURL);

		String redirctUrl = PxPay.createRequest(pxUserId, pxKey, pxReq, pxUrl);
		System.out.println(redirctUrl);

		resMsg.setResult(true);
		if (null != redirctUrl) {
			resMsg.setUrl(redirctUrl);
		} else {
			resMsg.setUrl("/auth/index");
		}

		return resMsg;
	}

	@RequestMapping(value = "/auth/result", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PxResponse> getResponseByPx(
			@RequestParam("result") String result) {
		ResponseEntity<PxResponse> res = new ResponseEntity<PxResponse>(false);

		String pxUserId = pxParamConfig.getPxUserId();
		String pxKey = pxParamConfig.getPxKey();
		String pxUrl = pxParamConfig.getPxUrl();

		PxResponse pxRes = PxPay
				.processResponse(pxUserId, pxKey, result, pxUrl);
		if (null != pxRes) {
			res.setResult(true);
			res.setData(pxRes);
		} else {
			res.setData(null);
		}
		return res;
	}

}
