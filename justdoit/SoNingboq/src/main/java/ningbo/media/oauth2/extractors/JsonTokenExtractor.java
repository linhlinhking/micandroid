package ningbo.media.oauth2.extractors ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ningbo.media.oauth2.exception.OAuthException;
import ningbo.media.oauth2.model.Token;
import ningbo.media.oauth2.utils.Preconditions;


public class JsonTokenExtractor implements AccessTokenExtractor
{
  private Pattern accessTokenPattern = Pattern.compile("\"access_token\":\\s*\"(\\S*?)\"");

  public Token extract(String response)
  {
    Preconditions.checkEmptyString(response, "Cannot extract a token from a null or empty String");
    Matcher matcher = accessTokenPattern.matcher(response);
    if(matcher.find())
    {
      return new Token(matcher.group(1), "", response);
    }
    else
    {
      throw new OAuthException("Cannot extract an acces token. Response was: " + response);
    }
  }

}
