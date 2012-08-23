package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ningbo.media.data.entity.UserCommentData;

@XmlRootElement
public class UserCommentList {

	private String userId;

	private Collection<UserCommentData> data;

	
	public UserCommentList(){}
	

	public UserCommentList(Collection<UserCommentData> data,String userId){
		this.data = data ;
		this.userId = userId ;
	}
	
	
	@XmlElement(name = "userId")
	public String getUserId() {
		return userId;
	}

	@XmlElement(name = "data")
	public Collection<UserCommentData> getData() {
		return data;
	}

}
