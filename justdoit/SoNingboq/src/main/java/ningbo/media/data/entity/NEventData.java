package ningbo.media.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.bean.EventCategory;
import ningbo.media.bean.EventDate;
import ningbo.media.bean.Location;
import ningbo.media.bean.NEvents;
import ningbo.media.bean.SystemUser;
import ningbo.media.rest.WsConstants;
import ningbo.media.rest.dto.SystemUserData;

@XmlType(name = "", namespace = WsConstants.NS, propOrder = { "id",
		"md5Value", "title_cn", "subject_cn", "address_cn", "title_en",
		"subject_en", "address_en", "organizer", "telephone", "isApproval",
		"price", "createDateTime", "updateDateTime", "lastUpdater",
		"locationDetail", "systemUserData", "eventCategoryData",
		"eventDateDatas" })
@XmlRootElement
public class NEventData {

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

	private String isApproval;

	private Double price;

	private Date createDateTime;

	private Date updateDateTime;

	private String lastUpdater;

	private LocationDetail locationDetail;

	private SystemUserData systemUserData;

	private EventCategoryData eventCategoryData;

	private List<EventDateData> eventDateDatas;

	public NEventData() {
	}

	public NEventData(NEvents e) {
		Location l = e.getLocation();
		if (null != l) {
			this.locationDetail = new LocationDetail(l);
		}
		SystemUser u = e.getSystemUser();
		if (u != null) {
			this.systemUserData = new SystemUserData(u);
		}
		EventCategory ec = e.getEventCategory();
		if (ec != null) {
			this.eventCategoryData = new EventCategoryData(ec);
		}
		List<EventDate> dates = e.getEventDates();
		if (null != dates && dates.size() > 0) {
			List<EventDateData> dlist = new ArrayList<EventDateData>();
			for (int i = 0, j = dates.size(); i < j; i++) {
				EventDate ed = dates.get(i) ;
				EventDateData edd = new EventDateData(ed);
				dlist.add(edd) ;
			}
			this.setEventDateDatas(dlist) ;
		}
		
		this.title_cn = e.getTitle_cn() ;
		this.title_en = e.getTitle_en() ;
		this.subject_cn = e.getSubject_cn() ;
		this.subject_en = e.getSubject_en() ;
		this.address_cn = e.getAddress_cn() ;
		this.address_en = e.getAddress_en() ;
		this.md5Value = e.getMd5Value() ;
		this.lastUpdater = e.getLastUpdater() ;
		this.organizer = e.getOrganizer() ;
		this.price = e.getPrice() ;
		if(e.isApproval()){
			this.isApproval = "true" ;
		}else{
			this.isApproval = "false" ;
		}

	}

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


	public String getIsApproval() {
		return isApproval;
	}

	public void setIsApproval(String isApproval) {
		this.isApproval = isApproval;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public LocationDetail getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(LocationDetail locationDetail) {
		this.locationDetail = locationDetail;
	}

	public SystemUserData getSystemUserData() {
		return systemUserData;
	}

	public void setSystemUserData(SystemUserData systemUserData) {
		this.systemUserData = systemUserData;
	}

	public EventCategoryData getEventCategoryData() {
		return eventCategoryData;
	}

	public void setEventCategoryData(EventCategoryData eventCategoryData) {
		this.eventCategoryData = eventCategoryData;
	}

	public List<EventDateData> getEventDateDatas() {
		return eventDateDatas;
	}

	public void setEventDateDatas(List<EventDateData> eventDateDatas) {
		this.eventDateDatas = eventDateDatas;
	}

}
