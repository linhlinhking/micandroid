package ningbo.media.rest.service;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ningbo.media.core.rest.BaseResource;
import ningbo.media.data.api.TagsList;
import ningbo.media.service.TagsService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Path("/tag")
@Component
@Scope("request")
public class TagsRest extends BaseResource{

	@Resource
	private TagsService tagsService ;
	
	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TagsList get(){
		return new TagsList(tagsService.getAllTags(),getResourceUrl()) ;
	}
}
