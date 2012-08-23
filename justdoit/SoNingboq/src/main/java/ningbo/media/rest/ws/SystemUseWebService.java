package ningbo.media.rest.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

import ningbo.media.rest.WsConstants;
import ningbo.media.rest.base.IdResult;
import ningbo.media.rest.dto.SystemUserData;
import ningbo.media.rest.ws.result.UserResult;

@WebService(name = "SystemUseWebService",targetNamespace = WsConstants.NS)
public interface SystemUseWebService {

	UserResult getUserDetail(@WebParam(name = "id")Integer id) ;
	
	IdResult createUser(@WebParam(name = "user") SystemUserData user) ;
}
