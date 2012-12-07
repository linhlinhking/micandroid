package com.xero.core.api;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class XeroApi extends DefaultApi10a {
	
	private static final String AUTHORIZE_URL = "https://api.xero.com/oauth/Authorize?oauth_token=%s";
	//oauth_callback  or oauth_token ???
	@Override
	public String getAccessTokenEndpoint() {
		return "https://api.xero.com/oauth/AccessToken";
	}

	@Override
	public String getRequestTokenEndpoint() {
		return "https://api.xero.com/oauth/RequestToken";
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}
}
