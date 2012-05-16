package ningbo.media.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7046172227850905451L;

	@SearchableId
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@SearchableProperty(store = Store.YES)
	@Column(updatable = false)
	private Date createDate;

	@SearchableProperty(store = Store.YES)
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}

		final BaseEntity base = (BaseEntity) obj;
		if (null == id) {
			if (base.getId() != null) {
				return false;
			} else if (!(id.equals(base.getId()))) {
				return false;
			}
		}
		return true ;
	}

}
