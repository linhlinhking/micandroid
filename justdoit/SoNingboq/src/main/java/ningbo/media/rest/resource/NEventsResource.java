package ningbo.media.rest.resource;

import java.util.ArrayList;
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

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.EventCategory;
import ningbo.media.bean.EventDate;
import ningbo.media.bean.Location;
import ningbo.media.bean.NEvents;
import ningbo.media.bean.SystemUser;
import ningbo.media.data.entity.NEventData;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.EventCategoryService;
import ningbo.media.service.EventDateService;
import ningbo.media.service.LocationService;
import ningbo.media.service.NEventsService;
import ningbo.media.service.SystemUserService;
import ningbo.media.util.MD5;
import ningbo.media.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/nEvent")
@Component
@Scope("request")
public class NEventsResource {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private NEventsService nEventsService;

	@Resource
	private EventCategoryService eventCategoryService;

	@Resource
	private LocationService locationService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private EventDateService eventDateService;

	@Path("/addOrUpdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEvent(@FormParam("key") String key,
			@FormParam("location_md5Value") String locationMd5Value,
			@FormParam("user_md5Value") String userMd5Value,
			@FormParam("categoryId") String category_id,
			@FormParam("title_cn") String title,
			@FormParam("title_en") String title_en,
			@FormParam("subject_cn") String subject,
			@FormParam("subject_en") String subject_en,
			@FormParam("telephone") String telephone,
			@FormParam("address_cn") String address,
			@FormParam("address_en") String address_en,
			@FormParam("startDate") String startDate,
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime,
			@FormParam("endDate") String endDate,
			@FormParam("organizer") String organizer,
			@FormParam("isRepeat") String isRepeat,
			@FormParam("repeat_type") String repeatType,
			@FormParam("price") String price,
			@FormParam("days_value") String daysValue,
			@FormParam("event_md5Value") String eventMd5Value)
			throws JSONException {
		JSONObject json = new JSONObject();
		NEvents event = null;
		try {
			if (key.isEmpty()) {
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();

			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();
			}

			SystemUser tempUser = systemUserService.get(Constant.MD5_FIELD,
					userMd5Value);
			if (null == tempUser) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_USER_USER_MD5VALUE);
				return Response.ok(json.toString()).build();
			}

			Location tempLocation = locationService.get(Constant.MD5_FIELD,
					locationMd5Value);
			if (null == tempLocation) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_MD5_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			if (StringUtils.hasText(eventMd5Value)) {
				event = nEventsService.get(Constant.MD5_FIELD, eventMd5Value);
				event.setUpdateDateTime(new Date());
				String updaterUser = tempUser.getUsername();
				event.setLastUpdater(updaterUser);
			} else {
				event = new NEvents();
				event.setCreateDateTime(new Date());
				event.setApproval(false);
			}

			event.setSystemUser(tempUser);
			event.setOrganizer(organizer);
			event.setLocation(tempLocation);

			if (category_id != null) {
				EventCategory eventCategory = eventCategoryService.get(Integer
						.valueOf(category_id));
				if (null == eventCategory) {
					json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
					json.put(Constant.MESSAGE,
							JSONCode.MSG_EVENT_CATEGORY_NOEXISTS);
					return Response.ok(json.toString()).build();
				}
				event.setEventCategory(eventCategory);
			} else {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_EVENT_CATEGORY_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			if (StringUtil.isNumeric(price)) {
				event.setPrice(Double.valueOf(price));
			} else {
				event.setPrice(0.0);
			}

			event.setTitle_cn(title);
			event.setSubject_cn(subject);
			event.setTelephone(telephone);
			event.setAddress_cn(address);
			event.setTitle_en(title_en);
			event.setSubject_en(subject_en);
			event.setAddress_en(address_en);

			// FormDataBodyPart part = form.getField("photo_path");
			// String fileName = part.getContentDisposition().getFileName();
			// String photo_path = FileUpload.uploadLocation(part, fileName,
			// request);
			// event.setPhoto_path(photo_path);

			// Integer eventId = eventService.save(event);
			// String md5Value = MD5.calcMD5(String.valueOf(eventId));
			// event.setMd5Value(md5Value);
			// eventService.update(event);
			event = nEventsService.saveOrUpdate(event);
			Integer ids = event.getId();
			event.setMd5Value(MD5.calcMD5(String.valueOf(ids)));
			event = nEventsService.saveOrUpdate(event);

			boolean flagRepeat = false;
			if (null != isRepeat) {
				flagRepeat = true;
			}

			EventDate d = null;
			if (flagRepeat) {
				d = new EventDate();
				d.setStartDate(startDate);
				d.setEndDate(endDate);
				d.setStartTime(startTime);
				d.setEndTime(endTime);
				d.setnEvents(event);
				d.setRepeatType(repeatType);
				d.setRepeatValue(daysValue);
				d.setRepeat(flagRepeat);

				eventDateService.saveOrUpdate(d);

			} else {
				d = new EventDate();
				d.setStartDate(startDate);
				d.setStartTime(startTime);
				d.setEndTime(endTime);
				d.setEndDate(startDate);
				d.setnEvents(event);
				d.setRepeat(flagRepeat);

				eventDateService.saveOrUpdate(d);
			}

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.EVENTID, ids);
			return Response.ok(json.toString()).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("SaveOrUpdate Error!", ex);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			return Response.ok(json.toString()).build();
		}

	}

	@Path("/getEvents/{dataType}/{dateValue}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEvents(@PathParam("dataType") String dataType,
			@PathParam("dateValue") String dateValue) throws JSONException {
		JSONObject json = new JSONObject() ;
		try {
			List<NEvents> events = nEventsService.getEventsByType(dateValue);
			List<NEventData> datas = new ArrayList<NEventData>();
			if (null != events && events.size() > 0) {
				for (int i = 0, j = events.size(); i < j; i++) {
					NEventData d = new NEventData(events.get(i));
					
					datas.add(d);
				}
				GenericEntity<List<NEventData>> entrys = new GenericEntity<List<NEventData>>(
						datas) {
				};
				return Response.ok(entrys).build();
			}
		} catch (ServiceException e) {
			logger.error("Get " + dateValue
					+ " Data Exception.On NEventsResource->getAllEvents", e);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
		}
		return Response.ok(json.toString()).build();
	}
	
	
}
