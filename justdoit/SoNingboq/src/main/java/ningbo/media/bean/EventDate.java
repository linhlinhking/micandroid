package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ningbo.media.bean.enums.RepeatType;

@Entity
@Table(name = "tb_events_date")
public class EventDate implements Serializable {

	private static final long serialVersionUID = -8983729784949974705L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 12)
	private String startDate;

	@Column(length = 12)
	private String endDate;

	@Column(length = 12)
	private String startTime;

	@Column(length = 12)
	private String endTime;
	
	private boolean isRepeat ;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 12,name="repeat_type")
	private RepeatType repeatType ;
	
	private String repeatValue ;
	
	private String frequency ;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private NEvents nEvents;

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

	public NEvents getnEvents() {
		return nEvents;
	}

	public void setnEvents(NEvents nEvents) {
		this.nEvents = nEvents;
	}

	

	public RepeatType getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(RepeatType repeatType) {
		this.repeatType = repeatType;
	}

	public String getRepeatValue() {
		return repeatValue;
	}

	public void setRepeatValue(String repeatValue) {
		this.repeatValue = repeatValue;
	}

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
}
