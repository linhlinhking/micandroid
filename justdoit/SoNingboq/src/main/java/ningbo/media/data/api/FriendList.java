package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.dto.SystemUserData;

@XmlType(propOrder = {"userId","friends"})
@XmlRootElement
public class FriendList {

	private String userId;

	private Collection<SystemUserData> friends;

	public FriendList() {
	}

	public FriendList(String userId, Collection<SystemUserData> friends) {
		this.userId = userId;
		this.friends = friends;
	}

	@XmlElement
	public String getUserId() {
		return userId;
	}

	@XmlElement
	public Collection<SystemUserData> getFriends() {
		return friends;
	}

}
