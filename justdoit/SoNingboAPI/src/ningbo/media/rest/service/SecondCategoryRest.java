package ningbo.media.rest.service;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ningbo.media.service.FirstCategoryService;
import ningbo.media.service.SecondCategoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/category/second")
@Component
@Scope("request")
public class SecondCategoryRest {

	@Resource
	private SecondCategoryService secondCategoryService;

	@Resource
	private FirstCategoryService firstCategoryService;

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSecondCategory() {
		return Response.ok(secondCategoryService.querySecondCategoryData(null))
				.build();
	}

	@Path("/show/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryByFirst(@PathParam("id")
	Integer id) {
		if (id == null) {
			return null;
		}
		return Response.ok(firstCategoryService.getFirstCategoryById(id))
				.build();
	}

}
