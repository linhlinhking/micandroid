package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Location;
import ningbo.media.bean.tuan.Shop;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.LocationService;
import ningbo.media.service.ShopService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@Path("/shop")
public class ShopResource {
	
	@Resource
	private ShopService shopService ;
	
	@Resource
	private LocationService locationService ;
	

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addShop(@FormParam("key")
	String key, @FormParam("shopArea")
	String shopArea, @FormParam("trafficInfo")
	String trafficInfo, @FormParam("locationId")
	Integer locationId) throws JSONException {

		Shop shop = new Shop() ;
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
			if(null == location){
				json.put(Constant.CODE, JSONCode.SHOP_LOCATION_NOEXISTS) ;
				return Response.ok(json.toString()).build() ;
			}
			
			shop.setLocation(location) ;
			shop.setShopArea(shopArea) ;
			shop.setShopTrafficinfo(trafficInfo) ;
			Integer ids = shopService.save(shop) ;
			json.put(Constant.CODE, JSONCode.SUCCESS) ;
			json.put(Constant.SHOPID, ids) ;
			
		}catch(Exception ex){
			ex.printStackTrace() ;
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION) ;
		}
		return Response.ok(json.toString()).build() ;
	}
}
