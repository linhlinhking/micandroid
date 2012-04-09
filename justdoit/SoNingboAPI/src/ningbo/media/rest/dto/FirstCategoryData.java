package ningbo.media.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.bean.SecondCategory;
import ningbo.media.rest.WsConstants;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.collect.Lists;

@XmlType(name = "FirstCategory", namespace = WsConstants.NS, propOrder = {
		"id", "name_cn", "name_en", "description", "secondCategorys" })
@XmlRootElement
public class FirstCategoryData {

	private Integer id;

	private String name_en;

	private String name_cn;

	private String description;

	private List<SecondCategory> secondCategorys = Lists.newArrayList();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	@XmlElement(name = "secondCategorys")
	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}
}
