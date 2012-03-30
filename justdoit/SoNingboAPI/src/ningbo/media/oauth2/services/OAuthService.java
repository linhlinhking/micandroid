package ningbo.media.oauth2.services;

import ningbo.media.oauth2.model.Token;
import ningbo.media.oauth2.model.Verifier;

public interface OAuthService {

	/**
	 * Retrieve the request token.
	 * 
	 * @return request token
	 */
	public Token getRequestToken();

	/**
	 * Retrieve the access token
	 * 
	 * @param requestToken
	 *            request token (obtained previously)
	 * @param verifier
	 *            verifier code
	 * @return access token
	 */
	public Token getAccessToken(Token requestToken, Verifier verifier);

	/**
	 * Returns the OAuth version of the service.
	 * 
	 * @return version as string
	 */
	public String getVersion();

	
	/**
	 * Returns the URL where you should redirect your users to authenticate your
	 * application.
	 * 
	 * @param requestToken
	 *            the request token you need to authorize
	 * @return the URL where you should redirect your users
	 */
	public String getAuthorizationUrl(Token requestToken);
}
