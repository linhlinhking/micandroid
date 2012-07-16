package ningbo.media.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_event_category1")
public class EventFirstCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private String name_cn ;
	
	private String name_en ;
	
	private String keywords_cn ;
	
	private String keywords_en ;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "eventFirstCategory")
	private List<EventSecondCategory> eventSecondCategorys;
	
	public EventFirstCategory(){}

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

	public List<EventSecondCategory> getEventSecondCategorys() {
		return eventSecondCategorys;
	}

	public void setEventSecondCategorys(
			List<EventSecondCategory> eventSecondCategorys) {
		this.eventSecondCategorys = eventSecondCategorys;
	}
	
	
}
