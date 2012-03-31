package ningbo.media.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tb_tools")
public class Tools {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "toolId")
	private Integer id ;
	
	private String toolName ;
	
	private String toolKeyWords ;
	
	private String description ;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_tools_systemUser", joinColumns = @JoinColumn(name = "toolId"),inverseJoinColumns=@JoinColumn(name = "userId"))
	private List<SystemUser> systemUsers ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getToolKeyWords() {
		return toolKeyWords;
	}

	public void setToolKeyWords(String toolKeyWords) {
		this.toolKeyWords = toolKeyWords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(List<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}

	
	
}
