package ningbo.media.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtil {

	private static Logger logger = LoggerFactory
			.getLogger(FileUploadUtil.class);

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
		String realPath = request.getSession().getServletContext()
				.getRealPath("");
		if (isTemp) {
			buffer.append(realPath).append(FILE_SEPARATOR)
					.append(Constant.FOLDER).append(FILE_SEPARATOR)
					.append(Constant.TEMP).append(FILE_SEPARATOR);
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
		sb.append(Constant.FOLDER).append(FILE_SEPARATOR)
				.append(uuid.substring(0, 4)).append(FILE_SEPARATOR)
				.append(uuid.substring(4, 8)).append(FILE_SEPARATOR)
				.append(uuid.substring(8, 12)).append(FILE_SEPARATOR);
		return sb.toString();
	}

	public static String copy(InputStream inputstream, String newPath) {
		try {
			OutputStream out = new FileOutputStream(new File(newPath));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return newPath;
	}

	/**
	 * delete file
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			File myDelFile = new File(filePathAndName);

			myDelFile.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * delete file
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName,
			HttpServletRequest request) {
		try {
			String realPath = request.getSession().getServletContext()
					.getRealPath("");
			StringBuffer temp = new StringBuffer();
			String path = FileUploadUtil.getUuidPath(filePathAndName);
			temp.append(realPath).append(File.separator).append(path)
					.append(filePathAndName.substring(12));

			File myDelFile = new File(temp.toString());

			myDelFile.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	 * @param path 文件目录
	 * 
	 * @param oldname 原来的文件名
	 * 
	 * @param newname 新文件名
	 */
	public static String renameFile(String path, String oldname) {
		String newname = String.valueOf(System.currentTimeMillis());
		if (!oldname.equals(newname)) {
			File oldfile = new File(path + File.separator + oldname);
			if (!oldfile.exists()) {
				logger.error("oldfile is not exists.");
				return null;
			}
			String ext = oldname.substring(oldname.lastIndexOf("."));
			newname = newname + ext ;
			File newfile = new File(path + File.separator + newname);

			oldfile.renameTo(newfile);
			
			return newname;
		} else {
			logger.error("oldname the same newname");
			return null ;
		}
	}

	//public static void main(String args[]) {
	//	String path = "/Users/ning/images";
	//	String oldname = "1348046218549.jpg";
	//	System.out.println(renameFile(path, oldname));
	//}
}
