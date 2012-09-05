package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Location;
import ningbo.media.bean.LocationExt;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.service.LocationExtService;
import ningbo.media.service.LocationService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/locationExt")
@Component
@Scope("request")
public class LocationExtResource {

	@Resource
	private LocationExtService locationExtService;

	@Resource
	private LocationService locationService;

	@Path("/addOrUpdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLocationExt(
			@FormParam("location_website") String website,
			@FormParam("location_qq") String qq,
			@FormParam("location_email") String email,
			@FormParam("location_msn") String msn,
			@FormParam("locationMd5Value") String locationMd5Value,
			@FormParam("key") String key,
			@FormParam("mon_isClosed") String isMonClosed,
			@FormParam("mon_open") String monOpen,
			@FormParam("mon_close") String monClose,
			@FormParam("tue_isClosed") String isTueClosed,
			@FormParam("tue_open") String tueOpen,
			@FormParam("tue_close") String tueClose,
			@FormParam("wed_isClosed") String isWedClosed,
			@FormParam("wed_open") String wedOpen,
			@FormParam("wed_close") String wedClose,
			@FormParam("thru_isClosed") String isThruClosed,
			@FormParam("thru_open") String thruOpen,
			@FormParam("thru_close") String thruClose,
			@FormParam("fri_isClosed") String isFriClosed,
			@FormParam("fri_open") String friOpen,
			@FormParam("fri_close") String friClose,
			@FormParam("sat_isClosed") String isSatClosed,
			@FormParam("sat_open") String satOpen,
			@FormParam("sat_close") String satClose,
			@FormParam("sun_isClosed") String isSunClosed,
			@FormParam("sun_open") String sunOpen,
			@FormParam("sun_close") String sunClose) throws JSONException {
		JSONObject json = new JSONObject();
		LocationExt extLocation = null;
		try {

			if (key.isEmpty()) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				return Response.ok(json.toString()).build();
			}

			Location loc = locationService.get(Constant.MD5_FIELD,
					locationMd5Value);
			if (null == loc) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			extLocation = locationExtService.get(Constant.LOCATIONEXT_LINK_LOCATION,
					loc.getId());
			
			if(null == extLocation){
				extLocation = new LocationExt();
			}

			extLocation.setWebsite(website);
			extLocation.setEmailAddress(email);
			extLocation.setMsn(msn);
			extLocation.setQq(qq);
			extLocation.setMonStartTime(monOpen);
			extLocation.setMonEndTime(monClose);
			extLocation.setClosedMon(Boolean.valueOf(isMonClosed));
			extLocation.setTueStartTime(tueOpen);
			extLocation.setTueEndTime(tueClose);
			extLocation.setClosedTue(Boolean.valueOf(isTueClosed));
			extLocation.setWedStartTime(wedOpen);
			extLocation.setWedEndTime(wedClose);
			extLocation.setClosedWed(Boolean.valueOf(isWedClosed));
			extLocation.setThurStartTime(thruOpen);
			extLocation.setThurEndTime(thruClose);
			extLocation.setClosedThur(Boolean.valueOf(isThruClosed));
			extLocation.setFriStartTime(friOpen);
			extLocation.setFriEndTime(friClose);
			extLocation.setClosedFri(Boolean.valueOf(isFriClosed));
			extLocation.setSatStartTime(satOpen);
			extLocation.setSatEndTime(satClose);
			extLocation.setClosedSat(Boolean.valueOf(isSatClosed));
			extLocation.setSunStartTime(sunOpen);
			extLocation.setSunEndTime(sunClose);
			extLocation.setClosedSun(Boolean.valueOf(isSunClosed));
			extLocation.setLocation(loc);
			
			locationExtService.saveOrUpdate(extLocation);

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
		return Response.ok(json.toString()).build();

	}
}
