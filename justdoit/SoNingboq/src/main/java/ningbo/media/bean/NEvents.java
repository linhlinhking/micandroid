package ningbo.media.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "tb_events")
public class NEvents implements Serializable {

	private static final long serialVersionUID = -8691692488456362767L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String md5Value;

	private String title_cn;

	private String subject_cn;

	private String address_cn;

	private String title_en;

	private String subject_en;

	private String address_en;

	private String telephone;

	private String organizer;
	
	@Column(name="event_icon")
	private String eventIcon ;
	
	@Column(name="poster_image")
	private String posterImage ;

	private boolean isApproval;

	private Double price;

	private Date createDateTime;

	private Date updateDateTime;

	@Column(name = "lastUpdater", length = 128)
	private String lastUpdater;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private SystemUser systemUser;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private EventCategory eventCategory;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "nEvents")
	private List<EventDate> eventDates;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getTitle_cn() {
		return title_cn;
	}

	public void setTitle_cn(String title_cn) {
		this.title_cn = title_cn;
	}

	public String getSubject_cn() {
		return subject_cn;
	}

	public void setSubject_cn(String subject_cn) {
		this.subject_cn = subject_cn;
	}

	public String getAddress_cn() {
		return address_cn;
	}

	public void setAddress_cn(String address_cn) {
		this.address_cn = address_cn;
	}

	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

	public String getSubject_en() {
		return subject_en;
	}

	public void setSubject_en(String subject_en) {
		this.subject_en = subject_en;
	}

	public String getAddress_en() {
		return address_en;
	}

	public void setAddress_en(String address_en) {
		this.address_en = address_en;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}

	public List<EventDate> getEventDates() {
		return eventDates;
	}

	public void setEventDates(List<EventDate> eventDates) {
		this.eventDates = eventDates;
	}

	public boolean isApproval() {
		return isApproval;
	}

	public void setApproval(boolean isApproval) {
		this.isApproval = isApproval;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public String getEventIcon() {
		return eventIcon;
	}

	public void setEventIcon(String eventIcon) {
		this.eventIcon = eventIcon;
	}

	public String getPosterImage() {
		return posterImage;
	}

	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	
	

}
