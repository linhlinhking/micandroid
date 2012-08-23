package ningbo.media.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;


@XmlType(name = "", namespace = WsConstants.NS, propOrder = {
		"id", "aspect_en", "aspect_cn"})
@XmlRootElement(name = "aspectsData")
public class AspectsCategoryData {

	private Integer id ;
	
	private String aspect_en ;
	
	private String aspect_cn ;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
