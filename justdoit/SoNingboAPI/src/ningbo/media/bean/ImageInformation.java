package ningbo.media.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_image_information")
public class ImageInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private Long imageSize;    //                    

	private Double imageWidth;

	private Double imageHeight;
	
	
	public ImageInformation(){}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getImageSize() {
		return imageSize;
	}

	public void setImageSize(Long imageSize) {
		this.imageSize = imageSize;
	}

	public Double getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Double imageWidth) {
		this.imageWidth = imageWidth;
	}

	public Double getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Double imageHeight) {
		this.imageHeight = imageHeight;
	}
	
	
	
}
