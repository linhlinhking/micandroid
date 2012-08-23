package ningbo.media.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.SystemUser;

@Path("/json/systemUser")
public class JSONRestService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public SystemUser getSystemUserJson() {

		SystemUser u = new SystemUser();
		u.setId(1);
		u.setEmail("leyxan.nb@gmail.com");
		u.setName_cn("...");
		u.setPassword("123456");
		u.setUsername("Davidning");
		u.setDatetime(new Date());
		u.setPhoto_path("/data/data/image/head.png");
		
		return u ;
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSystemUserJSON(SystemUser sUser){
		String result = "Tracksaved:" + sUser ;
		return Response.status(200).entity(result).build() ;
	} 
}
