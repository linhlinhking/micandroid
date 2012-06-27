package ningbo.media.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

public class FileUploadUtil {

	private static final String FILE_SEPARATOR = File.separator;

	private FileUploadUtil() {
	}

	/**
	 * example:/Users/ning/upload/1180/8011/8099/
	 * 
	 * @param uuid
	 * @param request
	 * 
	 * @return path
	 */
	public static String makeFileDir(String uuid, HttpServletRequest request,
			boolean isTemp) {
		StringBuffer buffer = new StringBuffer();
		String realPath = request.getSession().getServletContext().getRealPath(
				"");
		if (isTemp) {
			buffer.append(realPath).append(FILE_SEPARATOR).append(
					Constant.FOLDER).append(FILE_SEPARATOR).append(
					Constant.TEMP).append(FILE_SEPARATOR);
		} else {
			String path = getUuidPath(uuid);
			buffer.append(realPath).append(FILE_SEPARATOR).append(path);
		}

		// 如果目录不存在,生成文件目录
		File f = new File(buffer.toString());
		if (!f.exists()) {
			f.mkdirs();
		}
		return buffer.toString();
	}

	public static String getUuidPath(String uuid) {
		StringBuffer sb = new StringBuffer();
		sb.append(Constant.FOLDER).append(FILE_SEPARATOR).append(
				uuid.substring(0, 4)).append(FILE_SEPARATOR).append(
				uuid.substring(4, 8)).append(FILE_SEPARATOR).append(
				uuid.substring(8, 12)).append(FILE_SEPARATOR);
		return sb.toString();
	}
	
	public static String copy(InputStream inputstream,String newPath){
		try{
			OutputStream out = new FileOutputStream(new File(newPath));
			int read = 0;
			byte[] bytes = new byte[1024] ;
			while((read = inputstream.read(bytes)) != -1){
				out.write(bytes,0,read) ;
			}
			
			out.close();
		}catch(Exception ex){
			ex.printStackTrace() ;
			return null ;
		}
		return newPath ;
	}

}
