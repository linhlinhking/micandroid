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

import ningbo.media.bean.Comment;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.data.api.LocationCommentList;
import ningbo.media.data.api.UserCommentList;
import ningbo.media.data.entity.LocationCommentData;
import ningbo.media.data.entity.UserCommentData;
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
	public String addComment(@FormParam("key") String key,
			@FormParam("userId") String userId,
			@FormParam("locationId") String locationId,
			@FormParam("commentContent") String commentContent)
			throws JSONException {
		JSONObject json = new JSONObject();
		Comment comment = new Comment();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return json.toString();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return json.toString();
			}

			if (!StringUtils.hasText(userId)) {
				json.put(Constant.CODE, JSONCode.NOINPUT);
				return json.toString();
			} else {
				SystemUser u = systemUserService.get(Integer.valueOf(userId));
				if (u == null) {
					json.put(Constant.CODE, JSONCode.USER_NOEXISTS);
					return json.toString();
				}
				comment.setSystemUser(u);
			}

			if (!StringUtils.hasText(locationId)) {
				json.put(Constant.CODE, JSONCode.LOCATIONID_NOINPUT);
				return json.toString();
			} else {
				Location location = locationService.get(Integer
						.valueOf(locationId));
				if (location == null) {
					json.put(Constant.CODE, JSONCode.LOCATION_NOEXISTS);
					return json.toString();
				}
				comment.setLocation(location);
			}

			comment.setCommentContent(commentContent);
			comment.setDate_time(new Date());
			Integer ids = commentService.save(comment);
			json.put(Constant.COMMENTID, String.valueOf(ids));
			json.put(Constant.CODE, JSONCode.SUCCESS);
			return json.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.COMMENT_THROWEXCEPTION);
			return json.toString();
		}
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteComment(@FormParam("commentId") String id,
			@FormParam("key") String key) throws JSONException {
		JSONObject json = new JSONObject();

		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return json.toString();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return json.toString();
			}

			if (!StringUtils.hasText(id)) {
				json.put(Constant.CODE, JSONCode.NOINPUT);
				return json.toString();
			} else {
				Comment c = commentService.get(Integer.valueOf(id));
				if (c == null) {
					json.put(Constant.CODE, JSONCode.COMMENT_NOEXISTS);
					return json.toString();
				}
				commentService.delete(c);
				json.put(Constant.CODE, JSONCode.SUCCESS);
				return json.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.COMMENT_THROWEXCEPTION);
			return json.toString();
		}
	}

	@Path("/get/user/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserCommentList getUserCommentList(@PathParam("userId")String id){
		List<Comment> list = commentService.getList("systemUser.id",Integer.valueOf(id)) ;
		if(null == list || list.size() < 0){
			return new UserCommentList() ;
		}
		List<UserCommentData> tempList = new ArrayList<UserCommentData>() ;
		UserCommentData uc = null ;
		for(Comment c : list){
			uc = new UserCommentData() ;
			
			uc.setLocationId(c.getLocation().getId()) ;
			uc.setCommentId(c.getId()) ;
			uc.setCommentContent(c.getCommentContent()) ;
			tempList.add(uc) ;
		}
		return new UserCommentList(tempList,Integer.valueOf(id)) ;
	}
	
	
	@Path("/get/location/{locationId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LocationCommentList getLocationCommentList(@PathParam("locationId")String id){
		List<Comment> list = commentService.getList("location.id",Integer.valueOf(id)) ;
		if(null == list || list.size() < 0){
			return new LocationCommentList() ;
		}
		List<LocationCommentData> tempList = new ArrayList<LocationCommentData>() ;
		LocationCommentData lc= null ;
		for(Comment c : list){
			lc = new LocationCommentData() ;
			lc.setCommentContent(c.getCommentContent()) ;
			lc.setCommentId(c.getId()) ;
			lc.setUserId(c.getSystemUser().getId()) ;
			
			tempList.add(lc) ;
		}
		return new LocationCommentList(tempList,Integer.valueOf(id)) ;
	}
}
