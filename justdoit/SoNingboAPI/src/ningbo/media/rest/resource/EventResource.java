package ningbo.media.rest.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Event;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.enums.EventType;
import ningbo.media.data.api.LocationEventList;
import ningbo.media.data.api.UserEventList;
import ningbo.media.data.entity.LocationDetail;
import ningbo.media.data.entity.LocationEventData;
import ningbo.media.data.entity.UserEventData;
import ningbo.media.rest.dto.SystemUserData;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileHashCode;
import ningbo.media.rest.util.FileUpload;
import ningbo.media.rest.util.FileUploadUtil;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.service.EventService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SystemUserService;
import ningbo.media.util.Base64Image;
import ningbo.media.util.MD5;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/event")
@Component
@Scope("request")
public class EventResource {

	@Resource
	private EventService eventService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private LocationService locationService;

	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEvent(FormDataMultiPart form, @Context
	HttpServletRequest request) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			String key = form.getField("key").getValue();
			Event event = new Event();
			if (key.isEmpty()) {
				json.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();

			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();
			}

			String userMd5Value = form.getField("user_md5Value").getValue();
			String locationMd5Value = form.getField("location_md5Value")
					.getValue();
			String title = form.getField("title").getValue();
			String subject = form.getField("subject").getValue();
			String startDate = form.getField("startDate").getValue();
			String startTime = form.getField("startTime").getValue();
			String endDate = form.getField("endDate").getValue();
			String endTime = form.getField("endTime").getValue();
			String address = form.getField("address").getValue();

			SystemUser tempUser = systemUserService
					.getSystemUserByMd5Value(userMd5Value);
			if (null == tempUser) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_USER_USER_MD5VALUE);
				return Response.ok(json.toString()).build();
			}
			String organizer = tempUser.getUsername();

			Location tempLocation = locationService.get(Constant.MD5_FIELD,
					locationMd5Value);
			if (null == tempLocation) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_MD5_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			event.setTitle(title);
			event.setSubject(subject);
			event.setStartDate(startDate);
			event.setStartTime(startTime);
			event.setEndDate(endDate);
			event.setEndTime(endTime);
			event.setOrganizer(organizer);
			event.setAddress(address);
			event.setLocationMd5Value(locationMd5Value);
			event.setUserMd5Value(userMd5Value);

			FormDataBodyPart part = form.getField("photo_path");
			String fileName = part.getContentDisposition().getFileName();
			String photo_path = FileUpload.uploadLocation(part, fileName,
					request);
			event.setPhoto_path(photo_path);

			Integer eventId = eventService.save(event);
			String md5Value = MD5.calcMD5(String.valueOf(eventId));
			event.setMd5Value(md5Value);
			eventService.update(event);

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.EVENTID, eventId);
			return Response.ok(json.toString()).build();

		} catch (Exception ex) {
			json.put(Constant.CODE, JSONCode.LOCATION_EXCEPTION);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			return Response.ok(json.toString()).build();
		}

	}

	@Path("/add/base64")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEventBase64(@FormParam("key")
	String key, @FormParam("user_md5Value")
	String userMd5Value, @FormParam("location_md5Value")
	String locationMd5Value, @FormParam("title")
	String title, @FormParam("subject")
	String subject, @FormParam("startDate")
	String startDate, @FormParam("startTime")
	String startTime, @FormParam("endDate")
	String endDate, @FormParam("endTime")
	String endTime, @FormParam("address")
	String address, @FormParam("base64Value")
	String base64Value, @Context
	HttpServletRequest request) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			Event event = new Event();
			if (key.isEmpty()) {
				json.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();

			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();
			}

			SystemUser tempUser = systemUserService
					.getSystemUserByMd5Value(userMd5Value);
			if (null == tempUser) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_USER_USER_MD5VALUE);
				return Response.ok(json.toString()).build();
			}
			String organizer = tempUser.getUsername();

			Location tempLocation = locationService.get(Constant.MD5_FIELD,
					locationMd5Value);
			if (null == tempLocation) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_MD5_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			event.setTitle(title);
			event.setSubject(subject);
			event.setStartDate(startDate);
			event.setStartTime(startTime);
			event.setEndDate(endDate);
			event.setEndTime(endTime);
			event.setOrganizer(organizer);
			event.setAddress(address);
			event.setLocationMd5Value(locationMd5Value);
			event.setUserMd5Value(userMd5Value);

			String fileName = String.valueOf(System.currentTimeMillis());
			StringBuffer sb = new StringBuffer();
			String tempPath = FileUploadUtil.makeFileDir(null, request, true);
			sb.append(tempPath).append(fileName);

			String tempBase64Value = base64Value.replaceAll(" ", "+");

			boolean flag = Base64Image.generateImage(tempBase64Value, sb
					.toString());

			if (!flag) {
				File file = new File(sb.toString());
				file.delete();
				json.put(Constant.CODE, JSONCode.MODULEFILE_BASE64_INVALID);
				return Response.ok(json.toString()).build();
			}

			String photo_path = FileHashCode.writeBase64File(request, sb
					.toString());

			event.setPhoto_path(photo_path);

			Integer eventId = eventService.save(event);
			String md5Value = MD5.calcMD5(String.valueOf(eventId));
			event.setMd5Value(md5Value);
			eventService.update(event);

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.EVENTID, eventId);
			return Response.ok(json.toString()).build();

		} catch (Exception ex) {
			json.put(Constant.CODE, JSONCode.LOCATION_EXCEPTION);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			return Response.ok(json.toString()).build();
		}

	}

	@Path("/edit")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response editEvent() {

		return null;
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvent(@FormParam("event_md5_value")
	String eventMd5Value, @FormParam("key")
	String key,@Context HttpServletRequest request) {
		JSONObject json = new JSONObject();

		try {
			if (key.isEmpty()) {
				json.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();

			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();
			}
			if (null == eventMd5Value) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_MD5_ISNULL);
				return Response.ok(json.toString()).build();
			}

			Event event = eventService.get(Constant.MD5_FIELD, eventMd5Value);
			if (null != event) {
				String uuid = event.getPhoto_path();
				if (null != uuid && !("0".equals(uuid))) {
					FileUploadUtil.delFile(uuid, request);
				}
				eventService.delete(event);
			}

		} catch (Exception ex) {

		}

		return null;
	}

	@Path("/user/{md5Value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventByUser(@PathParam("md5Value")
	String md5Value) throws JSONException {
		JSONObject json = new JSONObject();
		if (null == md5Value) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_USER_MD5VALUE_ISNULL);
			return Response.ok(json.toString()).build();
		}
		List<Event> events = eventService.getEventsByType(md5Value,
				EventType.SYSTEMUSER);

		List<UserEventData> temps = getUserEventDataList(events);
		if (null == temps) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_USER_USER_MD5VALUE);
			return Response.ok(json.toString()).build();
		}

		return Response.ok(new UserEventList(md5Value, temps)).build();
	}

	@Path("/location/{md5Value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventByLocation(@PathParam("md5Value")
	String md5Value) throws JSONException {
		JSONObject json = new JSONObject();
		if (null == md5Value) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_MD5_ISNULL);
			return Response.ok(json.toString()).build();
		}
		List<Event> events = eventService.getEventsByType(md5Value,
				EventType.LOCATION);

		List<LocationEventData> temps = getLocationEventDataList(events);
		if (null == temps) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_MD5_NOEXISTS);
			return Response.ok(json.toString()).build();
		}

		return Response.ok(new LocationEventList(md5Value, temps)).build();
	}

	private List<UserEventData> getUserEventDataList(List<Event> events) {
		if (null == events || events.size() < 0) {
			return null;
		}

		List<UserEventData> datas = new ArrayList<UserEventData>();
		for (int i = 0, j = events.size(); i < j; i++) {
			Event temp = events.get(i);
			UserEventData tmpUserEvent = new UserEventData();
			String locMd5 = temp.getLocationMd5Value();
			Location loc = locationService.get(Constant.MD5_FIELD, locMd5);
			LocationDetail detail = new LocationDetail();
			detail.setMd5Value(loc.getMd5Value());
			detail.setLatitude(loc.getLatitude());
			detail.setLongitude(loc.getLongitude());
			detail.setName_cn(loc.getName_cn());
			detail.setName_en(loc.getName_en());
			detail.setAddress_cn(loc.getAddress_cn());
			detail.setAddress_en(loc.getAddress_en());

			tmpUserEvent.setLocation(detail);
			tmpUserEvent.setTitle(temp.getTitle());
			tmpUserEvent.setSubject(temp.getSubject());
			tmpUserEvent.setMd5Value(temp.getMd5Value());
			tmpUserEvent.setStartDate(temp.getStartDate());
			tmpUserEvent.setStartTime(temp.getStartTime());
			tmpUserEvent.setEndDate(temp.getEndDate());
			tmpUserEvent.setEndTime(temp.getEndTime());
			tmpUserEvent.setOrganizer(temp.getOrganizer());
			tmpUserEvent.setPhoto_path(temp.getPhoto_path());

			datas.add(tmpUserEvent);
		}

		return datas;
	}

	private List<LocationEventData> getLocationEventDataList(List<Event> events) {
		if (null == events || events.size() < 0) {
			return null;
		}

		List<LocationEventData> datas = new ArrayList<LocationEventData>();
		for (int i = 0, j = events.size(); i < j; i++) {
			Event temp = events.get(i);
			LocationEventData tmpUserEvent = new LocationEventData();
			String userMd5 = temp.getUserMd5Value();
			SystemUser u = systemUserService.get(Constant.MD5_FIELD, userMd5);
			SystemUserData detail = new SystemUserData();
			detail.setUsername(u.getUsername());
			detail.setMd5Value(u.getMd5Value());
			detail.setPhoto_path(u.getPhoto_path());

			tmpUserEvent.setUser(detail);
			tmpUserEvent.setTitle(temp.getTitle());
			tmpUserEvent.setSubject(temp.getSubject());
			tmpUserEvent.setMd5Value(temp.getMd5Value());
			tmpUserEvent.setStartDate(temp.getStartDate());
			tmpUserEvent.setStartTime(temp.getStartTime());
			tmpUserEvent.setEndDate(temp.getEndDate());
			tmpUserEvent.setEndTime(temp.getEndTime());
			tmpUserEvent.setOrganizer(temp.getOrganizer());
			tmpUserEvent.setPhoto_path(temp.getPhoto_path());

			datas.add(tmpUserEvent);
		}

		return datas;
	}

}
