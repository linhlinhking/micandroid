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

@Entity
@Table(name = "aspects_category1")
public class AspectsCategory implements Serializable {

	private static final long serialVersionUID = -51122297065299636L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String aspect_en;

	private String aspect_cn;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category1_id")
	private FirstCategory firstCategory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAspect_en() {
		return aspect_en;
	}

	public void setAspect_en(String aspect_en) {
		this.aspect_en = aspect_en;
	}

	public String getAspect_cn() {
		return aspect_cn;
	}

	public void setAspect_cn(String aspect_cn) {
		this.aspect_cn = aspect_cn;
	}

	public FirstCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

}
