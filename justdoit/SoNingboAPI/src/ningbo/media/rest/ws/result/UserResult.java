package ningbo.media.rest.ws.result;

import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;
import ningbo.media.rest.base.WSResult;
import ningbo.media.rest.dto.SystemUserData;

@XmlType(name = "DepartmentResult", namespace = WsConstants.NS)
public class UserResult extends WSResult {

	private SystemUserData systemUser;

	public UserResult() {
	}

	public UserResult(SystemUserData systemUser) {
		this.systemUser = systemUser;
	}

	public SystemUserData getSystemUser() {
		return systemUser;
	}
	
	
}
