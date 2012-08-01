package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.service.LocationExtService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/locationExt")
@Component
@Scope("request")
public class LocationExtResource {
	
	@Resource
	private LocationExtService locationExtService;
	
	
	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLocationExt(@FormParam("location_website")String website){
		
		
		return null ;
	}
}
