package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ningbo.media.data.entity.UserCommentData;

@XmlRootElement
public class UserCommentList {

	private Integer userId;

	private Collection<UserCommentData> data;

	
	public UserCommentList(){}
	

	public UserCommentList(Collection<UserCommentData> data,Integer userId){
		this.data = data ;
		this.userId = userId ;
	}
	
	
	@XmlElement(name = "userId")
	public Integer getUserId() {
		return userId;
	}

	@XmlElement(name = "data")
	public Collection<UserCommentData> getData() {
		return data;
	}

}
