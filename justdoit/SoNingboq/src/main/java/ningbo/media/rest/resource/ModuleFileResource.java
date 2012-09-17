package ningbo.media.rest.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

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
import ningbo.media.util.StringUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imgscalr.Scalr;
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

	@Path("/option")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fileUploadOption(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException,
			JSONException {
		String fileUploadPath = FileUpload.makeTempDir(request);
		JSONObject json = new JSONObject();
		if (request.getParameter("getfile") != null
				&& !request.getParameter("getfile").isEmpty()) {
			File file = new File(fileUploadPath,
					request.getParameter("getfile"));
			if (file.exists()) {
				int bytes = 0;
				ServletOutputStream op = response.getOutputStream();
				response.setContentType(getMimeType(file));
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "inline; filename=\""
						+ file.getName() + "\"");

				byte[] bbuf = new byte[1024];
				DataInputStream in = new DataInputStream(new FileInputStream(
						file));

				while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
					op.write(bbuf, 0, bytes);
				}

				in.close();
				op.flush();
				op.close();
			}
		} else if (request.getParameter("delfile") != null
				&& !request.getParameter("delfile").isEmpty()) {
			File file = new File(fileUploadPath,
					request.getParameter("delfile"));
			if (file.exists()) {
				file.delete(); // TODO:check and report success
			}
		} else if (request.getParameter("getthumb") != null
				&& !request.getParameter("getthumb").isEmpty()) {
			File file = new File(fileUploadPath,
					request.getParameter("getthumb"));
			if (file.exists()) {
				String mimetype = getMimeType(file);
				if (mimetype.endsWith("png") || mimetype.endsWith("jpeg")
						|| mimetype.endsWith("gif")) {
					BufferedImage im = ImageIO.read(file);
					if (im != null) {
						BufferedImage thumb = Scalr.resize(im, 75);
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						if (mimetype.endsWith("png")) {
							ImageIO.write(thumb, "PNG", os);
							response.setContentType("image/png");
						} else if (mimetype.endsWith("jpeg")) {
							ImageIO.write(thumb, "jpg", os);
							response.setContentType("image/jpeg");
						} else {
							ImageIO.write(thumb, "GIF", os);
							response.setContentType("image/gif");
						}
						ServletOutputStream srvos = response.getOutputStream();
						response.setContentLength(os.size());
						response.setHeader("Content-Disposition",
								"inline; filename=\"" + file.getName() + "\"");
						os.writeTo(srvos);
						srvos.flush();
						srvos.close();
					}
				}
			} // TODO: check and report success
		} else {
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, "call POST with multipart form data");
			return Response.ok(json.toString()).build();
		}
		json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
		return Response.ok(json.toString()).build();
	}

	@SuppressWarnings("unchecked")
	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response multipleFileUpload(@Context HttpServletRequest request)
			throws JSONException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException(
					"Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}
		ServletFileUpload uploadHandler = new ServletFileUpload(
				new DiskFileItemFactory());

		// List<FileData> lists = new ArrayList<FileData>();
		UriBuilder getFileUrl = UriBuilder.fromPath(
				"/resource/modulefile/option?getfile=").path("");
		UriBuilder getThumUrl = UriBuilder.fromPath(
				"/resource/modulefile/option?getthumb=").path("");
		UriBuilder delFileUrl = UriBuilder.fromPath(
				"/resource/modulefile/option?delfile=").path("");
		// String linkUrl = "http://localhost:9000" ;
		String linkUrl = Constant.API_URL;
		String tmpPath = FileUpload.makeTempDir(request);
		JSONArray jsonArry = new JSONArray();

		try {
			List<FileItem> items = uploadHandler.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField() && item.getSize() > 0) {
					File file = new File(tmpPath, item.getName());
					item.write(file);

					JSONObject obj = new JSONObject();
					obj.put("name", item.getName());
					obj.put("size", item.getSize());
					obj.put("url", linkUrl + getFileUrl.build("").getPath()
							+ item.getName());
					obj.put("thumbnail_url", linkUrl
							+ getThumUrl.build("").getPath() + item.getName());
					obj.put("delete_url", linkUrl
							+ delFileUrl.build("").getPath() + item.getName());
					obj.put("delete_type", "GET");

					jsonArry.put(obj);
					// FileData jsono = new FileData();
					// jsono.setName(item.getName());
					// jsono.setSize(String.valueOf(item.getSize()));
					// jsono.setUrl(headUrl.toString()
					// + getFileUrl.build("").getPath() + item.getName());
					// jsono.setThumbnail_url(headUrl.toString()
					// + getThumUrl.build("").getPath() + item.getName());
					// jsono.setDelete_url(headUrl.toString()
					// + delFileUrl.build("").getPath() + item.getName());
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
		// GenericEntity<JSONArray> entiry = new GenericEntity<JSONArray>(
		// jsonArry) {
		// };
		return Response.ok(jsonArry.toString()).build();
	}

	@Path("/save")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveModuleFile(@FormParam("fileNames") String fileNames,
			@FormParam("locationId") String locationId,
			@FormParam("userId") String userId, @FormParam("key") String key,
			@Context HttpServletRequest request) throws JSONException {
		JSONObject json = new JSONObject();
		List<SystemUser> listUsers = new ArrayList<SystemUser>();
		ModuleFile moduleFile = new ModuleFile();
		List<Location> listLocations = new ArrayList<Location>();
		UserModuleFiles files = new UserModuleFiles();

		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				return Response.ok(json.toString()).build();
			}

			SystemUser u = systemUserService.get(Constant.MD5_FIELD, userId);
			if (null == u) {
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_NOEXISTS);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();
			}
			Location loc = locationService.get(Constant.MD5_FIELD, locationId);
			if (null == loc) {
				json.put(Constant.MESSAGE, JSONCode.MSG_USER_NOEXISTS);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();
			}
			if ((!"".equals(fileNames)) && (fileNames.length() > 0)) {
				String[] arry = StringUtil.parseString(fileNames,
						Constant.STRING_REGEX);

				for (int i = 0, j = arry.length; i < j; i++) {
					String fileName = arry[i];
					StringBuffer buffer = new StringBuffer();
					buffer.append(Constant.API_URL).append(File.separator)
							.append(Constant.TEMP).append(File.separator);
					buffer.append(fileName);
					InputStream in = new FileInputStream(buffer.toString());

					ImageInformation inforImage = new ImageInformation();
					Map<String, Object> m = FileHashCode.writeToFile(in,
							buffer.toString());

					inforImage.setWidth(Double.valueOf(m.get(Constant.WIDTH)
							.toString()));
					inforImage.setHeight(Double.valueOf(m.get(Constant.HEIGHT)
							.toString()));
					inforImage.setSize(Long.valueOf(m.get(Constant.FILESIZE)
							.toString()));
					if (null != m.get(Constant.LATITUDE)) {
						inforImage.setLatitude(Double.valueOf(m.get(
								Constant.LATITUDE).toString()));
					}
					if (null != m.get(Constant.LONGITUDE)) {
						inforImage.setLongitude(Double.valueOf(m.get(
								Constant.LONGITUDE).toString()));
					}
					if (null != m.get(Constant.TAKE_PHOTO_DATE)) {
						inforImage.setTakePhotoDate(m.get(
								Constant.TAKE_PHOTO_DATE).toString());
					}
					String uuid = String.valueOf(m.get(Constant.UUID));
					imageInformationService.save(inforImage);

					moduleFile.setFileName(fileName);
					moduleFile.setFileHash(uuid);
					moduleFile.setCreateTime(new Date());
					moduleFile.setImageInfo(inforImage);
					moduleFile.setLocations(listLocations);
					moduleFile.setSystemUsers(listUsers);

					Integer moduleFileId = moduleFileService.save(moduleFile);

					if ((!"".equals(userId)) && (null != userId)) {
						files.setMd5Value(userId);
						files.setFileId(moduleFileId);
						files.setUploadedDate(new Date());

						userModuleFilesService.save(files);
					}

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, JSONCode.MSG_UPLOAD_FILE_EXCEPTION);
			return Response.ok(json.toString()).build();
		}
		json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
		return Response.ok(json.toString()).build();
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

	private String getMimeType(File file) {
		String mimetype = "";
		if (file.exists()) {
			if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
				mimetype = "image/png";
			} else {
				javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
				mimetype = mtMap.getContentType(file);
			}
		}
		// System.out.println("mimetype: " + mimetype);
		return mimetype;
	}

	private String getSuffix(String filename) {
		String suffix = "";
		int pos = filename.lastIndexOf('.');
		if (pos > 0 && pos < filename.length() - 1) {
			suffix = filename.substring(pos + 1);
		}
		// System.out.println("suffix: " + suffix);
		return suffix;
	}

}
