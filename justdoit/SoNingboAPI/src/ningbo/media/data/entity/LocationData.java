package ningbo.media.data.entity;

public class LocationData implements EntityData {

	private Integer id;

	private String name_cn;

	private String name_en;
	
	private String md5Value ;
	
	private String tags_en ;
	
	private String tags_cn ;
	
	

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

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
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
	
	

}
