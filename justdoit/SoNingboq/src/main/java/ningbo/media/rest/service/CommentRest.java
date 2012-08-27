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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.AspectsCategory;
import ningbo.media.bean.Comment;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.enums.CommentType;
import ningbo.media.data.api.LocationCommentList;
import ningbo.media.data.api.UserCommentList;
import ningbo.media.data.entity.CommentData;
import ningbo.media.data.entity.LocationCommentData;
import ningbo.media.data.entity.LocationDetail;
import ningbo.media.data.entity.UserCommentData;
import ningbo.media.rest.dto.AspectsCategoryData;
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

	@Path("/addOrUpdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(@FormParam("key") String key,
			@FormParam("userId") String userId,
			@FormParam("locationId") String locationId,
			@FormParam("commentContent") String commentContent,
			@FormParam("commentId") String commentId,
			@FormParam("overAll") String overAll,
			@FormParam("rank1") String rank1, @FormParam("rank2") String rank2,
			@FormParam("rank3") String rank3) throws JSONException {
		JSONObject json = new JSONObject();
		Comment comment = null;
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

			if (StringUtils.hasText(commentId)) {
				comment = commentService.get(Integer.valueOf(commentId));
				comment.setUpdateTime(new Date());
			} else {
				comment = new Comment();
				comment.setCreateTime(new Date());
			}

			SystemUser u = systemUserService.get(Constant.MD5_FIELD, userId);
			if (null == u) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_USER_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			comment.setSystemUser(u);

			Location location = locationService.get(Constant.MD5_FIELD,
					locationId);
			if (null == location) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			comment.setLocation(location);
			comment.setCommentContent(commentContent);

			int tOverAll = 0, tRank1 = 0, tRank2 = 0, tRank3 = 0;
			if (StringUtils.hasText(overAll)) {
				tOverAll = Integer.valueOf(overAll);
			}
			comment.setOverAll(tOverAll);
			if (StringUtils.hasText(rank1)) {
				tRank1 = Integer.valueOf(rank1);
			}
			comment.setRank1(tRank1);
			if (StringUtils.hasText(rank2)) {
				tRank2 = Integer.valueOf(rank2);
			}
			comment.setRank2(tRank2);
			if (StringUtils.hasText(rank3)) {
				tRank3 = Integer.valueOf(rank3);
			}
			comment.setRank3(tRank3);

			comment = commentService.saveOrUpdate(comment);
			Integer ids = comment.getId();
			json.put(Constant.COMMENTID, ids);
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
	public Response deleteComment(@FormParam("commentId") String id,
			@FormParam("userId") String userId, @FormParam("key") String key)
			throws JSONException {
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
	public UserCommentList getUserCommentList(@PathParam("userId") String id) {
		List<Comment> list = commentService.getListByMd5(id, CommentType.USER);
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
				detail.setLocationId(loc.getId());
				detail.setMd5Value(loc.getMd5Value());
				detail.setName_cn(loc.getName_cn());
				detail.setName_en(loc.getName_en());
				detail.setLatitude(loc.getLatitude());
				detail.setLongitude(loc.getLongitude());

				uc.setLocationDetail(detail);
			}
			CommentData cd = new CommentData();

			cd.setCommentId(c.getId());
			cd.setCommentContent(c.getCommentContent());
			cd.setOverAll(c.getOverAll());
			cd.setRank1(c.getRank1());
			cd.setRank2(c.getRank2());
			cd.setRank3(c.getRank3());
			cd.setCreateTime(c.getCreateTime());
			cd.setUpdateTime(c.getUpdateTime());
			uc.setCommentData(cd);

			tempList.add(uc);
		}
		return new UserCommentList(tempList, id);
	}

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllComments() throws JSONException {
		List<Comment> list = commentService.getAll();
		JSONObject json = new JSONObject();
		if (null == list) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_NO_DATA);
			return Response.ok(json.toString()).build();
		}
		List<CommentData> listData = new ArrayList<CommentData>();
		for (int i = 0, j = list.size(); i < j; i++) {
			Comment c = list.get(i);
			CommentData d = new CommentData();
			d.setCommentId(c.getId());
			d.setCommentContent(c.getCommentContent());
			if (null != c.getSystemUser()) {
				SystemUser su = c.getSystemUser();
				SystemUserData u = new SystemUserData();
				u.setUsername(su.getUsername());
				u.setEmail(su.getEmail());
				d.setUserData(u);
			}

			if (null != c.getLocation()) {
				Location l = c.getLocation();
				LocationDetail ld = new LocationDetail();
				ld.setName_cn(l.getName_cn());
				ld.setAddress_cn(l.getAddress_cn());

				d.setLocationData(ld);
			}
			d.setCreateTime(c.getCreateTime());
			d.setUpdateTime(c.getUpdateTime());

			listData.add(d);

		}
		GenericEntity<List<CommentData>> entity = new GenericEntity<List<CommentData>>(
				listData) {
		};
		return Response.ok(entity).build();
	}

	@Path("/location/{locationId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LocationCommentList getLocationCommentList(
			@PathParam("locationId") String id) {
		List<Comment> list = commentService.getListByMd5(id,
				CommentType.LOCATION);
		if (null == list || list.size() < 0) {
			return new LocationCommentList();
		}
		List<LocationCommentData> tempList = new ArrayList<LocationCommentData>();
		LocationCommentData lc = null;
		for (Comment c : list) {
			lc = new LocationCommentData();
			CommentData cd = new CommentData();
			cd.setCommentId(c.getId());
			cd.setCommentContent(c.getCommentContent());
			cd.setOverAll(c.getOverAll());
			cd.setRank1(c.getRank1());
			cd.setRank2(c.getRank2());
			cd.setRank3(c.getRank3());
			cd.setCreateTime(c.getCreateTime());
			cd.setUpdateTime(c.getUpdateTime());
			lc.setCommentData(cd);

			SystemUser tempUser = c.getSystemUser();
			if (null != tempUser) {
				SystemUserData data = new SystemUserData();
				data.setUsername(tempUser.getUsername());
				data.setMd5Value(tempUser.getMd5Value());
				if (null != tempUser.getPhoto_path()) {
					data.setPhoto_path(tempUser.getPhoto_path());
				} else {
					data.setPhoto_path("0");
				}

				cd.setUserData(data);
			}

			tempList.add(lc);
		}
		Location temp = locationService.get(Constant.MD5_FIELD, id);
		LocationDetail detail = new LocationDetail();
		if (null != temp) {
			List<SecondCategory> scList = temp.getSecondCategorys();
			if (null != scList && scList.size() > 0) {
				SecondCategory sc = scList.get(0);
				List<AspectsCategory> aspectList = sc.getFirstCategory()
						.getAspectsCategorys();
				List<AspectsCategoryData> aspectsData = new ArrayList<AspectsCategoryData>();
				for (AspectsCategory tmp : aspectList) {
					AspectsCategoryData d = new AspectsCategoryData();
					d.setAspect_cn(tmp.getAspect_cn());
					d.setAspect_en(tmp.getAspect_en());
					aspectsData.add(d);
				}
				detail.setAspectsDatas(aspectsData);
			}

			detail.setMd5Value(temp.getMd5Value());
		} else {
			detail.setMd5Value(id);
		}
		return new LocationCommentList(tempList, detail);
	}
}
