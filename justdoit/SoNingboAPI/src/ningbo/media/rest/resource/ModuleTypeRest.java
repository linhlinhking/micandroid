package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ningbo.media.bean.ModuleType;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.ModuleTypeService;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/moduletype")
@Component
@Scope("request")
public class ModuleTypeRest {

	@Resource
	private ModuleTypeService moduleTypeService;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTool(@FormParam("moduleName")
	String moduleName, @FormParam("description")
	String description, @FormParam("key")
	String key) {
		ModuleType t = new ModuleType();
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			t.setModuleName(null == moduleName ? null : moduleName);
			t.setDescription(null == description ? null : description);

			Integer typeId = moduleTypeService.save(t);
			json.put(Constant.TYPEID, typeId);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR, ex
					.getMessage());
		}
	}
}
