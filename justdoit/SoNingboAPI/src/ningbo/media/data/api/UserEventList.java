package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ningbo.media.data.entity.EventData;

@XmlRootElement
public class UserEventList {

	private Integer userId ;
	
	private Collection<EventData> data ;
	
	public UserEventList(){}
	
	public UserEventList(Integer userId,Collection<EventData> data){
		this.userId = userId ;
		this.data = data ;
	}

	@XmlElement
	public Integer getUserId() {
		return userId;
	}

	
	@XmlElement(name = "events",defaultValue = "null")
	public Collection<EventData> getData() {
		return data;
	}
	
	
}
