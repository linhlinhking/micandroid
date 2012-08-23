package ningbo.media.rest.service;

import java.util.Date;

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

	@Path("/addOrUpdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRate(@FormParam("key") String key,
			@FormParam("userId") String userId,
			@FormParam("locationId") String locationId,
			@FormParam("overAll") String overAll,
			@FormParam("rank1") String rank1, @FormParam("rank2") String rank2,
			@FormParam("rank3") String rank3, @FormParam("rateId") String rateId)
			throws JSONException {
		JSONObject json = new JSONObject();
		Rate rate = null;
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
			
			if(!StringUtils.hasText(rateId)){
				rate = new Rate();
				rate.setCreateTime(new Date()) ;
			}else{
				rate = rateService.get(Integer.valueOf(rateId)) ;
				rate.setUpdateTime(new Date()) ;
			}

			SystemUser u = systemUserService.get(Constant.MD5_FIELD, userId);
			if (null == u) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_USER_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			rate.setSystemUser(u);

			Location location = locationService.get(Constant.MD5_FIELD,
					locationId);
			if (null == location) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			rate.setLocation(location);
			
			int tOverAll=0,tRank1=0,tRank2=0,tRank3=0 ;
			if (StringUtils.hasText(overAll)) {
				 tOverAll = Integer.valueOf(overAll);
			}
			rate.setOverAll(tOverAll) ;
			if (StringUtils.hasText(rank1)) {
				tRank1 = Integer.valueOf(rank1);
			}
			rate.setRank1(tRank1) ;
			if (StringUtils.hasText(rank2)) {
				tRank2 = Integer.valueOf(rank2);
			}
			rate.setRank2(tRank2) ;
			if (StringUtils.hasText(rank3)) {
				tRank3 = Integer.valueOf(rank3);
			}
			rate.setRank3(tRank3) ;
			
			
			rate = rateService.saveOrUpdate(rate) ;
			Integer tempIds = rate.getId() ;
			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.RATEID,tempIds);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.CODE, JSONCode.COMMENT_THROWEXCEPTION);
			return Response.ok(json.toString()).build();
		}

	}

}
