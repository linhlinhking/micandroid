package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "users_temp")
@XmlRootElement
public class TempUser implements Serializable{
	
	private static final long serialVersionUID = 15423913474764901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@Column(name = "device_id")
	private String deviceId;
	
	@Column(name = "temp_key")
	private String tempKey ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTempKey() {
		return tempKey;
	}

	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}

	
	
}
