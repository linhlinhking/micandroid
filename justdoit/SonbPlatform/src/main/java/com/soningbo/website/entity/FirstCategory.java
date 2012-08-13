package com.soningbo.website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

import com.soningbo.core.entity.BaseEntity;

@Entity
@Table(name = "category1")
public class FirstCategory extends BaseEntity {
	private static final long serialVersionUID = -1852016481221287906L;

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

	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass().getPackage() != obj.getClass().getPackage())
			return false;
		final FirstCategory other = (FirstCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
