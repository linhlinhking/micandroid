package ningbo.media.data.entity;

import java.util.List;
import java.util.Map;


public class FavoriteData implements EntityData{
	
	private String userId ;
	private String deviceId ;
	private List<Map<String,Object>> data ;
	
	public FavoriteData(){}
	
	public FavoriteData(String userId,String deviceId,List<Map<String,Object>> data){
		this.userId = userId ;
		this.deviceId = deviceId ;
		this.data = data ;
		
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	
}
