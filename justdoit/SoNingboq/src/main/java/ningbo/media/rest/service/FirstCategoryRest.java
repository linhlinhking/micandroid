package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.rest.dto.FirstCategoryData;
import ningbo.media.rest.dto.SecondCategoryData;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.FirstCategoryService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/category/first")
@Component
@Scope("request")
public class FirstCategoryRest {

	@Resource
	private FirstCategoryService firstCategoryService;

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFirstCategory() {
		List<FirstCategoryData> data = firstCategoryService
				.getAllFirstCategory();
		GenericEntity<List<FirstCategoryData>> entiry = new GenericEntity<List<FirstCategoryData>>(
				data) {
		};
		return Response.ok(entiry).build();
	}

	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SecondCategoryData> querySecondCategoryByName(
			@PathParam("name")
			String name) {
		return firstCategoryService.getFristCategoryByName(name);
	}

	@Path("/show/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFirstCategoryById(@PathParam("id")
	String id) {
		if (!StringUtils.hasText(id)) {
			return null;
		}
		return Response.ok(
				firstCategoryService.getFirstCategoryById(Integer.valueOf(id)))
				.build();
	}

}
