package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.tuan.MenuItem;
import ningbo.media.bean.tuan.MenuTool;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.MenuItemService;
import ningbo.media.service.MenuToolService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/menuitem")
@Component
@Scope("request")
public class MenuItemResource {

	@Resource
	private MenuItemService menuItemService;
	
	@Resource
	private MenuToolService menuToolService ;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMenuItem(@FormParam("key")
	String key, @FormParam("menuName")
	String menuName, @FormParam("price")
	String price, @FormParam("description")
	String description, @FormParam("toolId")
	Integer toolId) throws JSONException {
		MenuItem item = new MenuItem() ;
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}
			
			MenuTool temp = menuToolService.get(toolId) ;
			if(null == temp){
				json.put(Constant.CODE, JSONCode.MENU_TOOL_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			
			item.setMenuTool(temp) ;
			item.setPrice(Float.valueOf(price)) ;
			item.setDescriptoin(description) ;
			item.setMenuName(menuName) ;
			Integer ids = menuItemService.save(item) ;
			
			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.MENUITEMID,ids) ;
			return Response.ok(json.toString()).build();
		}catch(Exception ex){
			ex.printStackTrace() ;
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION) ;
			return Response.ok(json.toString()).build();
		}
		
	}
}
