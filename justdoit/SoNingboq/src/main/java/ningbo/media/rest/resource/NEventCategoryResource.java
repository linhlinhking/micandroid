package ningbo.media.rest.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ningbo.media.bean.EventFirstCategory;
import ningbo.media.service.EventFirstCategoryService;

@Path("/eventCategory")
@Component
@Scope("request")
public class NEventCategoryResource {

	@Resource
	private EventFirstCategoryService eventFirstCategoryService ;
	
	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFirstCategory() {
		List<EventFirstCategory> data = eventFirstCategoryService.getAll() ;
		
		for(EventFirstCategory ef : data){
			System.out.println(ef.getName_cn());
		}
		
		GenericEntity<List<EventFirstCategory>> entiry = new GenericEntity<List<EventFirstCategory>>(
				data) {
		};
		return Response.ok(entiry).build();
	}
}
