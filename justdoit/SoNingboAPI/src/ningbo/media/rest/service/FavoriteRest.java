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
import javax.ws.rs.core.Response;
import ningbo.media.bean.Favorite;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.TempUser;
import ningbo.media.data.api.FavoriteList;
import ningbo.media.data.entity.FavoriteData;
import ningbo.media.data.entity.LocationDetail;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
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
	public Response getFavoriteCountByLocationId(@PathParam("locationId")
	String locationId) throws JSONException {
		List<Favorite> list = favoriteService.getList("locationId", Integer
				.valueOf(locationId));
		JSONObject json = new JSONObject();
		if (list == null) {
			json.put(Constant.FAVORITENUMBER, 0);
			return Response.ok(json.toString()).build();
		} else {
			json.put(Constant.FAVORITENUMBER, list.size());
			return Response.ok(json.toString()).build();
		}
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFavorite(@FormParam("locationId")
	String locationId, @FormParam("userId")
	String userId, @FormParam("deviceId")
	String deviceId, @FormParam("key")
	String key) throws JSONException {
		JSONObject json = new JSONObject();
		if (!StringUtils.hasText(key)) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
			return Response.ok(json.toString()).build();
		} else if (!Constant.KEY.equals(key)) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
			return Response.ok(json.toString()).build();
		}

		if ((!StringUtils.hasText(userId)) && (!StringUtils.hasText(deviceId))) {
			json.put(Constant.CODE, JSONCode.FAVORITE_INPUT_INVALID);
			return Response.ok(json.toString()).build();
		}

		if (StringUtils.hasText(userId)) {
			Favorite favorite = favoriteService.findFavoriteById(userId,
					locationId);
			if (null == favorite) {
				json.put(Constant.CODE, JSONCode.FAVORITE_NOEXISTS);
				return Response.ok(json.toString()).build();
			} else {
				favoriteService.delete(favorite);
				json.put(Constant.CODE, JSONCode.SUCCESS);
				return Response.ok(json.toString()).build();
			}
		} else {
			Favorite favorite = favoriteService.findFavoriteByDeviceId(
					deviceId, locationId);
			if (null == favorite) {
				json.put(Constant.CODE, JSONCode.FAVORITE_NOEXISTS);
				return Response.ok(json.toString()).build();
			} else {
				favoriteService.delete(favorite);
				json.put(Constant.CODE, JSONCode.SUCCESS);
				return Response.ok(json.toString()).build();
			}
		}

	}

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserFavorite(@FormParam("userId")
	String userId, @FormParam("locationId")
	String locationId, @FormParam("deviceId")
	String deviceId, @FormParam("key")
	String key) throws JSONException {
		JSONObject tempJson = new JSONObject();
		try {
			TempUser tempUser = null;
			Favorite fav = null;
			if (!StringUtils.hasText(key)) {
				tempJson.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
				return Response.ok(tempJson.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				tempJson.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
				return Response.ok(tempJson.toString()).build();
			}
			if (!StringUtils.hasText(locationId)) {
				tempJson.put(Constant.CODE, JSONCode.FAVORITE_LOCATIONISNULL);
				return Response.ok(tempJson.toString()).build();
			}
			Location loc = locationService.get(Integer.valueOf(locationId));
			if (loc == null) {
				tempJson.put(Constant.CODE, JSONCode.FAVORITE_LOCATIONEXISTS);
				return Response.ok(tempJson.toString()).build();
			}

			if (userId != null && userId.trim().length() > 0) {
				SystemUser systemUser = systemUserService.get(Integer
						.valueOf(userId));
				if (systemUser == null) {
					tempJson.put(Constant.CODE,
							JSONCode.FAVORITE_USERID_NOTEXISTS);
					return Response.ok(tempJson.toString()).build();
				}

				fav = favoriteService.findFavoriteById(userId, locationId);
				if (fav != null) {
					tempJson.put(Constant.CODE, JSONCode.FAVORITE_ISEXISTS);
					return Response.ok(tempJson.toString()).build();
				}

				fav = new Favorite();
				fav.setUserId(Integer.valueOf(userId));
				fav.setLocationId(Integer.valueOf(locationId));
				favoriteService.save(fav);
			} else {// User Is Not Exists.Use Temp User.
				if (!StringUtils.hasText(deviceId)) {
					tempJson.put(Constant.CODE, JSONCode.FAVORITE_GET_SERIAL);
					return Response.ok(tempJson.toString()).build();
				} else {
					fav = favoriteService.findFavoriteByDeviceId(deviceId,
							locationId);
					if (fav != null) {
						tempJson.put(Constant.CODE, JSONCode.FAVORITE_ISEXISTS);
						return Response.ok(tempJson.toString()).build();
					}

					fav = new Favorite();
					fav.setLocationId(Integer.valueOf(locationId));
					
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
			tempJson.put(Constant.CODE, JSONCode.SUCCESS);
			return Response.ok(tempJson.toString()).build();
		} catch (NumberFormatException ex) {
			tempJson.put(Constant.CODE, JSONCode.FAVORITE_INPUT_INVALID);
			return Response.ok(tempJson.toString()).build();
		}

	}

	@Path("/user/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserFavorite(@PathParam("userId")
	String userId) {
		return Response.ok(queryUserFavorites(userId)).build();
	}

	@Path("/device/{deviceId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeviceFavorite(@PathParam("deviceId")
	String deviceId) {
		return Response.ok(queryDeviceFavorites(deviceId)).build();
	}

	
	
	private FavoriteList queryUserFavorites(String userId) {
		List<Favorite> list = favoriteService.getListFavoriteByUserId(Integer
				.valueOf(userId));
		if(null == list || list.size() < 0){
			return new FavoriteList() ;
		}
		List<LocationDetail> locations = new ArrayList<LocationDetail>() ;
		
		for(Favorite f : list){
			LocationDetail detail = new LocationDetail() ;
			Integer locationId = f.getLocationId() ;
			Location tempLocation = null ;
			if(null != locationId){
				tempLocation = locationService.get(locationId) ;
				detail.setId(tempLocation.getId()) ;
				detail.setName(tempLocation.getName_cn()) ;
				detail.setAddress(tempLocation.getAddress_cn()) ;
				detail.setLatitude(tempLocation.getLatitude()) ;
				detail.setLongitude(tempLocation.getLongitude()) ;
				locations.add(detail) ;
			}
		}
		return new FavoriteList(Integer.valueOf(userId),"",locations);
	}
	
	private FavoriteList queryDeviceFavorites(String deviceId) {
		List<Favorite> list = favoriteService.getListFavoriteByDeviceId(deviceId) ;
		if(null == list || list.size() < 0){
			return new FavoriteList() ;
		}
		List<LocationDetail> locations = new ArrayList<LocationDetail>() ;
		
		for(Favorite f : list){
			LocationDetail detail = new LocationDetail() ;
			Integer locationId = f.getLocationId() ;
			Location tempLocation = null ;
			if(null != locationId){
				tempLocation = locationService.get(locationId) ;
				detail.setId(tempLocation.getId()) ;
				detail.setName(tempLocation.getName_cn()) ;
				detail.setAddress(tempLocation.getAddress_cn()) ;
				detail.setLatitude(tempLocation.getLatitude()) ;
				detail.setLongitude(tempLocation.getLongitude()) ;
				locations.add(detail) ;
			}
		}
		return new FavoriteList(Integer.valueOf(-1),deviceId,locations);
	}

	@Path("/location/getAll")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getFavoriteListByUserIdOrDeviceId(@FormParam("key")
	String key, @FormParam("deviceId")
	String deviceId, @FormParam("userId")
	String userId) throws JSONException {
		List<Favorite> list = new ArrayList<Favorite>();
		FavoriteData data = new FavoriteData();
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

			data.setUserId((userId == null) ? "null" : userId);
			data.setDeviceId((deviceId == null) ? "null" : deviceId);
			List<Map<String, Object>> tempList = new LinkedList<Map<String, Object>>();
			for (Favorite f : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", f.getId());
				map.put("locationId", f.getLocationId());
				tempList.add(map);
			}

			data.setData(tempList);

		} catch (NumberFormatException ex) {
			// return JSON.toJSONString("") ;
			json.put("code", "6");
			return json.toString();
		} catch (NullPointerException ex) {
			json.put("code", "7");
			return json.toString();
		}
		return JSON.toJSONString(data);
	}

}
