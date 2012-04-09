package ningbo.media.rest.service;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ningbo.media.bean.Favorite;
import ningbo.media.bean.SystemUser;
import ningbo.media.oauth2.utils.StringCode;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.service.FavoriteService;
import ningbo.media.service.SendManagerService;
import ningbo.media.service.SystemUserService;
import ningbo.media.util.ApplicationContextUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/user")
@Component
@Scope("request")
public class SystemUserRest {

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private FavoriteService favoriteService;

	private SendManagerService sendMgrService = (SendManagerService) ApplicationContextUtil
			.getContext().getBean("sendMail");


	/**
	 * description: access:/user/show/{id} example:/user/show/1 [return json.]
	 * request method:get
	 * 
	 * @param id
	 * @return SystemUser
	 */
	@Path("/show/{id : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSystemUserById(@PathParam("id")
	Integer id) {
		SystemUser u = systemUserService.get(id);
		if(null == u){
			String message = "The User Id [" + id +"] No Exists." ;
			throw Jerseys.buildException(Status.NOT_FOUND,message);
		}
		return Response.ok(u).build(); 
	}

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SystemUser> getAll() {
		return systemUserService.getAll();
	}

	/**
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Path("/verifystatus/{id : \\d+}/{key}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String verifystatus(@PathParam("id")
	Integer id, @PathParam("key")
	String key) throws Exception {
		JSONObject json = new JSONObject();
		// the key is wrong
		StringCode stringCode = new StringCode();
		String tempKey = stringCode.decrypt(key);

		if (key.isEmpty()) {
			json.put(Constant.CODE, JSONCode.KEYISNULL);
			return json.toString();

		} else if (!Constant.KEY.equals(tempKey)) {
			json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
			return json.toString();
		}
		SystemUser u = systemUserService.get(id);
		u.setStatus(true);
		try {
			systemUserService.update(u);
			return json.put(Constant.CODE, JSONCode.SUCCESS).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION);
			return json.toString();
		}
	}

	/**
	 * This method is judge whether the username and email is exists or not If
	 * exists returns true, and the register can't save the information
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@Path("/register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addSystemUser(@FormParam("username")
	String username, @FormParam("password")
	String password, @FormParam("email")
	String email, @FormParam("key")
	String key, @Context
	HttpServletRequest request) throws JSONException {
		JSONObject json = new JSONObject();
		// the key is wrong
		if (key.isEmpty()) {
			json.put(Constant.CODE, "1");
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(Constant.CODE, "2");
			return json.toString();
		}

		if ("".equals(username) || username == null) {
			json.put(Constant.CODE, "3");
			return json.toString();
		}
		if ("".equals(email) || email == null) {
			json.put(Constant.CODE, "4");
			return json.toString();
		}
		if ("".equals(password) || password == null) {
			json.put(Constant.CODE, "5");
			return json.toString();
		}

		// return true if the username is exist
		final boolean bool_username = systemUserService.isExist("username",
				username);
		try {
			if (bool_username) {
				json.put(Constant.CODE, "6");
				return json.toString();
			}
		} catch (JSONException e) {
			e.printStackTrace();
			json.put(Constant.CODE, "8");
			return json.toString();
		}
		// return true if the email is exists
		final boolean bool_email = systemUserService.isExist("email", email);
		try {
			if (bool_email) {
				json.put(Constant.CODE, "7");
				return json.toString();
			}

		} catch (JSONException e) {
			e.printStackTrace();
			json.put(Constant.CODE, "8");
			return json.toString();
		}
		// judge whether the username and email is exists or not
		SystemUser u = new SystemUser();
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		u.setDatetime(new Date());
		u.setIsManager(false);
		u.setStatus(false);
		try {
			Integer id = systemUserService.save(u);
			StringCode code = new StringCode();
			String tempKey = code.encrypt(key);
			sendMgrService.sendHtmlMail(email, username, String.valueOf(id),
					tempKey);
			json.put(Constant.USERID, id);
			json.put(Constant.CODE, "0");
			return json.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, "8");
			return json.toString();
		}
	}

	@Path("/resend/email/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response resendEmail(@PathParam("id")
	String id) throws Exception {
		SystemUser u = systemUserService.get(Integer.valueOf(id));
		JSONObject json = new JSONObject();
		if (null == u) {
			json.put(Constant.CODE, JSONCode.USER_NOEXISTS);
			return Response.ok(json.toString()).build();
		} else {
			StringCode code = new StringCode();
			String tempKey = code.encrypt(Constant.KEY);
			sendMgrService.sendHtmlMail(u.getEmail(), u.getUsername(), id,
					tempKey);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			return Response.ok(json.toString()).build();
		}
	}

	/**
	 * This method is judge whether the username and email is exists or not If
	 * exists returns true, and the register can't save the information
	 * 
	 * @param form
	 * @param request
	 * @return
	 */
	@Path("/edit")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String editSystemUser(FormDataMultiPart form, @Context
	HttpServletRequest request) throws JSONException {
		Integer id = Integer.parseInt(form.getField("id").getValue());
		String username = form.getField("username").getValue();
		String password = form.getField("password").getValue();
		String email = form.getField("email").getValue();
		String key = form.getField("key").getValue();
		// FormDataBodyPart part = form.getField("head_photo");
		// String fileName = part.getContentDisposition().getFileName();
		JSONObject json = new JSONObject();
		// the key is wrong
		if (key.isEmpty()) {
			json.put(Constant.CODE, JSONCode.KEYISNULL);
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
			return json.toString();
		}
		// return true if the username is exist
		final boolean bool_username = systemUserService.isExist("username",
				username);
		if (bool_username) {
			json.put(Constant.CODE, JSONCode.USERNAME_EXISTS).toString();
			return json.toString();
		}
		// return true if the email is exists
		final boolean bool_email = systemUserService.isExist("email", email);
		if (bool_email) {
			json.put(Constant.CODE, JSONCode.USER_EMAIL_EXISTS).toString();
			return json.toString();
		}

		// judge whether the username and email is exists or not
		SystemUser u = new SystemUser();
		u.setId(id);
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		u.setLastModifyTime(new Date());
		u.setIsManager(false);
		try {
			systemUserService.update(u);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.USERID, id);
			return json.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION);
			return json.toString();
		}
	}

	@Path("/check/{property}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String isExist(@PathParam("property")
	String property, @QueryParam("value")
	String value) {
		Boolean flag = systemUserService.isExist(property, value);
		return flag.toString();
	}

	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("username")
	String username, @FormParam("password")
	String password, @FormParam("key")
	String key, @FormParam("device_id")
	String device_id) throws JSONException {

		JSONObject json = new JSONObject();
		if (key.isEmpty()) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYISNULL);
			return Response.ok(json.toString()).build();
		} else if (!Constant.KEY.equals(key)) {
			json.put(Constant.CODE, JSONCode.GLOBAL_KEYINPUTINVALID);
			return Response.ok(json.toString()).build();
		}
		if (username.isEmpty()) {
			json.put(Constant.CODE, JSONCode.USERNAME_NOINPUT);
			return Response.ok(json.toString()).build();
		}
		if (password.isEmpty()) {
			json.put(Constant.CODE, JSONCode.USE_PASSWORD_NOINPUT);
			return Response.ok(json.toString()).build();
		}

		try {
			Integer tempUser = systemUserService.login(username, password);
			if (0 != tempUser) {
				json.put(Constant.USERID, tempUser);
				json.put(Constant.CODE, JSONCode.SUCCESS);
			} else {
				json.put(Constant.CODE,JSONCode.USER_NOEXISTS);
			}
			//
			List<Favorite> listFav = favoriteService
					.findFavoriteByDeviceForUser(device_id);
			if (null != listFav && listFav.size() > 0) {
				for (int i = 0; i < listFav.size(); i++) {
					Favorite entity = listFav.get(i);
					entity.setId(listFav.get(i).getId());
					entity.setUserId(tempUser);
					favoriteService.update(entity);
				}
			}

			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE,JSONCode.THROWEXCEPTION);
			return Response.ok(json.toString()).build();
		}

	}

}
