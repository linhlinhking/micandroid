package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.Path;

import ningbo.media.service.StaffPicksService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Path("/staff")
@Component
@Scope("request")
public class StaffPicksResource {

	@Resource
	private StaffPicksService staffPicksService ;
	
}
