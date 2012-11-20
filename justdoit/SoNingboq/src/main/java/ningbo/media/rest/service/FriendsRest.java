package ningbo.media.rest.service;

import java.util.ArrayList;
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

import ningbo.media.bean.Friends;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.enums.FriendType;
import ningbo.media.data.api.FriendList;
import ningbo.media.rest.dto.SystemUserData;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.FriendsService;
import ningbo.media.service.SystemUserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/friend")
@Component
@Scope("request")
public class FriendsRest {

	@Resource
	private FriendsService friendsService;

	@Resource
	private SystemUserService systemUserService;

	@Path("/add")
	@POST
	public Response addFollow(@FormParam("userId")
	String userId, @FormParam("followId")
	String followId, @FormParam("key")
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

			if (!StringUtils.hasText(userId) || !StringUtils.hasText(followId)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_NO_INPUT);
				return Response.ok(json.toString()).build();
			} else {
				if (userId.equals(followId)) {
					json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
					json.put(Constant.MESSAGE,
							JSONCode.MSG_FRIEND_FOLLOW_YOURSELF);
					return Response.ok(json.toString()).build();
				}
				SystemUser u = systemUserService
						.get(Constant.MD5_FIELD, userId);
				SystemUser u1 = systemUserService.get(Constant.MD5_FIELD,
						followId);
				if ((u == null) || (u1 == null)) {
					json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
					json.put(Constant.MESSAGE, JSONCode.MSG_USER_NOEXISTS);
					return Response.ok(json.toString()).build();
				}
			}

			Friends tempFriend = friendsService.getRelationObject(userId,
					followId);

			if (null == tempFriend) {
				Friends follow = new Friends();
				follow.setUserId(userId);
				follow.setFollowId(followId);
				follow.setIsFollowed(FriendType.FOLLOWED);

				Friends fans = new Friends();
				fans.setUserId(followId);
				fans.setFollowId(userId);
				fans.setIsFollowed(FriendType.FANS);

				friendsService.save(follow);
				friendsService.save(fans);
			} else {
				tempFriend.setIsFollowed(FriendType.FOLLOWED);
				friendsService.update(tempFriend);
			}

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.MESSAGE, JSONCode.MSG_FRIEND_FOLLOW_SUCCESS);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			return Response.ok(json.toString()).build();
		}
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelFollow(@FormParam("userId")
	String userId, @FormParam("followId")
	String followId, @FormParam("key")
	String key) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			Friends tempFriend = friendsService.getRelationObject(userId,
					followId);
			if (tempFriend != null) {
				tempFriend.setIsFollowed(FriendType.FANS);
				friendsService.update(tempFriend);
			}
			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.MESSAGE,
					JSONCode.MSG_FRIEND_CANCEL_FOLLOW_SUCCESS);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			return Response.ok(json.toString()).build();
		}

	}

	@Path("/user/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFriends(@PathParam("id")
	String id) {
		return Response.ok(getFriendsForUser(id, null)).build();
	}

	@Path("/fans/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFans(@PathParam("id")
	String id) {
		return Response.ok(getFriendsForUser(id, FriendType.FANS)).build();
	}

	@Path("/follow/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollowed(@PathParam("id")
	String id) {
		return Response.ok(getFriendsForUser(id, FriendType.FOLLOWED)).build();
	}

	private FriendList getFriendsForUser(String id, FriendType type) {
		List<Friends> list = friendsService.getFriendsForUserId(id, type);
		List<SystemUserData> userData = new ArrayList<SystemUserData>();
		if (null == list || list.size() < 0) {
			return new FriendList();
		}

		for (int i = 0, j = list.size(); i < j; i++) {
			SystemUserData data = new SystemUserData();
			Friends tmp = list.get(i);
			String followId = tmp.getFollowId();
			String remark = tmp.getRemark();
			SystemUser tmpUser = systemUserService.get(Constant.MD5_FIELD,
					followId);
			if (null != tmpUser) {
				String followingMd5Value = tmpUser.getMd5Value() ;
				boolean flag = friendsService.isExistsRelation(id, followingMd5Value);
				data.setUsername(tmpUser.getUsername());
				data.setMd5Value(followingMd5Value);
				data.setIntro(tmpUser.getIntro());
				data.setNickName(tmpUser.getNickName());
				if (null == remark || "".equals(remark) || remark.length() < 0) {
					data.setRemark("") ;
				} else {
					data.setRemark(remark) ;
				}
				if (null != tmpUser.getPhoto_path()) {
					data.setPhoto_path(tmpUser.getPhoto_path());
				} else {
					data.setPhoto_path("0");
				}
				data.setFollowingStatus(flag) ;

			}
			userData.add(data);
		}
		return new FriendList(id, userData);
	}

	@Path("/remark")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRemark(@FormParam("remark")
	String remark, @FormParam("userId")
	String userId, @FormParam("followId")
	String followId, @FormParam("key")
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

			Friends frd = friendsService.getRelationObject(userId, followId);
			if (null == frd) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_FRIEND_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			if ("".equals(remark) || null == remark) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_FRIEND_NO_REMARK);
				return Response.ok(json.toString()).build();
			}

			frd.setRemark(remark);
			friendsService.update(frd);
			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.MESSAGE, remark);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			return Response.ok(json.toString()).build();
		}

	}

	@Path("/search/{id}/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchFriends(@PathParam("name")String name,@PathParam("id")String id){
		return Response.ok(queryFriendsByName(id,name)).build() ;
	}
	
	
	private FriendList queryFriendsByName(String id,String name) {
		List<SystemUser> list = systemUserService.querySystemUserByName(name) ;
		List<SystemUserData> userData = new ArrayList<SystemUserData>();
		if (null == list || list.size() < 0) {
			return new FriendList();
		}

		for (int i = 0, j = list.size(); i < j; i++) {
			SystemUserData data = new SystemUserData();
			SystemUser tmp = list.get(i);
			if (null != tmp) {
				String followingMd5Value = tmp.getMd5Value() ;
				boolean flag = friendsService.isExistsRelation(id, followingMd5Value);
				data.setUsername(tmp.getUsername());
				data.setMd5Value(followingMd5Value);
				data.setIntro(tmp.getIntro());
				data.setNickName(tmp.getNickName());
				if (null != tmp.getPhoto_path()) {
					data.setPhoto_path(tmp.getPhoto_path());
				} else {
					data.setPhoto_path("0");
				}
				data.setFollowingStatus(flag) ;
			}
			userData.add(data);
		}
		return new FriendList(id, userData);
	}
}
