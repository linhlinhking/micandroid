package ningbo.media.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = -7555596229080350840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String marked; //资源标识

	@Column
	private String path; //资源路径

	@Column
	private String name; //资源名称
	
	@Column
	private Boolean isSystem;// 是否为系统内置资源
	
	@Column
	private String description;// 描述
	
	@ManyToMany(mappedBy = "resources", fetch = FetchType.EAGER)
	private Collection<SystemRole> roles ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarked() {
		return marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<SystemRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<SystemRole> roles) {
		this.roles = roles;
	}
	
	
}
