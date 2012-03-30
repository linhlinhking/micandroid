package ningbo.media.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ningbo.media.bean.Favorite;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.TempUser;
import ningbo.media.data.entity.FavoriteData;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.FavoriteService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SystemUserService;
import ningbo.media.service.TempUserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Path("/favorite")
@Component
@Scope("request")
public class FavoriteRest {

	@Resource
	private FavoriteService favoriteService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private LocationService locationService;

	@Resource
	private TempUserService tempUserService;

	@Path("/location/count/{locationId : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFavoriteCountByLocationId(
			@PathParam("locationId") String locationId) {
		List<Favorite> list = favoriteService.getList("locationId",
				Integer.valueOf(locationId));
		if (list == null) {
			return String.valueOf(0);
		} else {
			return String.valueOf(list.size());
		}
	}

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String addUserFavorite(@FormParam("userId") String userId,
			@FormParam("locationId") String locationId,
			@FormParam("deviceId") String deviceId, @FormParam("key") String key)
			throws JSONException {
		JSONObject tempJson = new JSONObject();
		try {
			TempUser tempUser = null;
			Favorite fav = null;
			if ("".equals(key) || key == null) {
				tempJson.put("code", "1");
				//tempJson.put("msg", "Key is NULL!");
				return tempJson.toString();
			} else if (!Constant.KEY.equals(key)) {
				tempJson.put("code", "2");
				//tempJson.put("msg", "Key Invalid！");
				return tempJson.toString();
			}
			if ("".equals(locationId) || locationId == null) {
				tempJson.put("code", "3");
				//tempJson.put("msg", "LocationId is NULL");
				return tempJson.toString();
			}
			Location loc = locationService.get(Integer.valueOf(locationId));
			if (loc == null) {
				tempJson.put("code", "4");
				//tempJson.put("msg", "LocationId is not exists.");
				return tempJson.toString();
			}

			if (userId != null && userId.trim().length() > 0) {// User Is
																// Exists.
				SystemUser systemUser = systemUserService.get(Integer
						.valueOf(userId));
				if (systemUser == null) {
					tempJson.put("code", "5");
					//tempJson.put("msg", "UserId is not exists.");
					return tempJson.toString();
				}

				fav = favoriteService.findFavoriteById(userId, locationId);
				if (fav != null) {
					tempJson.put("code", "6");
					//tempJson.put("msg", "Already Exists！");
					return tempJson.toString();
				}

				fav = new Favorite();
				fav.setUserId(Integer.valueOf(userId));
				fav.setLocationId(Integer.valueOf(locationId));
				favoriteService.save(fav);
			} else {// User Is Not Exists.Use Temp User.
				if ("".equals(deviceId) || deviceId == null) {
					tempJson.put("code", "7");
					//tempJson.put("msg",
					//		"Please get the serial number of the current equipment");
					return tempJson.toString();
				} else {
					fav = favoriteService.findFavoriteByDeviceId(deviceId,
							locationId);
					if (fav != null) {
						tempJson.put("code", "6");
						//tempJson.put("msg", "You have collected！");
						return tempJson.toString();
					}

					fav = new Favorite();
					fav.setLocationId(Integer.valueOf(locationId));
					fav.setDeviceId(deviceId);
					favoriteService.save(fav);

					boolean temp = tempUserService.isExistsDeviceId(deviceId);
					if (!temp) {
						tempUser = new TempUser();
						tempUser.setDeviceId(deviceId);
						tempUser.setTempKey(Constant.KEY);
						tempUserService.save(tempUser);
					}
				}
			}
			tempJson.put("code", "0");
			//tempJson.put("msg", "SUCCESS");
			return tempJson.toString();
		} catch (NumberFormatException ex) {
			tempJson.put("code", "8");
			//tempJson.put("msg", "Input Invaid!");
			return tempJson.toString();
		}

	}

	
	@Path("/location/getAll")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getFavoriteListByUserIdOrDeviceId(
			@FormParam("key") String key,
			@FormParam("deviceId") String deviceId,
			@FormParam("userId") String userId) throws JSONException {
		List<Favorite> list = new ArrayList<Favorite>();
		FavoriteData data = new FavoriteData() ;
		JSONObject json = new JSONObject();
		try {
			if ("".equals(key) || key == null) {
				json.put("code", "1");
				return json.toString();
			} else if (!Constant.KEY.equals(key)) {
				json.put("code", "2");
				return json.toString();
			}

			if (("".equals(deviceId) || deviceId == null)
					&& ("".equals(userId) || userId == null)) {
				json.put("code", "3");
				return json.toString();
			} else if ((deviceId != null && deviceId.trim().length() > 0)
					&& ("".equals(userId) || userId == null)) {
				list = favoriteService.getListFavoriteByDeviceId(deviceId);
				if (list == null || list.size() < 1) {
					json.put("code", "4");
					return json.toString();
				}
			} else {
				list = favoriteService.getListFavoriteByUserId(Integer
						.valueOf(userId));
				if (list == null || list.size() < 1) {
					json.put("code", "5");
					return json.toString();
				}

			}
			
			
			data.setUserId((userId == null) ? "null" : userId) ;
			data.setDeviceId((deviceId == null) ? "null" : deviceId) ;
			List<Map<String,Object>> tempList = new LinkedList<Map<String,Object>>() ;
			for(Favorite f : list){
				Map<String,Object> map = new HashMap<String,Object>() ;
				map.put("id", f.getId()) ;
				map.put("locationId", f.getLocationId()) ;
				tempList.add(map); 
			}
			
			data.setData(tempList) ;

		} catch (NumberFormatException ex) {
			//return JSON.toJSONString("") ;
			json.put("code", "6");
			return json.toString();
		} catch (NullPointerException ex) {
			json.put("code", "7");
			return json.toString();
		}

		//json.put("code", "0");
		//json.put("data", JSON.toJSONString(list));
		
		//return null ;
		return JSON.toJSONString(data) ;
	}
	
	
}
