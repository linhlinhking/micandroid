package ningbo.media.rest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.data.api.LocationList;
import ningbo.media.data.entity.LocationData;
import ningbo.media.data.entity.LocationDetail;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileUpload;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.service.LocationService;
import ningbo.media.service.SecondCategoryService;
import ningbo.media.util.MD5;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/location")
@Component
@Scope("request")
public class LocationRest {

	@Resource
	private LocationService locationService;

	@Resource
	private SecondCategoryService secondCategoryService;

	@Path("/showAll")
	@GET
	@Produces( { MediaType.APPLICATION_JSON })
	public List<Location> getAllLocations() {
		return locationService.getAll();
	}

	@Path("/show/{id : \\d+}")
	@GET
	@Produces( { MediaType.APPLICATION_JSON })
	public Location getLocationById(@PathParam("id")
	String id) {
		if (id == null) {
			return null;
		}
		return locationService.get(Integer.valueOf(id));
	}

	@Path("/number")
	@GET
	@Produces( { MediaType.APPLICATION_JSON })
	public String getLocationCount() throws JSONException {
		JSONObject json = new JSONObject();
		try {
			String temp = String.valueOf(locationService.getTotalCount());
			json.put(Constant.LOCATIONCOUNT, temp);
		} catch (Exception ex) {
			json.put(Constant.CODE, JSONCode.LOCATION_COUNT_EXCEPTION);
			return json.toString();
		}
		return json.toString();
	}

	@Path("/category/{id : \\d+}")
	@GET
	@Produces( { MediaType.APPLICATION_JSON })
	public List<Location> getAllLocationsBySecondCategory(@PathParam("id")
	String id) {
		if (id == null) {
			return null;
		}
		SecondCategory secondCategory = secondCategoryService.get(Integer
				.valueOf(id));
		if (secondCategory == null) {
			return new ArrayList<Location>();
		}
		List<Location> listLocation = secondCategory.getLocations();
		int size = listLocation.size();
		if (size == 1) {
			listLocation.add(new Location());
		}
		return listLocation;
	}

	/**
	 * This method is judge whether the username and email is exists or not If
	 * exists returns true, and the register can't save the information
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String addLocation(FormDataMultiPart form, @Context
	HttpServletRequest request) throws JSONException {
		String key = form.getField("key").getValue();
		JSONObject json = new JSONObject();
		Location location = new Location();
		// the key is wrong
		if (key.isEmpty()) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
			return json.toString();
		}
		String name_en = form.getField("name_en").getValue();
		String name_cn = form.getField("name_cn").getValue();
		String address_en = form.getField("address_en").getValue();
		String address_cn = form.getField("address_cn").getValue();
		String telephone = form.getField("telephone").getValue();
		String lon = form.getField("longitude").getValue();
		String lat = form.getField("latitude").getValue();
		String name_py = form.getField("name_py").getValue();

		// List<String> listValues = FieldsData.getValue(form
		// .getFields("category_id"));

		FormDataBodyPart part = form.getField("photo_path");
		String fileName = part.getContentDisposition().getFileName();

		String photo_path = null;
		try {
			photo_path = FileUpload.upload(part, fileName, request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		location.setName_cn(name_cn);
		location.setName_en(name_en);
		location.setAddress_cn(address_cn);
		location.setAddress_en(address_en);
		location.setTelephone(telephone);
		location.setPhoto_path(photo_path);
		location.setName_py(name_py);
		if (!lon.isEmpty())
			location.setLongitude(Double.parseDouble(lon));
		if (!lat.isEmpty())
			location.setLatitude(Double.parseDouble(lat));
		// if (null == listValues || listValues.size() < 0) {
		// return json.put(Constant.CODE,
		// JSONCode.LOCATION_CATEGORY2ID_INVALID).toString();
		// }

		try {
			// List<SecondCategory> listSc = new ArrayList<SecondCategory>();
			// for(String ids : listValues){
			// SecondCategory sc =
			// secondCategoryService.get(Integer.valueOf(ids)) ;
			// if(sc == null){
			// return
			// json.put(Constant.CODE,JSONCode.LOCATION_CATEGORY_NOEXISTS).toString();
			// }else{
			// listSc.add(sc) ;
			// }
			// }
			// location.setSecondCategorys(listSc) ;

			Integer locationId = locationService.save(location);
			String md5Value = MD5.calcMD5(String.valueOf(locationId));
			location = locationService.get(locationId);
			location.setMd5Value(md5Value);
			locationService.update(location);

			json.put(Constant.LOCATIONID, locationId);
			return json.put(Constant.CODE, JSONCode.SUCCESS).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.LOCATION_EXCEPTION);
			return json.toString();
		}
	}

	/**
	 * This method is judge whether the username and email is exists or not If
	 * exists returns true, and the register can't save the information
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@Path("/edit")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String editLocation(FormDataMultiPart form, @Context
	HttpServletRequest request) throws JSONException {
		String key = form.getField("key").getValue();
		JSONObject json = new JSONObject();
		boolean b = false;
		// the key is wrong
		if (key.isEmpty()) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
			return json.toString();
		}
		String pk = form.getField("id").getValue();
		if (pk.isEmpty()) {
			return json.put("id", "null").toString();
		}
		Integer id = Integer.parseInt(pk);
		String name_en = form.getField("name_en").getValue();
		String name_cn = form.getField("name_cn").getValue();
		String address_en = form.getField("address_en").getValue();
		String address_cn = form.getField("address_cn").getValue();
		String telephone = form.getField("telephone").getValue();
		String lon = form.getField("longitude").getValue();
		String lat = form.getField("latitude").getValue();
		String cid = form.getField("category_id").getValue();
		// FormDataBodyPart part = form.getField("photo_path");
		// String fileName = part.getContentDisposition().getFileName();
		Location location = locationService.get(id);
		location.setName_cn(name_cn);
		location.setName_en(name_en);
		location.setAddress_cn(address_cn);
		location.setAddress_en(address_en);
		location.setTelephone(telephone);
		if (!lon.isEmpty())
			location.setLongitude(Double.parseDouble(lon));
		if (!lat.isEmpty())
			location.setLatitude(Double.parseDouble(lat));
		if (cid.isEmpty()) {
			return json.put("cid", false).toString();
		}
		SecondCategory sc = new SecondCategory();
		sc.setId(Integer.parseInt(cid));
		// location.setSecondCategory(sc);
		try {
			// location.setPhoto_path(FileUpload.upload(part,
			// fileName,"location_head", request));
			locationService.save(location);
			return json.put("success", !b).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Path("/search/{name}")
	@GET
	@Produces( { MediaType.APPLICATION_JSON })
	public LocationList getLocationByName(@PathParam("name")
	String locationName) {
		List<Location> list = locationService.queryLocationByName(locationName);
		List<LocationData> listData = new ArrayList<LocationData>();
		LocationData d = null;
		for (Location l : list) {
			d = new LocationData();
			d.setId(l.getId());
			d.setName(l.getName_cn());

			listData.add(d);
		}
		return new LocationList(listData);
	}

	@Path("/nearby/{latitude}/{longitude}")
	@GET
	@Produces( { MediaType.APPLICATION_JSON })
	public List<LocationDetail> getNearByLocations(@PathParam("latitude")String latitude,@PathParam("longitude")String longitude){
		List<LocationDetail> list = locationService.queryLoctionsByLat(Double.valueOf(latitude), Double.valueOf(longitude)) ;
		
		return list ;
	}
}
