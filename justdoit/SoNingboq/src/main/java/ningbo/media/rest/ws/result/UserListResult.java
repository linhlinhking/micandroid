package ningbo.media.rest.ws.result;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;
import ningbo.media.rest.base.WSResult;
import ningbo.media.rest.dto.SystemUserData;

@XmlType(name = "UserListResult", namespace = WsConstants.NS)
public class UserListResult extends WSResult {

	List<SystemUserData> userList;

	public UserListResult() {
	}

	public UserListResult(List<SystemUserData> userList) {
		this.userList = userList;
	}

	
	@XmlElementWrapper(name = "userList")
	@XmlElement(name = "user")
	public List<SystemUserData> getUserList() {
		return userList;
	}
	
}
