package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import ningbo.media.bean.FirstCategory;
import ningbo.media.mapper.BeanMapper;
import ningbo.media.rest.WsConstants;
import ningbo.media.rest.dto.FirstCategoryData;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.service.FirstCategoryService;

@Path("/firstcategory")
@Component
public class FirstCategoryResource {

	@Resource
	private FirstCategoryService firstCategoryService ;
	
	@GET
	@Path("/show/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET })
	public FirstCategoryData getFirstCategory(@PathParam("id")Integer id){
		try{
			FirstCategory fc = firstCategoryService.get(id) ;
			if(null == fc){
				String message = "用户不存在(id:" + id + ")";
				throw Jerseys.buildException(Status.NOT_FOUND, message);
			}
			return BeanMapper.map(fc, FirstCategoryData.class);
		}catch(RuntimeException e){
			throw Jerseys.buildDefaultException(e);
		}
	}
}
