package ningbo.media.rest.resource;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.EventCategory;
import ningbo.media.bean.EventDate;
import ningbo.media.bean.Location;
import ningbo.media.bean.NEvents;
import ningbo.media.bean.SystemUser;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/nEvent")
@Component
@Scope("request")
public class NEventsResource {

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
			@FormParam("title") String title,
			@FormParam("subject") String subject,
			@FormParam("telephone") String telephone,
			@FormParam("address") String address,
			@FormParam("startDate") String startDate,
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime,
			@FormParam("endDate") String endDate,
			@FormParam("isRepeat") String isRepeat,
			@FormParam("repeat_type") String repeatType,
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
			if (StringUtils.hasText(eventMd5Value)) {
				event = nEventsService.get(Constant.MD5_FIELD, eventMd5Value);
				event.setUpdateDateTime(new Date());
			} else {
				event = new NEvents();
				event.setCreateDateTime(new Date());
			}

			SystemUser tempUser = systemUserService.get(Constant.MD5_FIELD,
					userMd5Value);
			if (null == tempUser) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_USER_USER_MD5VALUE);
				return Response.ok(json.toString()).build();
			}
			String organizer = tempUser.getUsername();
			event.setSystemUser(tempUser);
			event.setOrganizer(organizer);
			Location tempLocation = locationService.get(Constant.MD5_FIELD,
					locationMd5Value);
			if (null == tempLocation) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_MD5_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
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

			event.setTitle(title);
			event.setSubject(subject);
			event.setTelephone(telephone);
			event.setAddress(address);

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

			boolean flag = false;
			if (null != isRepeat) {
				flag = true;
				event.setRepeat(true);
			} else {
				event.setRepeat(false);
			}

			if (flag) {
				if ((daysValue != null) && (daysValue.length() > 0)) {
					List<String> tmpList = StringUtil
							.getCustomDateString(daysValue);
					for (String str : tmpList) {
						EventDate ed = new EventDate();
						ed.setStartDate(str);
						ed.setEndDate(str);
						ed.setStartTime(startTime);
						ed.setEndTime(endTime);
						eventDateService.save(ed);
					}

				}

			} else {
				EventDate d = new EventDate();
				d.setStartDate(startDate);
				d.setStartTime(startTime);
				d.setEndTime(endTime);
				if ((endDate != null) && (endDate.length() > 0)) {
					d.setEndDate(endDate);
				} else {
					d.setEndDate(startDate);
				}
				d.setnEvents(event);
				eventDateService.save(d);
			}

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.EVENTID, ids);
			return Response.ok(json.toString()).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			return Response.ok(json.toString()).build();
		}

	}

}
