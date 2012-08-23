package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Location;
import ningbo.media.bean.tuan.MenuTool;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.LocationService;
import ningbo.media.service.MenuToolService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/menutool")
@Component
@Scope("request")
public class MenuToolResource {

	@Resource
	private MenuToolService menuToolService;
	
	@Resource
	private LocationService locationService ;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMenuTool(@FormParam("key")
	String key, @FormParam("locationId")
	Integer locationId) throws JSONException {
		MenuTool tool = new MenuTool() ;
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}
			
			Location location = locationService.get(locationId) ;
			if(location == null){
				json.put(Constant.CODE, JSONCode.MENU_LOCATION_NOEXISTS) ;
				return Response.ok(json.toString()).build() ;
			}
			tool.setLocation(location) ;
			Integer ids = menuToolService.save(tool) ;
			json.put(Constant.CODE, JSONCode.SUCCESS) ;
			json.put(Constant.MENUTOOLID, ids) ;
			
		}catch(Exception ex){
			ex.printStackTrace() ;
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION) ;
			return Response.ok(json.toString()).build() ;
		}
		return Response.ok(json.toString()).build() ;
	}
}
