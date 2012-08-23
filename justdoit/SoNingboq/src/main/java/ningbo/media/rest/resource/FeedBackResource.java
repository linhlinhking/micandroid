package ningbo.media.rest.resource;

import java.util.Date;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.FeedBack;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.service.FeedBackService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/feedback")
@Component
@Scope("request")
public class FeedBackResource {

	@Resource
	private FeedBackService feedBackService;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFeedBack(@FormParam("md5Value")
	String md5Value, @FormParam("content")
	String content, @FormParam("key")
	String key, @FormParam("feedEmail")
	String feedEmail) throws JSONException {
		JSONObject json = new JSONObject();
		FeedBack feedBack = new FeedBack();
		try {
			if (key.isEmpty()) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				return Response.ok(json.toString()).build();

			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				return Response.ok(json.toString()).build();
			}
			feedBack.setContent(content) ;
			feedBack.setFeedEmail(feedEmail) ;
			feedBack.setUserMd5Value(md5Value) ;
			feedBack.setDate_time(new Date()) ;
			
			feedBackService.save(feedBack) ;
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.SERVER_EXCEPTION);
			return Response.ok(json.toString()).build();
		}
		json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
		return Response.ok(json.toString()).build();
	}
}
