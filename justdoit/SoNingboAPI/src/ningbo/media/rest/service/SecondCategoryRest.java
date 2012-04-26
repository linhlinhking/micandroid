package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ningbo.media.rest.dto.SecondCategoryData;
import ningbo.media.service.SecondCategoryService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/category/second")
@Component
@Scope("request")
public class SecondCategoryRest {

	@Resource
	private SecondCategoryService secondCategoryService;


	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SecondCategoryData> getAllSecondCategory() {
		return secondCategoryService.querySecondCategoryData(null) ;
	}

	@Path("/show/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SecondCategoryData> getCategoryByFirst(@PathParam("id")
	Integer id) {
		if (id == null) {
			return null;
		}
		return secondCategoryService.querySecondCategoryData(id) ;
	}
	
	

}
