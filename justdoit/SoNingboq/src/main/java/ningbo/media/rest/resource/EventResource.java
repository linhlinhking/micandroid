package ningbo.media.rest.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import ningbo.media.bean.enums.EventRequestType;
import ningbo.media.bean.enums.EventType;
import ningbo.media.data.api.EventList;
import ningbo.media.data.api.LocationEventList;
import ningbo.media.data.api.UserEventList;
import ningbo.media.data.entity.EventData;
import ningbo.media.data.entity.LocationDetail;
import ningbo.media.data.entity.LocationEventData;
import ningbo.media.data.entity.UserEventData;
import ningbo.media.rest.dto.SystemUserData;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.DateUtil;
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

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventData> getAllEvent() throws JSONException {
		//List<Event> list = eventService.getAllEventOrderByDate();
		String today_frm = DateUtil.getNow(DateUtil.SHORT_FORMAT_TYPE) ;
		List<Event> list = eventService.getEventsByType(today_frm, EventType.EVENTTODAY) ;
		List<EventData> d = null;
		if (null != list && list.size() > 0) {
			d = getEventsByDateList(list);
		} else {
			d = new ArrayList<EventData>();
		}
		return d;
	}

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
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();

			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
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
			String telephone = form.getField("telephone").getValue();

			SystemUser tempUser = systemUserService
					.get(Constant.MD5_FIELD,userMd5Value);
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
			event.setTelephone(telephone);
			event.setCreateDateTime(new Date());

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
	String address, @FormParam("telephone")
	String telephone, @FormParam("base64Value")
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
					.get(Constant.MD5_FIELD,userMd5Value);
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
			event.setCreateDateTime(new Date());
			event.setTelephone(telephone);

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

	@Path("/edit/base64")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response editEvent(@FormParam("eventMd5Value")
	String md5Value, @FormParam("key")
	String key, @FormParam("user_md5Value")
	String userMd5Value, @FormParam("location_md5Value")
	String locationMd5Value, @FormParam("title")
	String title, @FormParam("subject")
	String subject, @FormParam("startDate")
	String startDate, @FormParam("startTime")
	String startTime, @FormParam("endDate")
	String endDate, @FormParam("endTime")
	String endTime, @FormParam("address")
	String address, @FormParam("telephone")
	String telephone, @FormParam("base64Value")
	String base64Value, @Context
	HttpServletRequest request) throws JSONException {
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

			Event event = eventService.get(Constant.MD5_FIELD, md5Value);
			if (null == event) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			SystemUser tempUser = systemUserService
					.get(Constant.MD5_FIELD,userMd5Value);
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
			event.setTelephone(telephone);
			event.setUpdateDateTime(new Date());

			if ((!"".equals(base64Value)) && (null != base64Value)) {
				String fileName = String.valueOf(System.currentTimeMillis());
				StringBuffer sb = new StringBuffer();
				String tempPath = FileUploadUtil.makeFileDir(null, request,
						true);
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
			}

			eventService.update(event);

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_EDIT_SUCCESS);
			return Response.ok(json.toString()).build();

		} catch (Exception ex) {
			json.put(Constant.CODE, JSONCode.LOCATION_EXCEPTION);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			return Response.ok(json.toString()).build();
		}

	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvent(@FormParam("event_md5_value")
	String eventMd5Value, @FormParam("key")
	String key, @FormParam("user_md5_value")
	String userMd5Value, @Context
	HttpServletRequest request) throws JSONException {
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

			Event event = eventService.getEventByUser(eventMd5Value,
					userMd5Value);
			if (null != event) {
				String uuid = event.getPhoto_path();
				if (null != uuid && !("0".equals(uuid))) {
					FileUploadUtil.delFile(uuid, request);
				}
				eventService.delete(event);
				json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
				json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_DELETE_SUCCESS);
				return Response.ok(json.toString()).build();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
		json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_DELETE_FAIL);
		return Response.ok(json.toString()).build();
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
		LocationEventList lists = new LocationEventList();
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

		Location loc = locationService.get(Constant.MD5_FIELD, md5Value);
		Double lat = loc.getLatitude();
		Double lon = loc.getLongitude();
		String name_cn = loc.getName_cn();
		String name_en = loc.getName_en();

		lists.setLocationMd5(md5Value);
		lists.setName_en(name_en);
		lists.setName_cn(name_cn);
		lists.setLatitude(lat);
		lists.setLongitude(lon);
		lists.setData(temps);

		return Response.ok(lists).build();
	}

	/**
	 * @Description:获取指定日期或者指定日期之后的所有的活动。
	 * @throws JSONException
	 * @author Devon.Ning 
	 * @param nowDate
	 */
	@Path("/date/{requestType}/{date_today}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventsByDate(@PathParam("date_today")
	String nowDate,@PathParam("requestType")String reqType) throws JSONException {
		JSONObject json = new JSONObject();
		Collection<EventData> datas = new ArrayList<EventData>();
		List<Event> events = null ;
		try {
			if ("".equals(nowDate.trim()) || nowDate.length() < 0) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_DATE_NOINPUT);
				return Response.ok().build();
			}
			
			if(EventRequestType.EQ.getValue().equalsIgnoreCase(reqType)){
				events = eventService.getEventsByType(nowDate, EventType.EVENTTODAY) ;
			}else if(EventRequestType.GT.getValue().equalsIgnoreCase(reqType)){
				events = eventService.getEventsByType(nowDate,EventType.EVENTDATE);
			}else{
				events = new ArrayList<Event>() ;
			}

			datas = getEventsByDateList(events);
			if (null == datas) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_NO_DATA);
				return Response.ok(json.toString()).build();
			}

		} catch (Exception ex) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_NO_DATA);
			return Response.ok(json.toString()).build();
		}
		return Response.ok(new EventList(datas)).build();
	}

	private List<EventData> getEventsByDateList(List<Event> events) {
		if (null == events || events.size() < 0) {
			return null;
		}
		List<EventData> datas = new ArrayList<EventData>();
		for (int i = 0, j = events.size(); i < j; i++) {
			Event temp = events.get(i);
			EventData tempEventData = new EventData();
			SystemUserData tmpUser = new SystemUserData();
			LocationDetail detail = new LocationDetail();
			String userMd5 = temp.getUserMd5Value();
			SystemUser u = systemUserService.get(Constant.MD5_FIELD, userMd5);
			String locMd5 = temp.getLocationMd5Value();
			Location loc = locationService.get(Constant.MD5_FIELD, locMd5);
			if(null != loc){
				detail.setLatitude(loc.getLatitude());
				detail.setLongitude(loc.getLongitude());
				detail.setMd5Value(loc.getMd5Value());
				detail.setName_cn(loc.getName_cn());
				detail.setName_en(loc.getName_en());
				tempEventData.setLocation(detail);
			}
			if(null != u){
				tmpUser.setMd5Value(u.getMd5Value());
				tmpUser.setUsername(u.getUsername());
				tempEventData.setUser(tmpUser);
			}
			tempEventData.setTitle(temp.getTitle());
			tempEventData.setSubject(temp.getSubject());
			tempEventData.setMd5Value(temp.getMd5Value());
			tempEventData.setStartDate(temp.getStartDate());
			tempEventData.setStartTime(temp.getStartTime());
			tempEventData.setEndDate(temp.getEndDate());
			tempEventData.setEndTime(temp.getEndTime());
			tempEventData.setTelephone(temp.getTelephone());
			tempEventData.setOrganizer(temp.getOrganizer());
			tempEventData.setPhoto_path(temp.getPhoto_path());
			
			datas.add(tempEventData);
		}

		return datas;
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
			if(null != loc){
				detail.setMd5Value(loc.getMd5Value());
				detail.setLatitude(loc.getLatitude());
				detail.setLongitude(loc.getLongitude());
				detail.setName_cn(loc.getName_cn());
				detail.setName_en(loc.getName_en());
				detail.setAddress_cn(loc.getAddress_cn());
				detail.setAddress_en(loc.getAddress_en());
				detail.setTelephone(loc.getTelephone()) ;
			}

			tmpUserEvent.setLocation(detail);
			tmpUserEvent.setTitle(temp.getTitle());
			tmpUserEvent.setSubject(temp.getSubject());
			tmpUserEvent.setMd5Value(temp.getMd5Value());
			tmpUserEvent.setStartDate(temp.getStartDate());
			tmpUserEvent.setStartTime(temp.getStartTime());
			tmpUserEvent.setTelephone(temp.getTelephone()) ;
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
			if(null != u){
				detail.setUsername(u.getUsername());
				detail.setMd5Value(u.getMd5Value());
				detail.setPhoto_path(u.getPhoto_path());
			}

			tmpUserEvent.setUser(detail);
			tmpUserEvent.setTitle(temp.getTitle());
			tmpUserEvent.setSubject(temp.getSubject());
			tmpUserEvent.setMd5Value(temp.getMd5Value());
			tmpUserEvent.setStartDate(temp.getStartDate());
			tmpUserEvent.setStartTime(temp.getStartTime());
			tmpUserEvent.setTelephone(temp.getTelephone()) ;
			tmpUserEvent.setEndDate(temp.getEndDate());
			tmpUserEvent.setEndTime(temp.getEndTime());
			tmpUserEvent.setOrganizer(temp.getOrganizer());
			tmpUserEvent.setPhoto_path(temp.getPhoto_path());

			datas.add(tmpUserEvent);
		}

		return datas;
	}

}
