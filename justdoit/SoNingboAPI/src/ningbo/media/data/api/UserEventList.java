package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ningbo.media.data.entity.UserEventData;


@XmlType(name="",propOrder={"userMd5Value","data"})
@XmlRootElement
public class UserEventList {

	private String userMd5Value ;
	
	private Collection<UserEventData> data ;
	
	public UserEventList(){}
	
	public UserEventList(String userMd5Value,Collection<UserEventData> data){
		this.userMd5Value = userMd5Value ;
		this.data = data ;
	}

	@XmlElement
	public String getUserMd5Value() {
		return userMd5Value;
	}

	
	@XmlElement(name = "events",defaultValue = "null")
	public Collection<UserEventData> getData() {
		return data;
	}
	
	
}
