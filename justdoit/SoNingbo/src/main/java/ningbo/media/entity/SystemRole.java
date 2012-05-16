package ningbo.media.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ningbo.media.entity.base.BaseEntity;

@Entity
@Table(name = "tb_system_role")
public class SystemRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7222172481334704012L;

	@Column
	private String marked;//标识

	@Column
	private String name;//角色名称
	
	@Column
	private Boolean isSystem;// 是否为系统内置角色
	
	@Column
	private String description;// 描述

	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Resource> resources;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Collection<SystemUser> systemUsers ;

	public String getMarked() {
		return marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Resource> getResources() {
		return resources;
	}

	public void setResources(Collection<Resource> resources) {
		this.resources = resources;
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

	public Collection<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(Collection<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}
	
	
	
}
