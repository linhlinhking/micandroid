package ningbo.media.data.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.bean.EventDate;
import ningbo.media.rest.WsConstants;

@XmlType(name = "", namespace = WsConstants.NS, propOrder = { "id",
		"startDate", "endDate", "startTime", "endTime", "isRepeat",
		"repeatType", "repeatValue" })
@XmlRootElement
public class EventDateData {

	private Integer id;

	private String startDate;

	private String endDate;

	private String startTime;

	private String endTime;

	private String isRepeat;

	private String repeatType;

	private String repeatValue;
	
	public EventDateData(){
	}
	
	public EventDateData(EventDate d){
		if(null != d){
			this.id = d.getId() ;
			this.startDate = d.getStartDate() ;
			this.startTime = d.getStartTime() ;
			this.endDate = d.getEndDate() ;
			this.endTime = d.getEndTime() ;
			if(d.isRepeat()){
				this.isRepeat = "true" ;
			}else{
				this.isRepeat = "false" ;
			}
			this.repeatType = d.getRepeatType().getValue() ;
			this.repeatValue = d.getRepeatValue() ;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}

	public String getRepeatValue() {
		return repeatValue;
	}

	public void setRepeatValue(String repeatValue) {
		this.repeatValue = repeatValue;
	}

}
