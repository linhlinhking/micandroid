package ningbo.media.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ningbo.media.util.MagickImageScale;
import ningbo.media.util.Resources;

public class FileHashCode {

	private static char hexChar[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };


	private FileHashCode() {
	}

	public static String getFileMD5(String filename) {
		String str = "";
		try {
			str = getHash(filename, "MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA1(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA256(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA-256");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA384(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA-384");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA512(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA-512");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	private static String getHash(String fileName, String hashType)
			throws Exception {
		InputStream fis = new FileInputStream(fileName);
		byte buffer[] = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance(hashType);
		for (int numRead = 0; (numRead = fis.read(buffer)) > 0;) {
			md5.update(buffer, 0, numRead);
		}

		fis.close();
		return toHexString(md5.digest());
	}

	// 9191809001898918
	private static String toHexString(byte b[]) {
		System.out.println(b.length);
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			// sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			// System.out.println(b[i]) ;
			sb.append(hexChar[b[i] & 0x9]);
		}
		return sb.toString();
	}

	/**
	 * example:/Users/ning/upload/1180/8011/8099/
	 * 
	 * @param uuid
	 * @param serverId
	 *            Image information id
	 * @return path
	 */
	public static String makeFileDir(String uuid) {

		String path = getUuidPath(uuid);

		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		return path;
	}

	public static String getUuidPath(String uuid) {
		String dir = File.separator + uuid.substring(0, 4) + File.separator
				+ uuid.substring(4, 8) + File.separator + uuid.substring(8, 12)
				+ File.separator;// + uuid.substring(12);

		StringBuffer sb = new StringBuffer();
		sb.append(Resources.getText(Resources.GLOBAL_PATH)).append(dir);
		// .append(File.separator)
		return sb.toString();
	}

	// 0009808890190001
	// 0989919081110811
	public static void main(String args[]) {
		String path = "/Users/ning/git/image1.jpg";

		System.out.println(getFileMD5(path));
		// String uuid = "1180801180998999";
		// System.out.println(getUuidPath(uuid)) ;
		// System.out.println(makeFileDir(uuid));
		// System.out.println(FileHashCode.class.getClassLoader().getResource("").getPath())
		// ;
		// System.out.println(get) ;

	}

	/**
	 * example:/Users/ning/upload/temp/
	 * 
	 * @return
	 */
	public static String makeTempFileDir() {
		String dir = Resources.getText(Resources.GLOBAL_PATH) + File.separator
				+ Constant.TEMP + File.separator;
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		return dir;
	}
	

	/**
	 * @param uploadedInputStream
	 * @param uploadedFileLocation
	 * @param serverId
	 * @return
	 */
	public static Map<String, Object> writeToFile(
			InputStream uploadedInputStream, String uploadedFileLocation) {
		Map<String, Object> map = new HashMap<String, Object>(7);
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			String uuid = getFileMD5(uploadedFileLocation);
			String tempPath = makeFileDir(uuid);
			StringBuffer sb = new StringBuffer();
			sb.append(tempPath).append(File.separator).append(
					uuid.substring(12));
			copyFile(uploadedFileLocation, sb.toString());

			// 同时生成原图的缩略图
			File srcFile = new File(uploadedFileLocation);

			try {
				ResizeEnum[] resizes = ResizeEnum.values();
				for (ResizeEnum re : resizes) {
					StringBuffer temp = new StringBuffer();
					temp.append(tempPath).append(uuid.substring(12))
							.append("-");
					String tmp = re.getName();
					String[] tmps = tmp.split("x");

					temp.append(tmp);
					File destFile = new File(temp.toString());
					Integer width = Integer.valueOf(tmps[0]);
					Integer height = Integer.valueOf(tmps[1]);
					if (width == height) {
						MagickImageScale.resizeFix(srcFile, destFile, width,
								height, false);
					} else {
						MagickImageScale.resizeFix(srcFile, destFile, width,
								800);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			map = ImageDetailInformation
					.getImageInformation(uploadedFileLocation);
			map.put(Constant.UUID, uuid);

			delFile(uploadedFileLocation);
			out.flush();
			out.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	public static void delFile(String filePathAndName) {
		try {
			File myDelFile = new File(filePathAndName);

			myDelFile.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * copy file
	 * 
	 * @param oldPath
	 *            String oldPath
	 * @param newPath
	 *            String copyPath
	 * @return boolean
	 */
	private static void copyFile(String oldPath, String newPath) {
		try {
			//int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					//bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	
	public static Map<String, Object> writeToFile(
			HttpServletRequest request, String tempFilePath) {
		Map<String, Object> map = new HashMap<String, Object>(7);
		try {
			String uuid = getFileMD5(tempFilePath);
			String tempPath = FileUploadUtil.makeFileDir(uuid, request, false);
			StringBuffer sb = new StringBuffer();
			sb.append(tempPath).append(uuid.substring(12));
			
			copyFile(tempFilePath, sb.toString());

			// 同时生成原图的缩略图
			File srcFile = new File(tempFilePath);

			
			try {
				ResizeEnum[] resizes = ResizeEnum.values();
				for (ResizeEnum re : resizes) {
					StringBuffer temp = new StringBuffer();
					temp.append(tempPath).append(uuid.substring(12))
							.append("-");
					String tmp = re.getName();
					String[] tmps = tmp.split("x");

					temp.append(tmp);
					File destFile = new File(temp.toString());
					Integer width = Integer.valueOf(tmps[0]);
					Integer height = Integer.valueOf(tmps[1]);
					if (width == height) {
						MagickImageScale.resizeFix(srcFile, destFile, width,
								height, false);
					} else {
						MagickImageScale.resizeFix(srcFile, destFile, width,
								800);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			map = ImageDetailInformation
					.getImageInformation(tempFilePath);
			map.put(Constant.UUID, uuid);

			//delFile(tempFilePath);
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static String writeBase64File(
			HttpServletRequest request, String tempFilePath) {
		try {
			String uuid = getFileMD5(tempFilePath);
			String tempPath = FileUploadUtil.makeFileDir(uuid, request, false);
			StringBuffer sb = new StringBuffer();
			sb.append(tempPath).append(uuid.substring(12));
			
			copyFile(tempFilePath, sb.toString());
			File srcFile = new File(tempFilePath);
			try {
				ResizeEnum[] resizes = ResizeEnum.values();
				for (ResizeEnum re : resizes) {
					StringBuffer temp = new StringBuffer();
					temp.append(tempPath).append(uuid.substring(12))
							.append("-");
					String tmp = re.getName();
					String[] tmps = tmp.split("x");

					temp.append(tmp);
					File destFile = new File(temp.toString());
					Integer width = Integer.valueOf(tmps[0]);
					Integer height = Integer.valueOf(tmps[1]);
					if (width == height) {
						MagickImageScale.resizeFix(srcFile, destFile, width,
								height, false);
					} else {
						MagickImageScale.resizeFix(srcFile, destFile, width,
								800);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			delFile(tempFilePath);
			return uuid;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}