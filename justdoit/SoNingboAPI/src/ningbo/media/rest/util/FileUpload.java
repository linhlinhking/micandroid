package ningbo.media.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import com.sun.jersey.multipart.FormDataBodyPart;

public class FileUpload {

	/**
	 * generate file path
	 * 
	 * @param folderName
	 * @return
	 */
	public static String generateFolderName(String folderName) {
		String date = DateUtil.date2String("yyyy-MM-dd");
		folderName += "/" + date.substring(0, 7) + "/" + date.substring(8);
		return folderName;
	}

	/**
	 * generate file name
	 * 
	 * @param filename
	 * @return
	 */
	public static String generateFileName(String ext) {
		return System.currentTimeMillis() + "." + ext;
	}

	public static String createFolder(String folder, HttpServletRequest request) {
		folder = request.getSession().getServletContext().getRealPath("") + "/"
				+ folder + "/";
		folder += DateUtil.date2String("yyyyMMdd") + "/";
		File dic = new File(folder);
		if (!dic.exists()) {
			dic.mkdirs();
		}
		return folder;
	}

	/**
	 * get the extension of file
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		return ext;
	}

	/**
	 * upload files
	 * 
	 * @param part
	 * @param fileName
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static String upload(FormDataBodyPart part, String fileName,
			HttpServletRequest request) throws IOException {
		String ext = FileUpload.getFileExtension(fileName).toLowerCase();
		if (!"jpg".equals(ext) && !"png".equals(ext)) {
			return "";
		}
		String genereateFileName = FileUpload.generateFileName(ext);
		String folder = Constant.FOLDER;
		folder = createFolder(folder, request);
		InputStream in = part.getValueAs(InputStream.class);
		OutputStream os = new FileOutputStream(folder + "/" + genereateFileName);

		byte[] buffer = new byte[1024];
		int length = 0;
		while (-1 != (length = in.read(buffer, 0, 1024))) {
			os.write(buffer, 0, length);
		}
		os.close();
		in.close();
		String path = Constant.FOLDER + "/" + DateUtil.date2String("yyyyMMdd")
				+ "/" + genereateFileName;
		return path;
	}

	/**
	 * upload files
	 * 
	 * @param part
	 * @param fileName
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String upload(FormDataBodyPart part, String fileName,
			String foldername, HttpServletRequest request) throws IOException {
		String ext = FileUpload.getFileExtension(fileName).toLowerCase();
		if (!"jpg".equals(ext) && !"png".equals(ext)) {
			return "";
		}
		String genereateFileName = FileUpload.generateFileName(ext);
		String folder = Constant.FOLDER + "/" + foldername;
		folder = createFolder(folder, request);
		InputStream in = part.getValueAs(InputStream.class);
		OutputStream os = new FileOutputStream(folder + "/" + genereateFileName);

		byte[] buffer = new byte[1024];
		int length = 0;
		while (-1 != (length = in.read(buffer, 0, 1024))) {
			os.write(buffer, 0, length);
		}
		os.close();
		in.close();
		String path = Constant.FOLDER + "/" + foldername + "/"
				+ DateUtil.date2String("yyyyMMdd") + "/" + genereateFileName;
		return path;
	}


}
