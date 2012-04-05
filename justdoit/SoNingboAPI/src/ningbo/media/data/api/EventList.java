package ningbo.media.data.api;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ningbo.media.data.entity.OneEventData;


@XmlRootElement
public class EventList {
	
	private OneEventData data ;
	
	public EventList(){
		
	}
	
	public EventList(OneEventData data){
		this.data = data ;
	}

	@XmlElement(name = "events",defaultValue = "null")
	public OneEventData getData() {
		return data;
	}
	
}
