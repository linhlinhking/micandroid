package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "events")
@XmlRootElement
public class Event implements Serializable{
	
	private static final long serialVersionUID = -2772155327178236095L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id ;
	
	private String subject ; //(theme of the event)
	
	private String eventDate ; //(date of the event)
	
	private String eventTime ; //(time of the event)
	
	private Integer userId ;  //(organizer of the event)
	
	private Integer isPublic ;//(0:private no 0 public)
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId")
	private Location location ; //(address of the event)
	
	private String invitedIds ;//(Invited friend )
	
	
	
	
	public Event(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@XmlTransient
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getInvitedIds() {
		return invitedIds;
	}

	public void setInvitedIds(String invitedIds) {
		this.invitedIds = invitedIds;
	}
	

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", subject=" + subject + ", eventDate="
				+ eventDate + ", eventTime=" + eventTime + "]";
	}

	
}
