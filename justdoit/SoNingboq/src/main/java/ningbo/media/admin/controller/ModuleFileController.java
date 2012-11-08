package ningbo.media.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.bean.ModuleFile;
import ningbo.media.bean.enums.DirectoryType;
import ningbo.media.core.web.BaseController;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileHashCode;
import ningbo.media.rest.util.FileUploadUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/file")
public class ModuleFileController extends BaseController<ModuleFile> {

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	@RequestMapping(value = "/upload/{type}")
	public @ResponseBody
	String uploadMultipleFile(HttpServletRequest request,
			@PathVariable("type") String type) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			JSONArray jsonArry = new JSONArray();
			DirectoryType dType = null ;
			
			if(type.equals(Constant.EVENTDIR)){
				dType = DirectoryType.EVENTDIR ;
			}else if(type.equals(Constant.PRODUCTDIR)){
				dType = DirectoryType.PRODUCTDIR ;
			}else if(type.equals(Constant.EVENTICONDIR)){
				dType = DirectoryType.EVENTICONDIR ;
			}else if(type.equals(Constant.EVENTPOSTERDIR)){
				dType = DirectoryType.EVENTPOSTERDIR ;
			}else if(type.equals(Constant.TEMP)){
				dType = DirectoryType.TEMPDIR ;
			}else if(type.equals(Constant.USERDIR)){
				dType = DirectoryType.USERDIR ;
			}else{
				dType = DirectoryType.UPLOAD ;
			}
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();
				String fileName = mf.getOriginalFilename();
				long fileSize = mf.getSize();
				/** 获取文件的UUID */
				String uuid = FileHashCode.getFileMD5(mf.getInputStream());
				/** 根据UUID生成目录 */
				String newDir = FileUploadUtil.makeFileDir(uuid,
						multipartRequest, dType, false);
				/** 获取文件的绝对路径 */
				StringBuffer buffer = new StringBuffer();

				buffer.append(newDir).append(uuid.substring(12))
						.append(FileUploadUtil.getFileExt(fileName));
				/** 写入文件 */
				FileUploadUtil.copy(mf.getInputStream(), buffer.toString());
				/** 生成网络路径 */
				StringBuffer netUrl = new StringBuffer();
				netUrl.append("http://192.168.0.102:9000/upload/events/")
						.append(FileUploadUtil.getUuidPath(uuid))
						.append(uuid.substring(12))
						.append(FileUploadUtil.getFileExt(fileName));
				JSONObject obj = new JSONObject();
				obj.put("name", fileName);
				obj.put("size", fileSize);
				obj.put("url", netUrl);
				obj.put("thumbnail_url", netUrl);
				obj.put("delete_url", netUrl);
				obj.put("uuid", uuid);
				obj.put("delete_type", "GET");

				jsonArry.put(obj);

			}

			return jsonArry.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
