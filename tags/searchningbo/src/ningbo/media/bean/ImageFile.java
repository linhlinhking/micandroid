package ningbo.media.bean;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tb_image_file")
@XmlRootElement
public class ImageFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** Upload file hash value */
	private String uuid;

	/** Upload file Name */
	private String fileName;

	/** Upload date */
	private Date uploadTime;
	
	/**  File latitude */
	private Double latitude;

	/**  File longitude */
	private Double longitude;

	/** File create address */
	private String locationName;

	/** File create date */
	private Date createTime;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	private PhotoType photoType;

	
	@OneToOne
	@JoinColumn(name = "infoId")
	private ImageInformation imageInfo;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId")
	private Location locationId;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "favoriteId")
	private Favorite favoriteId;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private SystemUser userId ;

	
	
	public ImageFile() {

	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public ImageInformation getImageInfo() {
		return imageInfo;
	}

	public void setImageInfo(ImageInformation imageInfo) {
		this.imageInfo = imageInfo;
	}

	public PhotoType getPhotoType() {
		return photoType;
	}

	public void setPhotoType(PhotoType photoType) {
		this.photoType = photoType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Location getLocationId() {
		return locationId;
	}

	public void setLocationId(Location locationId) {
		this.locationId = locationId;
	}

	public Favorite getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Favorite favoriteId) {
		this.favoriteId = favoriteId;
	}

	public SystemUser getUserId() {
		return userId;
	}

	public void setUserId(SystemUser userId) {
		this.userId = userId;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}