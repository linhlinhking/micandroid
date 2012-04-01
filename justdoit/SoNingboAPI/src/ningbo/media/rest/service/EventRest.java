package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Event;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.data.api.UserEventList;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.EventService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SystemUserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/event")
@Component
@Scope("request")
public class EventRest {

	@Resource
	private EventService eventService;

	@Resource
	private LocationService locationService;

	@Resource
	private SystemUserService systemUserService;

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllEvent() {
		return eventService.getAll();
	}

	/**
	 * @param params POST Object.
	 * @throws JSONException 
	 */
	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEvent(FormDataMultiPart params) throws JSONException {
		String title = params.getField("eventTitle").getValue();
		String subject = params.getField("eventSubject").getValue();
		String userId = params.getField("userId").getValue();
		String locationId = params.getField("locationId").getValue();
		String startDate = params.getField("startDate").getValue();
		String startTime = params.getField("startTime").getValue();
		String endDate = params.getField("endDate").getValue();
		String endTime = params.getField("endTime").getValue();
		String key = params.getField("key").getValue();
		Event event = new Event();
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			SystemUser user = systemUserService.get(Integer.valueOf(userId));
			if (user == null) {
				json.put(Constant.CODE, JSONCode.USER_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			Location location = locationService
					.get(Integer.valueOf(locationId));
			if (location == null) {
				json.put(Constant.CODE, JSONCode.LOCATION_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			event.setTitle(title);
			event.setSubject(subject);
			event.setStart_date(startDate);
			event.setStart_time(startTime);
			event.setEnd_date(endDate);
			event.setEnd_time(endTime);
			event.setSystemUser(user);
			event.setLocation(location);

			Integer ids = eventService.save(event);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.EVENTID, ids) ;
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.COMMENT_THROWEXCEPTION);
			return Response.ok(json.toString()).build();
		}
		return Response.ok(json.toString()).build();
	}

	
	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserEventByUserId(@PathParam("id")String id){
		
		
		
		return null ;
	}
	
	
	private UserEventList getUserEvent(){
		
		
		return null; 
	}
}
