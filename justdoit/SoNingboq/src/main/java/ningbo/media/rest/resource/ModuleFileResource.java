package ningbo.media.rest.resource;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ningbo.media.bean.ImageInformation;
import ningbo.media.bean.Location;
import ningbo.media.bean.ModuleFile;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.UserModuleFiles;
import ningbo.media.rest.dto.ModuleFileData;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileHashCode;
import ningbo.media.rest.util.FileUpload;
import ningbo.media.rest.util.FileUploadUtil;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.ImageInformationService;
import ningbo.media.service.LocationService;
import ningbo.media.service.ModuleFileService;
import ningbo.media.service.SystemUserService;
import ningbo.media.service.UserModuleFilesService;
import ningbo.media.util.Base64Image;
import ningbo.media.util.MagickImageScale;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
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

	@Resource
	private UserModuleFilesService userModuleFilesService;

	@Path("/show/{fileId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getModuleFile(@PathParam("fileId") String fileId) {
		try {
			JSONObject json = new JSONObject();
			if (null == fileId || fileId.length() < 0) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_FILEID_ERROR);
				return Response.ok(json.toString()).build();
			}
			return Response
					.ok(moduleFileService.getModuleFileById(Integer
							.valueOf(fileId))).build();
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR,
					ex.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response multipleFileUpload(@Context HttpServletRequest request)
			throws JSONException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException(
					"Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}
		ServletFileUpload uploadHandler = new ServletFileUpload(
				new DiskFileItemFactory());
		System.out.println(request.getSession().getServletContext()
				.getRealPath(""));
		// List<FileData> lists = new ArrayList<FileData>();
		JSONArray json = new JSONArray();
		String tmpPath = FileUpload.makeTempDir(request);
		try {
			List<FileItem> items = uploadHandler.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField() && item.getSize() > 0) {
					File file = new File(tmpPath, item.getName());
					item.write(file);
					JSONObject jsono = new JSONObject();
					jsono.put("name", item.getName());
					jsono.put("size", item.getSize());
					jsono.put("url", "upload?getfile=" + item.getName());
					jsono.put("thumbnail_url",
							"upload?getthumb=" + item.getName());
					jsono.put("delete_url", "upload?delfile=" + item.getName());
					jsono.put("delete_type", "GET");
					json.put(jsono);
					// FileData jsono = new FileData();
					// jsono.setName(item.getName());
					// jsono.setSize(String.valueOf(item.getSize()));
					// jsono.setUrl("pload?getfile=" + item.getName());
					// jsono.setThumbnail_url("upload?getthumb=" +
					// item.getName());
					// jsono.setDelete_url("upload?delfile=" + item.getName());
					// jsono.setDelete_type("GET");
					// lists.add(jsono);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject errorJSON = new JSONObject();
			errorJSON.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			errorJSON.put(Constant.MESSAGE, JSONCode.MSG_UPLOAD_FILE_EXCEPTION);
			return Response.ok(errorJSON.toString()).build();
		}
		GenericEntity<JSONArray> entiry = new GenericEntity<JSONArray>(json) {
		};
		return Response.ok(entiry).build();
	}

	@Path("/user/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addModuleFile(FormDataMultiPart form,
			@FormDataParam("file") InputStream uploadFile,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

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

			Map<String, Object> m = FileHashCode.writeToFile(uploadFile,
					sb.toString());

			inforImage.setWidth(Double
					.valueOf(m.get(Constant.WIDTH).toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long
					.valueOf(m.get(Constant.FILESIZE).toString()));

			if (null != m.get(Constant.LATITUDE)) {
				inforImage.setLatitude(Double.valueOf(m.get(Constant.LATITUDE)
						.toString()));
			}
			if (null != m.get(Constant.LONGITUDE)) {
				inforImage.setLongitude(Double.valueOf(m
						.get(Constant.LONGITUDE).toString()));
			}
			if (null != m.get(Constant.TAKE_PHOTO_DATE)) {
				inforImage.setTakePhotoDate(m.get(Constant.TAKE_PHOTO_DATE)
						.toString());
			}

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
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR,
					ex.getMessage());
		}

	}

	@Path("/location/base64/upload")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLoctionFileByBase64(@FormParam("key") String key,
			@FormParam("locationId") String locationId,
			@FormParam("imageBase64") String imageBase64,
			@FormParam("md5_value") String md5Value,
			@Context HttpServletRequest request) {
		try {
			JSONObject json = new JSONObject();
			List<Location> listLocations = new ArrayList<Location>();
			ModuleFile moduleFile = new ModuleFile();
			UserModuleFiles files = new UserModuleFiles();

			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			Location loc = locationService.queryLocationByMd5(locationId);
			if (null == loc) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TYPE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}
			listLocations.add(loc);

			String fileName = String.valueOf(System.currentTimeMillis());
			StringBuffer sb = new StringBuffer();
			String tempPath = FileUploadUtil.makeFileDir(null, request, true);
			sb.append(tempPath).append(fileName);

			String tempBase64 = imageBase64.replaceAll(" ", "+");
			boolean flag = Base64Image.generateImage(tempBase64, sb.toString());

			if (!flag) {
				File file = new File(sb.toString());
				file.delete();
				json.put(Constant.CODE, JSONCode.MODULEFILE_BASE64_INVALID);
				return Response.ok(json.toString()).build();
			}

			ImageInformation inforImage = new ImageInformation();
			Map<String, Object> m = FileHashCode.writeToFile(request,
					sb.toString());

			inforImage.setWidth(Double
					.valueOf(m.get(Constant.WIDTH).toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long
					.valueOf(m.get(Constant.FILESIZE).toString()));
			if (null != m.get(Constant.LATITUDE)) {
				inforImage.setLatitude(Double.valueOf(m.get(Constant.LATITUDE)
						.toString()));
			}
			if (null != m.get(Constant.LONGITUDE)) {
				inforImage.setLongitude(Double.valueOf(m
						.get(Constant.LONGITUDE).toString()));
			}
			if (null != m.get(Constant.TAKE_PHOTO_DATE)) {
				inforImage.setTakePhotoDate(m.get(Constant.TAKE_PHOTO_DATE)
						.toString());
			}

			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);
			moduleFile.setLocations(listLocations);

			Integer moduleFileId = moduleFileService.save(moduleFile);

			if ((!"".equals(md5Value)) && (null != md5Value)) {
				files.setMd5Value(md5Value);
				files.setFileId(moduleFileId);
				files.setUploadedDate(new Date());

				userModuleFilesService.save(files);
			}

			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.FILEID, moduleFileId);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			// FileHashCode.delFile(filePathAndName)
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR,
					ex.getMessage());
		}

	}

	@Path("/location/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLoctionFile(FormDataMultiPart form,
			@FormDataParam("file") InputStream uploadFile,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		try {
			UserModuleFiles files = new UserModuleFiles();
			JSONObject json = new JSONObject();
			List<Location> listLocations = new ArrayList<Location>();
			ModuleFile moduleFile = new ModuleFile();
			String key = form.getField("key").getValue();
			String locationId = form.getField("locationId").getValue();// md5

			// 上传用户的md5Value
			String md5Value = form.getField("user_md5_value").getValue();

			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

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
			Map<String, Object> m = FileHashCode.writeToFile(uploadFile,
					sb.toString());

			inforImage.setWidth(Double
					.valueOf(m.get(Constant.WIDTH).toString()));
			inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setSize(Long
					.valueOf(m.get(Constant.FILESIZE).toString()));
			if (null != m.get(Constant.LATITUDE)) {
				inforImage.setLatitude(Double.valueOf(m.get(Constant.LATITUDE)
						.toString()));
			}
			if (null != m.get(Constant.LONGITUDE)) {
				inforImage.setLongitude(Double.valueOf(m
						.get(Constant.LONGITUDE).toString()));
			}
			if (null != m.get(Constant.TAKE_PHOTO_DATE)) {
				inforImage.setTakePhotoDate(m.get(Constant.TAKE_PHOTO_DATE)
						.toString());
			}
			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);
			moduleFile.setLocations(listLocations);

			Integer moduleFileId = moduleFileService.save(moduleFile);

			if ((!"".equals(md5Value)) && (null != md5Value)) {
				files.setMd5Value(md5Value);
				files.setFileId(moduleFileId);
				files.setUploadedDate(new Date());

				userModuleFilesService.save(files);
			}

			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.FILEID, moduleFileId);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR,
					ex.getMessage());
		}

	}

	@Path("/user/file/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleFileData> getUserHeadFile(
			@PathParam("userId") String userId) {
		return moduleFileService.queryModuleFileByUserHeader(Integer
				.valueOf(userId));
	}

	@Path("/location/file/{locationId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleFileData> getLocationFile(
			@PathParam("locationId") String locationId) {
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
	public Response resizeImageByCustomer(@PathParam("fileId") Integer fileId,
			@PathParam("width") Integer width,
			@PathParam("height") Integer height) {
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
			destBuffer.append(filePath).append(fileHash.substring(12))
					.append("-").append(width).append("x").append(height);

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
	public Response deleteFileByUploader(@FormParam("key") String key,
			@FormParam("user_md5_value") String md5Value,
			@FormParam("fileId") String fileId,
			@Context HttpServletRequest request) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			UserModuleFiles flag = userModuleFilesService
					.getUserModuleFilesByUserId(Integer.valueOf(fileId),
							md5Value);

			if (null != flag) {
				ModuleFile tempFile = moduleFileService.get(Integer
						.valueOf(fileId));
				String uuid = "";

				if (null != tempFile) {
					ImageInformation infor = tempFile.getImageInfo();
					uuid = tempFile.getFileHash();

					if ((!"0".equals(uuid)) && (null != uuid)) {
						FileUploadUtil.delFile(uuid, request);
					}

					moduleFileService.delete(tempFile);
					imageInformationService.delete(infor);
				}
				userModuleFilesService.delete(flag);
				json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
				json.put(Constant.MESSAGE,
						JSONCode.MSG_MODULEFILE_DELETE_SUCCESS);
				return Response.ok(json.toString()).build();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
		json.put(Constant.MESSAGE, JSONCode.MSG_MODULEFILE_DELETE_FAIL);
		return Response.ok(json.toString()).build();
	}

}
