package ningbo.media.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_module_file_ext")
public class ImageInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private Long size;    //                    

	private Double width;

	private Double height;
	
	private Double longitude ;
	
	private Double latitude ;
	
	
	public ImageInformation(){}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Long getSize() {
		return size;
	}


	public void setSize(Long size) {
		this.size = size;
	}


	public Double getWidth() {
		return width;
	}


	public void setWidth(Double width) {
		this.width = width;
	}


	public Double getHeight() {
		return height;
	}


	public void setHeight(Double height) {
		this.height = height;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	
	
}
