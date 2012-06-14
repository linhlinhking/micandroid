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
		String strTime = String.valueOf(System.currentTimeMillis()) ;
		StringBuffer buffer = new StringBuffer();
		buffer.append(folderName).append(File.separator).append(date.substring(0,4))
		.append(File.separator).append(date.substring(5, 7)).append(File.separator)
		.append(strTime.substring(0, 4)).append(File.separator)
		.append(strTime.substring(4, 8)).append(File.separator)
		.append(strTime.substring(8));
		return buffer.toString();
	}
	
	public static void main(String args[]){
		
		System.out.println(generateFolderName("upload"));
	}


	public static String createFolder(String folder, HttpServletRequest request) {
		String tempPath = generateFolderName(folder) ;
		StringBuffer buffer = new StringBuffer();
		buffer.append(request.getSession().getServletContext().getRealPath("")).append(File.separator)
		.append(tempPath) ;
				
		File dic = new File(buffer.toString());
		if (!dic.exists()) {
			dic.mkdirs();
		}
		return buffer.toString();
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
	public static String upload(FormDataBodyPart part, String fileName,
			String foldername, HttpServletRequest request) throws IOException {
		String ext = FileUpload.getFileExtension(fileName).toLowerCase();
		if (!"jpg".equals(ext) && !"png".equals(ext)) {
			return "";
		}
	
		String filePath = createFolder(Constant.FOLDER, request);
		StringBuffer temp = new StringBuffer();
		temp.append(filePath).append(File.separator).append(fileName) ;
		InputStream in = part.getValueAs(InputStream.class);
		OutputStream os = new FileOutputStream(temp.toString());

		byte[] buffer = new byte[1024];
		int length = 0;
		while (-1 != (length = in.read(buffer, 0, 1024))) {
			os.write(buffer, 0, length);
		}
		os.close();
		in.close();
		return filePath;
	}


}
