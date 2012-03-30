package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ningbo.media.bean.FirstCategory;
import ningbo.media.service.FirstCategoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/category/first")
@Component
@Scope("request")
public class FirstCategoryRest {

	@Resource
	private FirstCategoryService firstCategoryService ;
	
	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FirstCategory> getAllFirstCategory(){
		return firstCategoryService.getAll() ;
	}
	
	
	@Path("/categoryName/{local}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getCategoryName(@PathParam("local")String local){
		return firstCategoryService.getAllCagegoryName(local) ;
	}
	
	
}
