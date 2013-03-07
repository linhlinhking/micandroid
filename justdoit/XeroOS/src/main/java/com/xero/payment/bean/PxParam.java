package com.xero.payment.bean;

public class PxParam {

	private String pxUrl;

	private String pxUserId;

	private String pxKey;

	private String postUsername;

	private String postPassword;

	private String postUrl;

	public PxParam() {
	}

	public PxParam(String pxUrl, String pxUserId, String pxKey,
			String postUsername, String postPassword, String postUrl) {
		this.pxUrl = pxUrl;
		this.pxUserId = pxUserId;
		this.pxKey = pxKey;
		this.postUsername = postUsername;
		this.postPassword = postPassword;
		this.postUrl = postUrl;
	}

	public String getPxUrl() {
		return pxUrl;
	}

	public void setPxUrl(String pxUrl) {
		this.pxUrl = pxUrl;
	}

	public String getPxUserId() {
		return pxUserId;
	}

	public void setPxUserId(String pxUserId) {
		this.pxUserId = pxUserId;
	}

	public String getPxKey() {
		return pxKey;
	}

	public void setPxKey(String pxKey) {
		this.pxKey = pxKey;
	}

	public String getPostUsername() {
		return postUsername;
	}

	public void setPostUsername(String postUsername) {
		this.postUsername = postUsername;
	}

	public String getPostPassword() {
		return postPassword;
	}

	public void setPostPassword(String postPassword) {
		this.postPassword = postPassword;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

}
