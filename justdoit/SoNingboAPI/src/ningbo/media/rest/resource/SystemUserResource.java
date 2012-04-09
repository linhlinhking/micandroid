package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ningbo.media.bean.SystemUser;
import ningbo.media.mapper.BeanMapper;
import ningbo.media.rest.WsConstants;
import ningbo.media.rest.dto.SystemUserData;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.service.SystemUserService;

@Path("/systemuser")
@Component
@Scope("request")
public class SystemUserResource {

	@Resource
	private SystemUserService systemUserService ;
	
	@GET
	@Path("/show/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET })
	public SystemUserData getUser(@PathParam("id")Integer id){
		try{
			SystemUser u = systemUserService.get(id) ;
			if(null == u){
				String message = "用户不存在(id:" + id + ")";
				throw Jerseys.buildException(Status.NOT_FOUND, message);
			}
			return BeanMapper.map(u, SystemUserData.class);
		}catch(RuntimeException e){
			throw Jerseys.buildDefaultException(e);
		}
	}
}
