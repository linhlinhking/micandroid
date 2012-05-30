package ningbo.media.rest.resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ningbo.media.bean.ImageInformation;
import ningbo.media.bean.Location;
import ningbo.media.bean.ModuleFile;
import ningbo.media.bean.SystemUser;
import ningbo.media.rest.dto.ModuleFileData;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileHashCode;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.ImageInformationService;
import ningbo.media.service.LocationService;
import ningbo.media.service.ModuleFileService;
import ningbo.media.service.SystemUserService;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("/modulefile")
@Component
@Scope("request")
public class ModuleFileResource {

	@Resource
	private ModuleFileService moduleFileService;

	@Resource
	private SystemUserService systemUserService;
	
	@Resource
	private LocationService locationService ;

	@Resource
	private ImageInformationService imageInformationService;

	@Path("/user/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addModuleFile(FormDataMultiPart form,
			@FormDataParam("file")
			InputStream uploadFile, @FormDataParam("file")
			FormDataContentDisposition fileDetail) {

		try {
			JSONObject json = new JSONObject();
			List<SystemUser> listUsers = new ArrayList<SystemUser>();
			ModuleFile moduleFile = new ModuleFile();
			String key = form.getField("key").getValue();
			String userId = form.getField("userId").getValue();
			
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			SystemUser u = systemUserService.get(Integer.valueOf(userId));
			if (null == u) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TYPE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			listUsers.add(u) ;

			String fileName = fileDetail.getFileName();
			StringBuffer sb = new StringBuffer();
			String tempPath = FileHashCode.makeTempFileDir();
			sb.append(tempPath).append(fileName);

			ImageInformation inforImage = new ImageInformation();
			Map<String, Object> m = FileHashCode.writeToFile(uploadFile, sb
					.toString());
			inforImage.setWidth(Double.valueOf(m.get(Constant.WIDTH)
					.toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long.valueOf(m.get(Constant.FILESIZE)
					.toString()));

			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);
			moduleFile.setSystemUsers(listUsers) ;

			Integer moduleFileId = moduleFileService.save(moduleFile);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.FILEID, moduleFileId);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR, ex
					.getMessage());
		}

	}
	
	
	
	
	
	@Path("/location/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLoctionFile(FormDataMultiPart form,
			@FormDataParam("file")
			InputStream uploadFile, @FormDataParam("file")
			FormDataContentDisposition fileDetail) {

		try {
			JSONObject json = new JSONObject();
			List<Location> listLocations = new ArrayList<Location>();
			ModuleFile moduleFile = new ModuleFile();
			String key = form.getField("key").getValue();
			String locationId = form.getField("locationId").getValue();
			
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			Location loc = locationService.get(Integer.valueOf(locationId));
			if (null == loc) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TYPE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			listLocations.add(loc) ;

			String fileName = fileDetail.getFileName();
			StringBuffer sb = new StringBuffer();
			String tempPath = FileHashCode.makeTempFileDir();
			sb.append(tempPath).append(fileName);

			ImageInformation inforImage = new ImageInformation();
			Map<String, Object> m = FileHashCode.writeToFile(uploadFile, sb
					.toString());
			inforImage.setWidth(Double.valueOf(m.get(Constant.WIDTH)
					.toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long.valueOf(m.get(Constant.FILESIZE)
					.toString()));

			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);
			moduleFile.setLocations(listLocations) ;

			Integer moduleFileId = moduleFileService.save(moduleFile);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.FILEID, moduleFileId);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR, ex
					.getMessage());
		}

	}
	

	
	

	@Path("/user/file/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleFileData> getUserHeadFile(@PathParam("userId")
	String userId) {
		return moduleFileService.queryModuleFileByUserHeader(Integer.valueOf(userId));
	}
	
	
	@Path("/location/file/{locationId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleFileData> getLocationFile(@PathParam("locationId")
	String locationId) {
		return moduleFileService.queryModuleFileByLocation(Integer.valueOf(locationId)) ;
	}
	
	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleFileData> getAllFile(){
		return moduleFileService.queryAllFile();
	}

}
