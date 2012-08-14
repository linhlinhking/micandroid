package com.soningbo.website.entity.base;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

import com.soningbo.core.entity.BaseEntity;
import com.soningbo.website.entity.FirstCategory;
import com.soningbo.website.entity.SecondCategory;

@MappedSuperclass
public abstract class BaseSecondCategory extends BaseEntity {

	private static final long serialVersionUID = -806857329955147448L;

	public static String REF = "SecondCategory";
	public static String RESOURCE_ID = "id";
	public static String RESOURCE_NAME_EN = "name_en";
	public static String RESOURCE_NAME_CN = "name_cn";
	public static String RESOURCE_NAME_PY = "name_py";
	public static String RESOURCE_KEYWORDS_CN = "keywords_cn";
	public static String RESOURCE_KEYWORDS_EN = "keywords_en";
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
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category1_id")
	private FirstCategory firstCategory;

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
	

	public FirstCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof SecondCategory))
			return false;
		else {
			SecondCategory o = (SecondCategory) obj;
			if (null == this.getId() || null == o.getId())
				return false;
			else
				return (this.getId().equals(o.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}
}
