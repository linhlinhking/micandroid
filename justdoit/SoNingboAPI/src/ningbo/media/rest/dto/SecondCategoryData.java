package ningbo.media.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.bean.FirstCategory;
import ningbo.media.rest.WsConstants;

@XmlType(name = "SecondCategory", namespace = WsConstants.NS, propOrder = {
		"id", "name_cn", "name_en", "firstCategory" })
@XmlRootElement
public class SecondCategoryData {

	private Integer id;

	private String name_en;

	private String name_cn;

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

	public FirstCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

}
