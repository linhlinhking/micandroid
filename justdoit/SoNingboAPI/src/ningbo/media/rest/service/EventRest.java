package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ningbo.media.bean.Event;
import ningbo.media.service.EventService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Path("/event")
@Component
@Scope("request")
public class EventRest {
	
	
	@Resource
	private EventService eventService ;
	
	@Path("/showAll") 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllEvent(){
		return eventService.getAll() ;
	}

}
