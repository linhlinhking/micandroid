package ningbo.media.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ningbo.media.rest.WsConstants;

@XmlType(name = "FirstCategory", namespace = WsConstants.NS, propOrder = {
		"id", "name_cn", "name_en", "keywords_en","keywords_cn","description_en","description_cn" })
@XmlRootElement(name = "data")
public class FirstCategoryData {

	private Integer id;

	private String name_en;

	private String name_cn;
	
	private String keywords_en ;
	
	private String keywords_cn ;

	private String description_en ;
	
	private String description_cn ;

	public FirstCategoryData() {
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

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getDescription_cn() {
		return description_cn;
	}

	public void setDescription_cn(String description_cn) {
		this.description_cn = description_cn;
	}



}
