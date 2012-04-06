package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.data.entity.SystemUserData;

@XmlType(propOrder = {"userId","friends"})
@XmlRootElement
public class FriendList {

	private Integer userId;

	private Collection<SystemUserData> friends;

	public FriendList() {
	}

	public FriendList(Integer userId, Collection<SystemUserData> friends) {
		this.userId = userId;
		this.friends = friends;
	}

	@XmlElement
	public Integer getUserId() {
		return userId;
	}

	@XmlElement
	public Collection<SystemUserData> getFriends() {
		return friends;
	}

}
