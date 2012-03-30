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

@Entity
@Table(name = "location_images")
@XmlRootElement
public class LocationImages implements Serializable {

	private static final long serialVersionUID = 8784589002443197429L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String ori_image;

	private String zoom_image;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;
	
	public LocationImages() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOri_image() {
		return ori_image;
	}

	public void setOri_image(String ori_image) {
		this.ori_image = ori_image;
	}

	public String getZoom_image() {
		return zoom_image;
	}

	public void setZoom_image(String zoom_image) {
		this.zoom_image = zoom_image;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
