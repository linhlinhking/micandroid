package ningbo.media.rest.service;

import java.util.ArrayList;
import java.util.List;

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

import ningbo.media.bean.Event;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.data.api.EventList;
import ningbo.media.data.api.LocationEventList;
import ningbo.media.data.api.UserEventList;
import ningbo.media.data.entity.LocationDetail;
import ningbo.media.data.entity.LocationEventData;
import ningbo.media.data.entity.OneEventData;
import ningbo.media.data.entity.SystemUserData;
import ningbo.media.data.entity.UserEventData;
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

	
	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvent(@FormParam("eventId")
	String eventId, @FormParam("key")
	String key) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.NOINPUT);
				return Response.ok(json.toString()).build();
			}

			Event event = eventService.get(Integer.valueOf(eventId));
			if (event == null) {
				json.put(Constant.CODE, JSONCode.EVENT_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			eventService.delete(event);
			json.put(Constant.CODE, JSONCode.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION);
			return Response.ok(json.toString()).build();
		}
		return Response.ok(json.toString()).build();
	}

	/**
	 * @param params
	 *            POST Object.
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
		String eventId = params.getField("eventId").getValue();
		Event event = null;
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

			if (StringUtils.hasText(eventId)) {
				event = eventService.get(Integer.valueOf(eventId));
			}
			if (event != null) {
				event.setTitle(title);
				event.setSubject(subject);
				event.setStart_date(startDate);
				event.setStart_time(startTime);
				event.setEnd_date(endDate);
				event.setEnd_time(endTime);
				event.setSystemUser(user);
				event.setLocation(location);
				eventService.update(event) ;
				json.put(Constant.CODE, JSONCode.SUCCESS);
				json.put(Constant.EVENTID, event.getId());
			} else {
				event = new Event();
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
				json.put(Constant.EVENTID, ids);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.COMMENT_THROWEXCEPTION);
			return Response.ok(json.toString()).build();
		}
		return Response.ok(json.toString()).build();
	}

	@Path("/get_user/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserEventByUserId(@PathParam("id")
	String id) {
		return Response.ok(getUserEvent(id)).build();
	}

	@Path("/get_location/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationEventByUserId(@PathParam("id")
	String id) {
		return Response.ok(getLocationEvent(id)).build();
	}

	@Path("/get_event/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventById(@PathParam("id")
	String id) {
		return Response.ok(getEvent(id)).build();
	}

	private EventList getEvent(String id) {
		Event e = eventService.get(Integer.valueOf(id));
		if (null == e) {
			return new EventList();
		}
		Location loc = e.getLocation();
		SystemUser u = e.getSystemUser();

		OneEventData data = new OneEventData();
		data.setId(e.getId());
		data.setTitle(e.getTitle());
		data.setSubject(e.getSubject());
		data.setStartDate(e.getStart_date());
		data.setStartTime(e.getStart_time());
		data.setEndDate(e.getEnd_date());
		data.setEndTime(e.getEnd_time());

		SystemUserData uData = new SystemUserData();
		uData.setId(u.getId());
		uData.setUsername(u.getUsername());
		data.setUser(uData);

		LocationDetail detail = new LocationDetail();
		detail.setId(loc.getId());
		detail.setAddress(loc.getAddress_cn());
		detail.setName(loc.getName_cn());
		detail.setLatitude(loc.getLatitude());
		detail.setLongitude(loc.getLongitude());
		data.setLocation(detail);

		return new EventList(data);

	}

	private UserEventList getUserEvent(String id) {
		List<Event> listEvents = eventService.getUserEventsList(Integer
				.valueOf(id));
		if (null == listEvents || listEvents.size() < 0) {
			return new UserEventList();
		}
		List<UserEventData> eventDatas = new ArrayList<UserEventData>();
		for (Event e : listEvents) {
			UserEventData tempData = new UserEventData();
			Location loc = e.getLocation();
			tempData.setId(e.getId());
			tempData.setTitle(e.getTitle());
			tempData.setSubject(e.getSubject());
			tempData.setStartDate(e.getStart_date());
			tempData.setStartTime(e.getStart_time());
			tempData.setEndDate(e.getEnd_date());
			tempData.setEndTime(e.getEnd_time());
			LocationDetail detail = new LocationDetail();
			detail.setId(loc.getId());
			detail.setAddress(loc.getAddress_cn());
			detail.setName(loc.getName_cn());
			detail.setLatitude(loc.getLatitude());
			detail.setLongitude(loc.getLongitude());
			tempData.setLocation(detail);

			eventDatas.add(tempData);
		}
		UserEventList list = new UserEventList(Integer.valueOf(id), eventDatas);
		return list;
	}

	private LocationEventList getLocationEvent(String id) {
		List<Event> listEvents = eventService.getLocationEventsList(Integer
				.valueOf(id));
		if (null == listEvents || listEvents.size() < 0) {
			return new LocationEventList();
		}
		List<LocationEventData> eventDatas = new ArrayList<LocationEventData>();
		for (Event e : listEvents) {
			LocationEventData tempData = new LocationEventData();
			SystemUser u = e.getSystemUser();
			tempData.setId(e.getId());
			tempData.setTitle(e.getTitle());
			tempData.setSubject(e.getSubject());
			tempData.setStartDate(e.getStart_date());
			tempData.setStartTime(e.getStart_time());
			tempData.setEndDate(e.getEnd_date());
			tempData.setEndTime(e.getEnd_time());

			SystemUserData detailUser = new SystemUserData();
			detailUser.setId(u.getId());
			detailUser.setUsername(u.getUsername());
			tempData.setUser(detailUser);

			eventDatas.add(tempData);
		}

		return new LocationEventList(Integer.valueOf(id), eventDatas);
	}
}
