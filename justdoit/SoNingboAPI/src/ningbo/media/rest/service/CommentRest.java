package ningbo.media.rest.service;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Comment;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.data.api.LocationCommentList;
import ningbo.media.data.api.UserCommentList;
import ningbo.media.data.entity.LocationCommentData;
import ningbo.media.data.entity.LocationDetail;
import ningbo.media.data.entity.UserCommentData;
import ningbo.media.rest.dto.SystemUserData;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.CommentService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SystemUserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/comment")
@Component
@Scope("request")
public class CommentRest {

	@Resource
	private CommentService commentService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private LocationService locationService;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(@FormParam("key")
	String key, @FormParam("userId")
	String userId, @FormParam("locationId")
	String locationId, @FormParam("commentContent")
	String commentContent) throws JSONException {
		JSONObject json = new JSONObject();
		Comment comment = new Comment();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				return Response.ok(json.toString()).build();
			}

			if (!StringUtils.hasText(userId)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_NO_INPUT);
				return Response.ok(json.toString()).build();
			} else {
				SystemUser u = systemUserService.get(Integer.valueOf(userId));
				if (null == u) {
					json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
					json.put(Constant.MESSAGE, JSONCode.MSG_USER_NOEXISTS);
					return Response.ok(json.toString()).build();
				}
				comment.setSystemUser(u);
			}

			if (!StringUtils.hasText(locationId)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_NO_INPUT);
				return Response.ok(json.toString()).build();
			} else {
				Location location = locationService.get(Integer
						.valueOf(locationId));
				if (null == location) {
					json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
					json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_NOEXISTS);
					return Response.ok(json.toString()).build();
				}
				comment.setLocation(location);
			}

			comment.setCommentContent(commentContent);
			comment.setDate_time(new Date());
			Integer ids = commentService.save(comment);
			json.put(Constant.COMMENTID, String.valueOf(ids));
			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			return Response.ok(json.toString()).build();
		}
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteComment(@FormParam("commentId")
	String id, @FormParam("userId")
	String userId, @FormParam("key")
	String key) throws JSONException {
		JSONObject json = new JSONObject();

		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				return Response.ok(json.toString()).build();
			}

			if (!StringUtils.hasText(id)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_NO_INPUT);
				return Response.ok(json.toString()).build();
			} else {
				Comment c = commentService.getCommentByUser(userId, id);
				if (null == c) {
					json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
					json.put(Constant.MESSAGE, JSONCode.MSG_COMMENT_NOEXISTS);
					return Response.ok(json.toString()).build();
				}
				commentService.delete(c);
				json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
				json.put(Constant.MESSAGE, JSONCode.MSG_DELETE_SUCCESS);
				return Response.ok(json.toString()).build();
			}
		} catch (Exception ex) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			return Response.ok(json.toString()).build();
		}
	}

	@Path("/user/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserCommentList getUserCommentList(@PathParam("userId")
	String id) {
		List<Comment> list = commentService.getList("systemUser.id", Integer
				.valueOf(id));
		if (null == list || list.size() < 0) {
			return new UserCommentList();
		}
		List<UserCommentData> tempList = new ArrayList<UserCommentData>();
		UserCommentData uc = null;
		for (Comment c : list) {
			uc = new UserCommentData();
			Location loc = c.getLocation();
			if (null != loc) {
				LocationDetail detail = new LocationDetail();
				detail.setLocationId(loc.getId()) ;
				detail.setMd5Value(loc.getMd5Value()) ;
				detail.setName_cn(loc.getName_cn()) ;
				detail.setName_en(loc.getName_en()) ;
				detail.setLatitude(loc.getLatitude()) ;
				detail.setLongitude(loc.getLongitude()) ;
				
				uc.setLocationDetail(detail) ;
			}

			uc.setCommentId(c.getId());
			uc.setCommentContent(c.getCommentContent());
			tempList.add(uc);
		}
		return new UserCommentList(tempList, Integer.valueOf(id));
	}

	@Path("/location/{locationId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LocationCommentList getLocationCommentList(@PathParam("locationId")
	String id) {
		List<Comment> list = commentService.getList("location.id", Integer
				.valueOf(id));
		if (null == list || list.size() < 0) {
			return new LocationCommentList();
		}
		List<LocationCommentData> tempList = new ArrayList<LocationCommentData>();
		LocationCommentData lc = null;
		for (Comment c : list) {
			lc = new LocationCommentData();
			lc.setCommentContent(c.getCommentContent());
			lc.setCommentId(c.getId());

			SystemUser tempUser = c.getSystemUser();
			if (null != tempUser) {
				SystemUserData data = new SystemUserData();
				data.setUsername(tempUser.getUsername());
				data.setMd5Value(tempUser.getMd5Value());
				if (null != tempUser.getPhoto_path()) {
					data.setPhoto_path(tempUser.getPhoto_path());
				}else{
					data.setPhoto_path("0") ;
				}

				lc.setSystemUserData(data);
			}
			lc.setDate_time(c.getDate_time());
			tempList.add(lc);
		}
		return new LocationCommentList(tempList, Integer.valueOf(id));
	}
}
