package ningbo.media.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;

import com.sun.jersey.multipart.FormDataBodyPart;

public class FileUpload {

	private static final char FILE_SEPARATOR = File.separatorChar;

	/**
	 * generate file path
	 * 
	 * @param folderName
	 * @return
	 */
	public static String generateFolderName(String folderName) {
		String date = DateUtil.date2String("yyyy-MM-dd");
		String strTime = String.valueOf(System.currentTimeMillis());
		StringBuffer buffer = new StringBuffer();
		buffer.append(folderName).append(FILE_SEPARATOR)
				.append(date.substring(0, 4)).append(FILE_SEPARATOR)
				.append(date.substring(5, 7)).append(FILE_SEPARATOR)
				.append(strTime.substring(0, 4)).append(FILE_SEPARATOR)
				.append(strTime.substring(4, 8)).append(FILE_SEPARATOR)
				.append(strTime.substring(8));

		return buffer.toString();
	}

	public static String makeTempDir(HttpServletRequest request) {
		StringBuffer temp = new StringBuffer();
		temp.append(request.getSession().getServletContext().getRealPath(""))
				.append(FILE_SEPARATOR).append(Constant.TEMP)
				.append(FILE_SEPARATOR);

		File f = new File(temp.toString());
		if (!f.exists()) {
			f.mkdirs();
		}
		return temp.toString();
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
			HttpServletRequest request) throws IOException {
		/** 创建特定要求的目录 */
		String filePath = generateFolderName(Constant.FOLDER);
		StringBuffer temp = new StringBuffer();
		temp.append(request.getSession().getServletContext().getRealPath(""))
				.append(FILE_SEPARATOR).append(filePath);
		File dic = new File(temp.toString());
		if (!dic.exists()) {
			dic.mkdirs();
		}
		/** 获取文件的绝对路径 */
		temp.append(FILE_SEPARATOR).append(fileName);

		/** 文件上传 */
		InputStream in = part.getValueAs(InputStream.class);
		OutputStream os = new FileOutputStream(temp.toString());

		byte[] buffer = new byte[1024];
		int length = 0;
		while (-1 != (length = in.read(buffer, 0, 1024))) {
			os.write(buffer, 0, length);
		}
		os.close();
		in.close();

		/** 返回文件的上下文路径 */
		StringBuffer temp2 = new StringBuffer();
		temp2.append(filePath).append(FILE_SEPARATOR).append(fileName);
		return temp2.toString();
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
	public static String uploadLocation(FormDataBodyPart part, String fileName,
			HttpServletRequest request) {
		try {
			/** 创建临时的目录 */
			StringBuffer temp = new StringBuffer();
			temp.append(
					request.getSession().getServletContext().getRealPath(""))
					.append(FILE_SEPARATOR).append(Constant.FOLDER);

			File dic = new File(temp.toString());
			if (!dic.exists()) {
				dic.mkdirs();
			}
			/** 获取文件的绝对路径 */
			temp.append(FILE_SEPARATOR).append(fileName);

			/** 文件上传 */
			InputStream in = part.getValueAs(InputStream.class);
			OutputStream os = new FileOutputStream(temp.toString());

			byte[] buffer = new byte[1024];
			int length = 0;
			while (-1 != (length = in.read(buffer, 0, 1024))) {
				os.write(buffer, 0, length);
			}
			os.close();
			in.close();

			String uuid = FileHashCode
					.writeBase64File(request, temp.toString());

			return uuid;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
