package ningbo.media.rest.resource;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Location;
import ningbo.media.bean.LocationEdit;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.service.LocationEditService;
import ningbo.media.service.LocationService;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/locationEdit")
@Component
@Scope("request")
public class LocationEditRest {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private LocationService locationService;

	@Resource
	private LocationEditService locationEditService;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLocationEdit(@FormParam("key") String key,
			@FormParam("fieldName") String fieldName,
			@FormParam("oldValue") String oldValue,
			@FormParam("newValue") String newValue,
			@FormParam("locationMd5") String locationMd5,
			@FormParam("userMd5") String userMd5) throws JSONException {

		JSONObject json = new JSONObject();
		LocationEdit locEdit = new LocationEdit();
		try {
			if (key.isEmpty()) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				return Response.ok(json.toString()).build();
			}
			if (!Constant.KEY.equals(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				return Response.ok(json.toString()).build();
			}

			Location location = locationService.get(Constant.MD5_FIELD,
					locationMd5);
			if (null == location) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			locEdit.setFieldName(fieldName);
			locEdit.setOldValue(oldValue);
			locEdit.setNewValue(newValue);
			locEdit.setIsDeleted(false);
			locEdit.setLocationMd5(locationMd5);
			// now the user'md5 maybe have or not.
			locEdit.setUserMd5(userMd5);
			locEdit.setCreateDateTime(new Date());

			locationEditService.saveOrUpdate(locEdit);
		} catch (Exception ex) {
			logger.error("Save Edit's Location Error.", ex);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			return Response.ok(json.toString()).build();
		}
		json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
		return Response.ok(json.toString()).build();
	}

	@Path("/show/{md5Value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEditInforByLocation(
			@PathParam("md5Value") String md5Value) throws JSONException {
		JSONObject json = new JSONObject();
		Location location = locationService.get(Constant.MD5_FIELD, md5Value);
		if (null == location) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_NOEXISTS);
			return Response.ok(json.toString()).build();
		}
		List<LocationEdit> lists = locationEditService
				.getAllEditInfors(md5Value);

		GenericEntity<List<LocationEdit>> entity = new GenericEntity<List<LocationEdit>>(
				lists) {
		};
		return Response.ok(entity).build();
	}
}
