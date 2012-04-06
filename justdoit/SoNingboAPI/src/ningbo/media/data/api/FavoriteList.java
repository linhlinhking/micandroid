package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.data.entity.LocationDetail;

@XmlType(propOrder = {"userId","deviceId","loctions"})
@XmlRootElement
public class FavoriteList {

	private Integer userId;

	private String deviceId;

	private Collection<LocationDetail> loctions;

	public FavoriteList() {
	}

	public FavoriteList(Integer userId, String deviceId,
			Collection<LocationDetail> locations) {
		this.userId = userId;
		this.deviceId = deviceId;
		this.loctions = locations;

	}

	@XmlElement
	public Integer getUserId() {
		return userId;
	}

	@XmlElement(defaultValue = "")
	public String getDeviceId() {
		return deviceId;
	}

	@XmlElement
	public Collection<LocationDetail> getLoctions() {
		return loctions;
	}

}
