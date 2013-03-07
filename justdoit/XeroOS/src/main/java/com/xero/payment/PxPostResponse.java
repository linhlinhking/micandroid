package com.xero.payment;

public class PxPostResponse {
	
	private String ReCo ;
	
	private String ResponseText ;
	
	private String HelpText ;
	
	private String Success ;
	
	private String DpsTxnRef ;
	
	private String TxnRef ;
	
	private String ResponseXml ;
	
	private TxnTransaction Transaction ;
	
	public PxPostResponse(){}
	
	
	
	public String getReCo() {
		return ReCo;
	}

	public void setReCo(String reCo) {
		ReCo = reCo;
	}

	public String getResponseText() {
		return ResponseText;
	}

	public void setResponseText(String responseText) {
		ResponseText = responseText;
	}

	public String getHelpText() {
		return HelpText;
	}

	public void setHelpText(String helpText) {
		HelpText = helpText;
	}

	public String getSuccess() {
		return Success;
	}

	public void setSuccess(String success) {
		Success = success;
	}

	public String getDpsTxnRef() {
		return DpsTxnRef;
	}

	public void setDpsTxnRef(String dpsTxnRef) {
		DpsTxnRef = dpsTxnRef;
	}

	public String getTxnRef() {
		return TxnRef;
	}

	public void setTxnRef(String txnRef) {
		TxnRef = txnRef;
	}

	public TxnTransaction getTransaction() {
		return Transaction;
	}

	public void setTransaction(TxnTransaction transaction) {
		Transaction = transaction;
	}


	public String getResponseXml() {
		return ResponseXml;
	}


	public void setResponseXml(String responseXml) {
		ResponseXml = responseXml;
	}
	
	
	
	
}
