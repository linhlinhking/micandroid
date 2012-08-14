package com.soningbo.website.entity.base;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.soningbo.core.entity.BaseEntity;
import com.soningbo.website.entity.FirstCategory;

@MappedSuperclass
public abstract class BaseAspectsCategory extends BaseEntity {

	private static final long serialVersionUID = 7847691477008707321L;
	
	public static String REF = "AspectsCategory" ;
	public static String RESOURCE_ID = "id" ;
	public static String RESOURCE_NAME_EN = "name_en" ;
	public static String RESOURCE_NAME_CN = "name_cn" ;

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
