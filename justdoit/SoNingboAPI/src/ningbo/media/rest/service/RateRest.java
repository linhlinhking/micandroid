package ningbo.media.rest.service;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Location;
import ningbo.media.bean.Rate;
import ningbo.media.bean.SystemUser;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.LocationService;
import ningbo.media.service.RateService;
import ningbo.media.service.SystemUserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/rate")
@Component
@Scope("request")
public class RateRest {

	@Resource
	private LocationService locationService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private RateService rateService;

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRate(@FormParam("key")
	String key, @FormParam("userId")
	String userId, @FormParam("locationId")
	String locationId, @FormParam("rank")
	String rank) throws JSONException {
		JSONObject json = new JSONObject();
		Rate rate = new Rate();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			if (!StringUtils.hasText(userId)) {
				json.put(Constant.CODE, JSONCode.NOINPUT);
				return Response.ok(json.toString()).build();
			} else {
				SystemUser u = systemUserService.get(Integer.valueOf(userId));
				if (u == null) {
					json.put(Constant.CODE, JSONCode.USER_NOEXISTS);
					return Response.ok(json.toString()).build();
				}
				rate.setSystemUser(u);
			}

			if (!StringUtils.hasText(locationId)) {
				json.put(Constant.CODE, JSONCode.LOCATIONID_NOINPUT);
				return Response.ok(json.toString()).build();
			} else {
				Location location = locationService.get(Integer
						.valueOf(locationId));
				if (location == null) {
					json.put(Constant.CODE, JSONCode.LOCATION_NOEXISTS);
					return Response.ok(json.toString()).build();
				}
				rate.setLocation(location);
			}
			
			Rate tempRate = rateService.getRateByIds(Integer.valueOf(userId), Integer
					.valueOf(locationId));
			double ranks = 0.0;
			if (StringUtils.hasText(rank)) {
				ranks = Double.valueOf(rank);
			}
			if(tempRate == null){
				rate.setRank(ranks);
				Integer ids = rateService.save(rate);
				json.put(Constant.RATEID, ids);
			}else{
				tempRate.setRank(ranks) ;
				rateService.update(tempRate) ;
				json.put(Constant.RATEID, tempRate.getId());
			}
			json.put(Constant.CODE, JSONCode.SUCCESS);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.COMMENT_THROWEXCEPTION);
			return Response.ok(json.toString()).build();
		}

	}


}
