package ningbo.media.data.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.bean.EventCategory;
import ningbo.media.rest.WsConstants;

@XmlType(name = "", namespace = WsConstants.NS, propOrder = { "id", "name_cn",
		"name_en", "keywords_cn", "keywords_en" })
@XmlRootElement
public class EventCategoryData {

	private Integer id;

	private String name_cn;

	private String name_en;

	private String keywords_cn;

	private String keywords_en;
	
	public EventCategoryData(){}
	
	public EventCategoryData(EventCategory ec){
		if(null != ec){
			this.id = ec.getId() ;
			this.name_cn = ec.getName_cn() ;
			this.name_en = ec.getName_en() ;
			this.keywords_cn = ec.getKeywords_cn() ;
			this.keywords_en = ec.getKeywords_en() ;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
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

}
