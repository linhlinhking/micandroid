package ningbo.media.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.SecondCategory;
import ningbo.media.service.FirstCategoryService;
import ningbo.media.service.SecondCategoryService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/category/second")
@Component
@Scope("request")
public class SecondCategoryRest {

	@Resource
	private SecondCategoryService secondCategoryService ;
	@Resource
	private FirstCategoryService firstCategoryService ;
	
	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SecondCategory> getAllSecondCategory(){
		return secondCategoryService.getAll() ;
	}
	
	@Path("/show/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SecondCategory> getCategoryByFirst(@PathParam("id")String id){
		if(id == null){
			return null ;
		}
		FirstCategory firstCategory = firstCategoryService.get(Integer.valueOf(id)) ;
		if(firstCategory == null){
			return new ArrayList<SecondCategory>()  ;
		}
		return firstCategory.getSecondCategorys() ;
	}

}
