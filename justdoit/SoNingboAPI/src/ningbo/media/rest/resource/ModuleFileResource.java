package ningbo.media.rest.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import ningbo.media.util.Base64Image;
import ningbo.media.util.MagickImageScale;

import org.json.JSONException;
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
	private LocationService locationService;

	@Resource
	private ImageInformationService imageInformationService;

	@Path("/show/{fileId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getModuleFile(@PathParam("fileId")
	String fileId) {
		try {
			JSONObject json = new JSONObject();
			if (null == fileId || fileId.length() < 0) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_FILEID_ERROR);
				return Response.ok(json.toString()).build();
			}
			return Response.ok(
					moduleFileService
							.getModuleFileById(Integer.valueOf(fileId)))
					.build();
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR, ex
					.getMessage());
		}

	}

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
			listUsers.add(u);

			String fileName = fileDetail.getFileName();
			StringBuffer sb = new StringBuffer();
			String tempPath = FileHashCode.makeTempFileDir();
			sb.append(tempPath).append(fileName);

			ImageInformation inforImage = new ImageInformation();

			Map<String, Object> m = FileHashCode.writeToFile(uploadFile, sb
					.toString());

			inforImage.setWidth(Double
					.valueOf(m.get(Constant.WIDTH).toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long
					.valueOf(m.get(Constant.FILESIZE).toString()));

			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);
			moduleFile.setSystemUsers(listUsers);

			Integer moduleFileId = moduleFileService.save(moduleFile);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.FILEID, moduleFileId);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR, ex
					.getMessage());
		}

	}

	@Path("/location/base64/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLoctionFileByBase64(FormDataMultiPart form) {

		try {
			JSONObject json = new JSONObject();
			List<Location> listLocations = new ArrayList<Location>();
			ModuleFile moduleFile = new ModuleFile();
			String key = form.getField("key").getValue();
			String locationId = form.getField("locationId").getValue();// md5
			String base64Value = form.getField("imageBase64").getValue() ;
																		// value
			//String uploaderId = form.getField("uploaderId").getValue();

			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			// SystemUser sysUser =
			// systemUserService.getSystemUserByMd5Value(uploaderId) ;
			//SystemUser sysUser = systemUserService.get(Integer
			//		.valueOf(uploaderId));
			//if (null == sysUser) {
			//	json.put(Constant.CODE, JSONCode.MODULEFILE_USER_NOEXISTS);
			//	return Response.ok(json.toString()).build();
			//}

			Location loc = locationService.queryLocationByMd5(locationId);
			if (null == loc) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TYPE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			listLocations.add(loc);

			String fileName = String.valueOf(System.currentTimeMillis());
			StringBuffer sb = new StringBuffer();
			String tempPath = FileHashCode.makeTempFileDir();
			sb.append(tempPath).append(fileName);
			
			boolean flag = Base64Image.generateImage(base64Value, sb.toString()) ;
			if(!flag){
				File file = new File(sb.toString());
				file.delete() ;
				json.put(Constant.CODE, JSONCode.MODULEFILE_BASE64_INVALID);
				return Response.ok(json.toString()).build();
			}
			
			InputStream uploadFile = new FileInputStream(sb.toString());
			
			ImageInformation inforImage = new ImageInformation();
			Map<String, Object> m = FileHashCode.writeToFile(uploadFile, sb
					.toString());

			inforImage.setWidth(Double
					.valueOf(m.get(Constant.WIDTH).toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long
					.valueOf(m.get(Constant.FILESIZE).toString()));

			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);
			moduleFile.setLocations(listLocations);
			//moduleFile.setUploaderId(uploaderId);

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
			String locationId = form.getField("locationId").getValue();// md5
																		// value
			//String uploaderId = form.getField("uploaderId").getValue();

			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			// SystemUser sysUser =
			// systemUserService.getSystemUserByMd5Value(uploaderId) ;
			//SystemUser sysUser = systemUserService.get(Integer
			//		.valueOf(uploaderId));
			//if (null == sysUser) {
			//	json.put(Constant.CODE, JSONCode.MODULEFILE_USER_NOEXISTS);
			//	return Response.ok(json.toString()).build();
			//}

			Location loc = locationService.queryLocationByMd5(locationId);
			if (null == loc) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TYPE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			listLocations.add(loc);

			String fileName = fileDetail.getFileName();
			StringBuffer sb = new StringBuffer();
			String tempPath = FileHashCode.makeTempFileDir();
			sb.append(tempPath).append(fileName);

			ImageInformation inforImage = new ImageInformation();
			Map<String, Object> m = FileHashCode.writeToFile(uploadFile, sb
					.toString());

			inforImage.setWidth(Double
					.valueOf(m.get(Constant.WIDTH).toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long
					.valueOf(m.get(Constant.FILESIZE).toString()));

			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);
			moduleFile.setLocations(listLocations);
			//moduleFile.setUploaderId(uploaderId);

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
		return moduleFileService.queryModuleFileByUserHeader(Integer
				.valueOf(userId));
	}

	@Path("/location/file/{locationId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleFileData> getLocationFile(@PathParam("locationId")
	String locationId) {
		return moduleFileService.queryModuleFileByLocation(locationId);
	}

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleFileData> getAllFile() {
		return moduleFileService.queryAllFile();
	}

	@Path("/resize/{fileId}/{width}/{height}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response resizeImageByCustomer(@PathParam("fileId")
	Integer fileId, @PathParam("width")
	Integer width, @PathParam("height")
	Integer height) {
		JSONObject json = new JSONObject();
		try {

			ModuleFile tempFile = null;
			if (null == fileId) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_FILEID_ERROR);
				return Response.ok(json.toString()).build();
			}
			tempFile = moduleFileService.get(fileId);
			if (null == tempFile) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_FILE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			String fileHash = tempFile.getFileHash();
			StringBuffer buffer = new StringBuffer();
			String filePath = FileHashCode.makeFileDir(fileHash);
			buffer.append(filePath).append(fileHash.substring(12));

			File srcFile = new File(buffer.toString());

			StringBuffer destBuffer = new StringBuffer();
			destBuffer.append(filePath).append(fileHash.substring(12)).append(
					"-").append(width).append("x").append(height);

			File destFile = new File(destBuffer.toString());

			MagickImageScale.resizeFix(srcFile, destFile, width, height);

			json.put(Constant.CODE, destBuffer.toString());
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			try {
				json.put(Constant.CODE, JSONCode.SERVER_EXCEPTION);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return Response.ok(json.toString()).build();
		}

	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFileByUploader(@FormParam("key")
	String key, @FormParam("uploaderId")
	String uploaderId, @FormParam("locationId")
	String locationId) {
		
		return null;
	}

}
