package ningbo.media.data.entity;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="user",propOrder={"id","username"})
public class SystemUserData {
	
	private Integer id ;
	
	private String username ;
	
	public SystemUserData(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}
