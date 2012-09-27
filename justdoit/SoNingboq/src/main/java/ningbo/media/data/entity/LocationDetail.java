package ningbo.media.data.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.rest.dto.AspectsCategoryData;
import ningbo.media.rest.dto.FirstCategoryData;

@XmlType(name = "locationBean", propOrder = { "locationId", "md5Value",
		"name_cn", "name_en", "address_cn", "photo_path", "address_en",
		"latitude", "longitude", "name_py", "tags_cn", "tags_en", "telephone",
		"category2_id", "isAppoval", "phoneNumberOne", "phoneNumberTwo",
		"phoneNumberThree", "description_cn", "description_en",
		"firstCategoryNameEn", "firstCategoryNameCn", "secondCategoryNameEn",
		"secondCategoryNameCn", "firstCategoryData", "aspectsDatas" })
@XmlRootElement(name = "data")
public class LocationDetail {

	private Integer locationId;

	private String md5Value;

	private String name_cn;

	private String name_en;

	private String address_cn;

	private String address_en;

	private Double latitude;

	private Double longitude;

	private String name_py;

	private String tags_en;

	private String tags_cn;

	private String telephone;

	private String photo_path;

	private String category2_id;

	private String isAppoval;

	private String phoneNumberOne;

	private String phoneNumberTwo;

	private String phoneNumberThree;

	private String description_cn;

	private String description_en;

	private String firstCategoryNameEn;

	private String firstCategoryNameCn;

	private String secondCategoryNameEn;

	private String secondCategoryNameCn;

	private FirstCategoryData firstCategoryData;

	private List<AspectsCategoryData> aspectsDatas;

	public LocationDetail() {
	}

	public LocationDetail(Location l) {
		new LocationDetail(l, false);
	}

	public LocationDetail(Location l, boolean flag) {
		if (null != l) {
			if (flag) {
				this.address_cn = l.getAddress_cn();
				this.address_en = l.getAddress_en();
				this.name_cn = l.getName_cn();
				this.name_en = l.getName_en();
				this.latitude = l.getLatitude();
				this.longitude = l.getLongitude();
				this.md5Value = l.getMd5Value();
				this.telephone = l.getTelephone();
				this.photo_path = l.getPhoto_path();
				this.name_py = l.getName_py();
				this.tags_cn = l.getTags_cn();
				this.tags_en = l.getTags_en();
				this.isAppoval = String.valueOf(l.isAppoval());
				
				List<SecondCategory> scList = l.getSecondCategorys();
				SecondCategory sc = null;
				if (null != scList && scList.size() > 0) {
					sc = scList.get(0);
					if (null != sc) {
						this.secondCategoryNameCn = sc.getName_cn();
						this.secondCategoryNameEn = sc.getName_en();

						FirstCategory fc = sc.getFirstCategory();
						if (null != fc) {
							this.firstCategoryNameCn = fc.getName_cn();
							this.firstCategoryNameEn = fc.getName_en();
						}
					}
				}

			} else {
				this.address_cn = l.getAddress_cn();
				this.address_en = l.getAddress_en();
				this.name_cn = l.getName_cn();
				this.name_en = l.getName_en();
				this.latitude = l.getLatitude();
				this.longitude = l.getLongitude();
				this.md5Value = l.getMd5Value();
			}
		}
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
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

	public String getAddress_cn() {
		return address_cn;
	}

	public void setAddress_cn(String address_cn) {
		this.address_cn = address_cn;
	}

	public String getAddress_en() {
		return address_en;
	}

	public void setAddress_en(String address_en) {
		this.address_en = address_en;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName_py() {
		return name_py;
	}

	public void setName_py(String name_py) {
		this.name_py = name_py;
	}

	public String getTags_en() {
		return tags_en;
	}

	public void setTags_en(String tags_en) {
		this.tags_en = tags_en;
	}

	public String getTags_cn() {
		return tags_cn;
	}

	public void setTags_cn(String tags_cn) {
		this.tags_cn = tags_cn;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCategory2_id() {
		return category2_id;
	}

	public void setCategory2_id(String category2_id) {
		this.category2_id = category2_id;
	}

	public FirstCategoryData getFirstCategoryData() {
		return firstCategoryData;
	}

	public void setFirstCategoryData(FirstCategoryData firstCategoryData) {
		this.firstCategoryData = firstCategoryData;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public List<AspectsCategoryData> getAspectsDatas() {
		return aspectsDatas;
	}

	public void setAspectsDatas(List<AspectsCategoryData> aspectsDatas) {
		this.aspectsDatas = aspectsDatas;
	}

	public String getIsAppoval() {
		return isAppoval;
	}

	public void setIsAppoval(String isAppoval) {
		this.isAppoval = isAppoval;
	}

	public String getPhoneNumberOne() {
		return phoneNumberOne;
	}

	public void setPhoneNumberOne(String phoneNumberOne) {
		this.phoneNumberOne = phoneNumberOne;
	}

	public String getPhoneNumberTwo() {
		return phoneNumberTwo;
	}

	public void setPhoneNumberTwo(String phoneNumberTwo) {
		this.phoneNumberTwo = phoneNumberTwo;
	}

	public String getPhoneNumberThree() {
		return phoneNumberThree;
	}

	public void setPhoneNumberThree(String phoneNumberThree) {
		this.phoneNumberThree = phoneNumberThree;
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

	public String getFirstCategoryNameEn() {
		return firstCategoryNameEn;
	}

	public void setFirstCategoryNameEn(String firstCategoryNameEn) {
		this.firstCategoryNameEn = firstCategoryNameEn;
	}

	public String getFirstCategoryNameCn() {
		return firstCategoryNameCn;
	}

	public void setFirstCategoryNameCn(String firstCategoryNameCn) {
		this.firstCategoryNameCn = firstCategoryNameCn;
	}

	public String getSecondCategoryNameEn() {
		return secondCategoryNameEn;
	}

	public void setSecondCategoryNameEn(String secondCategoryNameEn) {
		this.secondCategoryNameEn = secondCategoryNameEn;
	}

	public String getSecondCategoryNameCn() {
		return secondCategoryNameCn;
	}

	public void setSecondCategoryNameCn(String secondCategoryNameCn) {
		this.secondCategoryNameCn = secondCategoryNameCn;
	}

}
