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
import ningbo.media.data.entity.SystemUserData;
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
	public String addFollow(@FormParam("userId")
	String userId, @FormParam("followId")
	String followId, @FormParam("key")
	String key) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return json.toString();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return json.toString();
			}

			if (!StringUtils.hasText(userId) || !StringUtils.hasText(followId)) {
				json.put(Constant.CODE, JSONCode.NOINPUT);
				return json.toString();
			} else {
				SystemUser u = systemUserService.get(Integer.valueOf(userId));
				SystemUser u1 = systemUserService
						.get(Integer.valueOf(followId));
				if ((u == null) || (u1 == null)) {
					json.put(Constant.CODE, JSONCode.USER_NOEXISTS);
					return json.toString();
				}
			}

			Friends tempFriend = friendsService.getRelationObject(Integer
					.valueOf(userId), Integer.valueOf(followId));

			if (tempFriend == null) {
				Friends follow = new Friends();
				follow.setUserId(Integer.valueOf(userId));
				follow.setFollowId(Integer.valueOf(followId));
				follow.setIsFollowed(FriendType.FOLLOWED);

				Friends fans = new Friends();
				fans.setUserId(Integer.valueOf(followId));
				fans.setFollowId(Integer.valueOf(userId));
				fans.setIsFollowed(FriendType.FANS);

				friendsService.save(follow);
				friendsService.save(fans);
			} else {
				tempFriend.setIsFollowed(FriendType.FOLLOWED);
				friendsService.update(tempFriend);
			}

			json.put(Constant.CODE, JSONCode.SUCCESS);
			return json.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION);
			return json.toString();
		}
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String cancelFollow(@FormParam("userId")
	String userId, @FormParam("followId")
	String followId, @FormParam("key")
	String key) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			Friends tempFriend = friendsService.getRelationObject(Integer
					.valueOf(userId), Integer.valueOf(followId));
			if (tempFriend != null) {
				tempFriend.setIsFollowed(FriendType.FANS);
				friendsService.update(tempFriend);
			}
			json.put(Constant.CODE, JSONCode.SUCCESS);
			return json.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION);
			return json.toString();
		}

	}

	@Path("/user/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFriends(@PathParam("id")
	String id) {
		return Response.ok(getFriendsForUser(id)).build();
	}

	
	
	private FriendList getFriendsForUser(String id) {
		List<Friends> list = friendsService.getFriendsForUserId(Integer
				.valueOf(id));
		List<SystemUserData> userData = new ArrayList<SystemUserData>();
		if (null == list || list.size() < 0) {
			return new FriendList();
		}

		for (int i = 0, j = list.size(); i < j; i++) {
			SystemUserData data = new SystemUserData();
			Friends tmp = list.get(i);
			Integer followId = tmp.getFollowId();
			SystemUser tmpUser = systemUserService.get(followId);
			if (tmpUser != null) {
				data.setId(tmpUser.getId());
				data.setUsername(tmpUser.getUsername());
			}
			userData.add(data);
		}
		return new FriendList(Integer.valueOf(id), userData);
	}

}
