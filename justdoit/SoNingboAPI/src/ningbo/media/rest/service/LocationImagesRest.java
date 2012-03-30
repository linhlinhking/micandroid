package ningbo.media.rest.service;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import ningbo.media.bean.Location;
import ningbo.media.bean.LocationImages;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileUpload;
import ningbo.media.service.LocationImagesService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/location_images")
@Component
@Scope("request")
public class LocationImagesRest {

	@Resource
	private LocationImagesService locationImagesService;

	@Path("/delete/{id : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteLocationImages(@PathParam("id") Integer id) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			locationImagesService.delete(id);
			return json.put("success", true).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return json.put("success", false).toString();
		}
	}
	
	@Path("/showImages/{location_id : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LocationImages> getLocationImages(@PathParam("location_id") Integer location_id) {
		List<LocationImages> images = locationImagesService.getList("location.id", location_id);
		return images;
	}

	/**
	 * This method is judge whether the username and email is exists or not If
	 * exists returns true, and the register can't save the information
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String addLocation(FormDataMultiPart form,
			@Context HttpServletRequest request) throws JSONException {
		String key = form.getField("key").getValue();
		String locationid = form.getField("location_id").getValue();
		JSONObject json = new JSONObject();
		boolean b = false;
		// the key is wrong
		if (key.isEmpty()) {
			json.put("code", "1");
			return json.toString();
		} else if (!Constant.KEY.equals(key)) {
			json.put("code", "2");
			return json.toString();
		}
		FormDataMultiPart part = form;
		List<FormDataBodyPart> listfile = part.getFields("loc_img");
		try {
			for (int i = 0; i < listfile.size(); i++) {
				LocationImages locimg = new LocationImages();		
				if (!locationid.isEmpty()) {
					Location loc = new Location();
					loc.setId(Integer.parseInt(locationid));
					locimg.setLocation(loc);
				}
				FormDataBodyPart fdbp = listfile.get(i);
				String fileName = fdbp.getContentDisposition().getFileName();
				if (fileName.isEmpty())
					continue;
				locimg.setOri_image(FileUpload.upload(fdbp, fileName,
						"location_images", request));
				locationImagesService.save(locimg);
			}
			return json.put("success", !b).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
