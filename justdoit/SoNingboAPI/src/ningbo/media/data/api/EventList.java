package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.data.entity.EventData;

@XmlType(propOrder = { "datas" })
@XmlRootElement
public class EventList {

	private Collection<EventData> datas;

	public EventList() {

	}

	public EventList(Collection<EventData> datas) {
		this.datas = datas;

	}

	@XmlElement
	public Collection<EventData> getDatas() {
		return datas;
	}
	
	
}
