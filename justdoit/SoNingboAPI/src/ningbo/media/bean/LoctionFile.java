package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_files_location")
public class LoctionFile implements Serializable{

	private static final long serialVersionUID = 8723132171044380051L;
	
	private Integer id ;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId")
	private Location location ;
	
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "fileId")
	private ModuleFile moduleFile ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ModuleFile getModuleFile() {
		return moduleFile;
	}

	public void setModuleFile(ModuleFile moduleFile) {
		this.moduleFile = moduleFile;
	}
	
	

}
