package com.soningbo.website.entity.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

import com.soningbo.core.entity.BaseEntity;
import com.soningbo.website.entity.AspectsCategory;
import com.soningbo.website.entity.FirstCategory;
import com.soningbo.website.entity.SecondCategory;

@MappedSuperclass
public abstract class BaseFirstCategory  extends BaseEntity {
	
	private static final long serialVersionUID = -2897129396776795552L;
	
	public static String REF = "FirstCategory" ;
	public static String RESOURCE_ID = "id" ;
	public static String RESOURCE_NAME_EN = "name_en" ;
	public static String RESOURCE_NAME_CN = "name_cn" ;
	public static String RESOURCE_NAME_PY = "name_py" ;
	public static String RESOURCE_KEYWORDS_CN = "keywords_cn" ;
	public static String RESOURCE_KEYWORDS_EN = "keywords_en" ;
	private int hashCode = Integer.MIN_VALUE;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@SearchableProperty(store = Store.YES)
	@Column(length = 64, nullable = false)
	private String name_en;

	@SearchableProperty(store = Store.YES)
	@Column(length = 64, nullable = false)
	private String name_cn;

	@SearchableProperty(store = Store.YES)
	private String name_py;

	@SearchableProperty(store = Store.YES)
	private String keywords_cn;

	@SearchableProperty(store = Store.YES)
	private String keywords_en;

	@SearchableProperty(store = Store.YES)
	private String description_cn;

	@SearchableProperty(store = Store.YES)
	private String description_en;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "firstCategory")
	private List<SecondCategory> secondCategorys ;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "firstCategory")
	private List<AspectsCategory> aspectsCategorys ;

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

	public String getKeywords_cn() {
		return keywords_cn;
	}

	public void setKeywords_cn(String keywords_cn) {
		this.keywords_cn = keywords_cn;
	}

	public String getKeywords_en() {
		return keywords_en;
	}

	public void setKeywords_en(String keywords_en) {
		this.keywords_en = keywords_en;
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
	
	
	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}
	

	public List<AspectsCategory> getAspectsCategorys() {
		return aspectsCategorys;
	}

	public void setAspectsCategorys(List<AspectsCategory> aspectsCategorys) {
		this.aspectsCategorys = aspectsCategorys;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof FirstCategory)) return false;
		else {
			FirstCategory o = (FirstCategory) obj;
			if (null == this.getId() || null == o.getId()) return false;
			else return (this.getId().equals(o.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}
