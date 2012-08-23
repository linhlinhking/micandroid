package ningbo.media.oauth2.builder;

import ningbo.media.oauth2.model.OAuthConfig;
import ningbo.media.oauth2.services.OAuthService;

public interface Api {

	/**
	 * Creates an {@link OAuthService}
	 * 
	 * @param apiKey
	 *            your application api key
	 * @param apiSecret
	 *            your application api secret
	 * @param callback
	 *            the callback url (or 'oob' for out of band OAuth)
	 * @param scope
	 *            the OAuth scope
	 * 
	 * @return fully configured {@link OAuthService}
	 */
	OAuthService createService(OAuthConfig config);
}
