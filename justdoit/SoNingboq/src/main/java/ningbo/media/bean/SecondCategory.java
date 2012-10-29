package ningbo.media.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "category2")
@XmlRootElement
public class SecondCategory implements Serializable {

	private static final long serialVersionUID = 1831733122782637063L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name_en;

	private String name_cn;
	
	private String name_py ;
	
	private String keywords_en ;
	
	private String keywords_cn ;
	
	@JsonIgnore
	private String description_cn ;
	
	@JsonIgnore
	private String description_en ;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category1_id")
	private FirstCategory firstCategory;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "secondCategorys")
	private List<Location> locations;
	
	

	public SecondCategory() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}
	
	

	public String getName_py() {
		return name_py;
	}

	public void setName_py(String name_py) {
		this.name_py = name_py;
	}

	public String getKeywords_en() {
		return keywords_en;
	}

	public void setKeywords_en(String keywords_en) {
		this.keywords_en = keywords_en;
	}

	public String getKeywords_cn() {
		return keywords_cn;
	}

	public void setKeywords_cn(String keywords_cn) {
		this.keywords_cn = keywords_cn;
	}

	public String getDescription_cn() {
		return description_cn;
	}

	public void setDescription_cn(String description_cn) {
		this.description_cn = description_cn;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	@XmlTransient
	public FirstCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

	
	@XmlTransient
	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	

}
