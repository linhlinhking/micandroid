package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ningbo.media.data.entity.UserEventData;


@XmlType(name="",propOrder={"userId","data"})
@XmlRootElement
public class UserEventList {

	private Integer userId ;
	
	private Collection<UserEventData> data ;
	
	public UserEventList(){}
	
	public UserEventList(Integer userId,Collection<UserEventData> data){
		this.userId = userId ;
		this.data = data ;
	}

	@XmlElement
	public Integer getUserId() {
		return userId;
	}

	
	@XmlElement(name = "events",defaultValue = "null")
	public Collection<UserEventData> getData() {
		return data;
	}
	
	
}
