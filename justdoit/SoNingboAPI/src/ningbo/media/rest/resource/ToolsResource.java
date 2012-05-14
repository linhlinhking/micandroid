package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ningbo.media.bean.Tools;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.ToolsService;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/tool")
@Component
@Scope("request")
public class ToolsResource {

	@Resource
	private ToolsService toolsService;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTool(@FormParam("toolNameCn")
	String toolName, @FormParam("toolNameEn")
	String keyWords, @FormParam("description")
	String description, @FormParam("key")
	String key) {
		Tools t = new Tools();
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}
			
			t.setToolNameCn(null == toolName ? null : toolName);
			t.setToolNameEn(null == keyWords ? null : keyWords);
			t.setDescription(null == description ? null : description);

			Integer toolId = toolsService.save(t);
			json.put(Constant.TOOLID, toolId);
			json.put(Constant.CODE, JSONCode.SUCCESS);

			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR, ex
					.getMessage());
		}
	}
}
