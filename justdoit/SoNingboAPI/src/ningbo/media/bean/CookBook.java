package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cookbook")
public class CookBook implements Serializable{

	private static final long serialVersionUID = 7529310502527508886L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private String menuNameCN ;
	
	private String menuNameEN ;
	
	private String description ;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuNameCN() {
		return menuNameCN;
	}

	public void setMenuNameCN(String menuNameCN) {
		this.menuNameCN = menuNameCN;
	}

	public String getMenuNameEN() {
		return menuNameEN;
	}

	public void setMenuNameEN(String menuNameEN) {
		this.menuNameEN = menuNameEN;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
