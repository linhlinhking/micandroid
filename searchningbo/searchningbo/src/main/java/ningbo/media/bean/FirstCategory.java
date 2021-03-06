package ningbo.media.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "category1")
@XmlRootElement
public class FirstCategory implements Serializable {

	private static final long serialVersionUID = -7364749707727750792L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;

	@Expose
	private String name_en;

	@Expose
	private String name_cn;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "firstCategory")
	private List<SecondCategory> secondCategorys;

	public FirstCategory() {
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

	@XmlTransient
	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, FirstCategory.class);
		return json;
	}

	public static FirstCategory fromJson(String jsonCategory) {
		Gson gson = new Gson();
		FirstCategory fCategory = gson.fromJson(jsonCategory,
				FirstCategory.class);
		return fCategory;
	}

	@Override
	public String toString() {
		return "FirstCategory [id=" + id + ", name_en=" + name_en
				+ ", name_cn=" + name_cn + "]";
	}

}
