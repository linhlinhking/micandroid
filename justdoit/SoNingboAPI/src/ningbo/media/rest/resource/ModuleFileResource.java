package ningbo.media.rest.resource;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ningbo.media.bean.ImageInformation;
import ningbo.media.bean.ModuleFile;
import ningbo.media.bean.ModuleType;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.Tools;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileHashCode;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.ImageInformationService;
import ningbo.media.service.ModuleFileService;
import ningbo.media.service.ModuleTypeService;
import ningbo.media.service.SystemUserService;
import ningbo.media.service.ToolsService;

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
	private ModuleTypeService moduleTypeService;

	@Resource
	private ToolsService toolsService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private ImageInformationService imageInformationService;

	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addModuleFile(FormDataMultiPart form,
			@FormDataParam("file")
			InputStream uploadFile, @FormDataParam("file")
			FormDataContentDisposition fileDetail) {

		try {
			JSONObject json = new JSONObject();
			ModuleFile moduleFile = new ModuleFile();
			String key = form.getField("key").getValue();
			String userId = form.getField("userId").getValue();
			String toolId = form.getField("toolId").getValue();
			String typeId = form.getField("typeId").getValue();
			if (!StringUtils.hasText(key)) {
				json.put(Constant.CODE, JSONCode.KEYISNULL);
				return Response.ok(json.toString()).build();
			} else if (!key.equals(Constant.KEY)) {
				json.put(Constant.CODE, JSONCode.KEYINPUTINVALID);
				return Response.ok(json.toString()).build();
			}

			Tools tool = toolsService.get(Integer.valueOf(toolId));
			if (null == tool) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TOOL_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			ModuleType type = moduleTypeService.get(Integer.valueOf(typeId));
			if (null == type) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TYPE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			SystemUser u = systemUserService.get(Integer.valueOf(userId));
			if (null == u) {
				json.put(Constant.CODE, JSONCode.MODULEFILE_TYPE_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			String fileName = fileDetail.getFileName();
			StringBuffer sb = new StringBuffer();
			String tempPath = FileHashCode.makeTempFileDir();
			sb.append(tempPath).append(fileName);

			ImageInformation inforImage = new ImageInformation();
			Map<String, Object> m = FileHashCode.writeToFile(uploadFile, sb
					.toString());
			inforImage.setImageWidth(Double.valueOf(m.get(Constant.WIDTH)
					.toString()));
			inforImage.setImageHeight(Double.valueOf(m.get(Constant.HEIGHT)
					.toString()));
			inforImage.setImageSize(Long.valueOf(m.get(Constant.FILESIZE)
					.toString()));

			String uuid = String.valueOf(m.get(Constant.UUID));
			imageInformationService.save(inforImage);

			moduleFile.setFileName(fileName);
			moduleFile.setFileHash(uuid);
			moduleFile.setModuleType(type);
			moduleFile.setTools(tool);
			moduleFile.setCreateTime(new Date());
			moduleFile.setImageInfo(inforImage);

			Integer moduleFileId = moduleFileService.save(moduleFile);
			json.put(Constant.CODE, JSONCode.SUCCESS);
			json.put(Constant.FILEID, moduleFileId);
			return Response.ok(json.toString()).build();
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.INTERNAL_SERVER_ERROR, ex
					.getMessage());
		}

	}

	@Path("/get/{userId}/{toolId}/{typeId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserHeadFile(@QueryParam("userId")
	Integer userId, @QueryParam("toolId")
	Integer toolId, @QueryParam("typeId")
	Integer typeId) {
		
		return null;
	}

}
