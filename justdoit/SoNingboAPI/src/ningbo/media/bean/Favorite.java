package ningbo.media.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "favorite")
@XmlRootElement
public class Favorite implements Serializable{

	private static final long serialVersionUID = 5891170734899084091L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private Integer userId ;
	
	private Integer locationId ;
	
	@Column(name = "deviceId")
	private String deviceId ;
	
	public Favorite(){}
	
	
	public Favorite(boolean flag){
		flag  = false ;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


/**
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, Favorite.class);
		return json;
	}

	public static Favorite fromJson(String json) {
		Gson gson = new Gson();
		Favorite fav = gson.fromJson(json,
				Favorite.class);
		return fav;
	}
*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favorite other = (Favorite) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Favorite [id=" + id + ", userId=" + userId + ", locationId="
				+ locationId + ", deviceId=" + deviceId + "]";
	}


	public String toJson(List<Favorite> list){
		JSONObject json = new JSONObject() ;
		try {
			if(list != null && list.size() > 0){
				for(Favorite fav : list){
					json.put("id", fav.getId()) ;
					json.put("userId", fav.getUserId()) ;
					json.put("locationId", fav.locationId) ;
					json.put("deviceId", fav.getDeviceId()) ;
				}
			}
			return json.toString() ;
		} catch (JSONException e) {
			e.printStackTrace();
			return null ;
		}
		
	}
}
