package ningbo.media.rest.service;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import ningbo.media.bean.ImageFile;
import ningbo.media.bean.ImageInformation;
import ningbo.media.bean.LinkUserAndImage;
import ningbo.media.bean.Location;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileHashCode;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.ImageFileService;
import ningbo.media.service.ImageInformationService;
import ningbo.media.service.LocationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("/image")
@Component
@Scope("request")
public class ImageFileRest {

	@Resource
	private ImageFileService imageFileService;

	@Resource
	private ImageInformationService imageInformationService;

	@Resource
	private LocationService locationService ;

	@SuppressWarnings("unused")
	@Context
	private UriInfo uriInfo;

	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String addImageFile(FormDataMultiPart form,
			@FormDataParam("file") InputStream uploadFile,
			@FormDataParam("file") FormDataContentDisposition fileDetail)
			throws JSONException, URISyntaxException {

		JSONObject json = new JSONObject();
		String key = form.getField("key").getValue();
		String userId = form.getField("userId").getValue();
		String deviceId = form.getField("deviceId").getValue();
		String locationId = form.getField("locationId").getValue() ;

		if (!StringUtils.hasText(key)) {
			json.put(Constant.CODE, JSONCode.KEYISNULL);
			return json.toString();
		} else if (!key.equals(Constant.KEY)) {
			json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
			return json.toString();
		}
		if (!(StringUtils.hasText(userId))
				&& !(StringUtils.hasText(deviceId))) {
			json.put(Constant.CODE, JSONCode.NOINPUT);
			return json.toString();
		}
		if (!StringUtils.hasText(fileDetail.getFileName())) {
			json.put(Constant.CODE, JSONCode.NOUPLOADFILE);
			return json.toString();
		}
		if(!StringUtils.hasText(locationId)){
			json.put(Constant.CODE, JSONCode.LOCATIONID_NOINPUT);
			return json.toString();
		}

		Location loc = locationService.get(Integer.valueOf(locationId)) ;
		if(loc == null){
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION) ;
			return json.toString() ;
		}
		//Setting Path.
		String fileName = fileDetail.getFileName();
		StringBuffer sb = new StringBuffer();
		String tempPath = FileHashCode.makeTempFileDir();
		sb.append(tempPath).append(fileName);

		// save image information.
		ImageInformation inforImage = new ImageInformation();
		Map<String, Object> m = FileHashCode.writeToFile(uploadFile,
				sb.toString());
		inforImage.setWidth(Double.valueOf(m.get(Constant.WIDTH)
				.toString()));
		inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
				.toString()));
		inforImage.setSize(Long.valueOf(m.get(Constant.FILESIZE)
				.toString()));

		String uuid = String.valueOf(m.get(Constant.UUID));
		//Save Image Expand Information.
		imageInformationService.save(inforImage);
		ImageFile imageFile = new ImageFile();
		imageFile.setFileName(fileName);
		imageFile.setUploadTime(new Date());
		imageFile.setImageInfo(inforImage);
		imageFile.setUuid(uuid);
		imageFile.setLocation(loc) ;
		// Save Image File.
		Integer fileId = imageFileService.save(imageFile);
		json.put(Constant.CODE, JSONCode.SUCCESS);
		json.put(Constant.FILEID, fileId) ;
		return json.toString();
	}

	@Path("/get")
	@GET
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public List<LinkUserAndImage> getImageFile() {
		//
		return null;
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteImageFile(@FormParam("fileId") String fileId,
			@FormParam("key") String key) throws JSONException {
		JSONObject json = new JSONObject();
		if (!StringUtils.hasText(fileId)) {
			json.put(Constant.CODE, JSONCode.DELETE_FILE_ISNULL);
			return json.toString();
		}
		try {
			ImageFile imageFile = imageFileService.get(Integer.valueOf(fileId));
			// delete ext information.
			imageInformationService.delete(imageFile.getImageInfo());
			String uuid = imageFile.getUuid();
			StringBuffer sb = new StringBuffer() ;
			sb.append(FileHashCode.getUuidPath(uuid)).append(uuid.substring(12)) ;
			System.out.println(sb.toString());
			// delete file
			FileHashCode.delFile(sb.toString());
			// delete file
			imageFileService.delete(imageFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.THROWEXCEPTION);
			return json.toString();
		}
		json.put(Constant.CODE, JSONCode.SUCCESS);
		return json.toString();
	}

}
