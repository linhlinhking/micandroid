package ningbo.media.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_location_product")
public class LocationProduct extends BaseEntity{

	private static final long serialVersionUID = -8842968121032239531L;
	
	@Column(name="name_cn")
	private String nameCn ;
	
	@Column(name="name_en")
	private String nameEn ;
	
	@Column(name="description_cn")
	private String descriptionCn ;
	
	@Column(name="description_en")
	private String descriptionEn ;
	
	@Column(name="tags_cn")
	private String tagsCn ;
	
	@Column(name="tags_en")
	private String tagsEn ;
	
	private Double price ;
	
	private String photo_path ;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId")
	private Location location ;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "locationProduct")
	private List<ModuleFile> moduleFiles ;
	

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getDescriptionCn() {
		return descriptionCn;
	}

	public void setDescriptionCn(String descriptionCn) {
		this.descriptionCn = descriptionCn;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getTagsCn() {
		return tagsCn;
	}

	public void setTagsCn(String tagsCn) {
		this.tagsCn = tagsCn;
	}

	public String getTagsEn() {
		return tagsEn;
	}

	public void setTagsEn(String tagsEn) {
		this.tagsEn = tagsEn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<ModuleFile> getModuleFiles() {
		return moduleFiles;
	}

	public void setModuleFiles(List<ModuleFile> moduleFiles) {
		this.moduleFiles = moduleFiles;
	}
	
	
}
