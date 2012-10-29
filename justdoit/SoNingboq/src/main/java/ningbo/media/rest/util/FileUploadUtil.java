package ningbo.media.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ningbo.media.bean.enums.DirectoryType;
import ningbo.media.util.MagickImageScale;

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
			DirectoryType type, boolean isTemp) {
		StringBuffer buffer = new StringBuffer();
		String realPath = request.getSession().getServletContext()
				.getRealPath("");

		buffer.append(realPath).append(File.separator);
		if (type.equals(DirectoryType.PRODUCT)) {
			buffer.append(Constant.PRODUCT);
		} else if (type.equals(DirectoryType.USERDIR)) {
			buffer.append(Constant.USERDIR);
		} else {
			buffer.append(Constant.UPLOAD);
		}
		if (isTemp) {
			buffer.append(FILE_SEPARATOR).append(Constant.TEMP)
					.append(FILE_SEPARATOR);
		} else {
			String path = getUuidPath(uuid);
			buffer.append(FILE_SEPARATOR).append(path);
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
		sb.append(uuid.substring(0, 4)).append(FILE_SEPARATOR)
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
			String path = getUuidPath(filePathAndName);
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
			newname = newname + ext;
			File newfile = new File(path + File.separator + newname);

			oldfile.renameTo(newfile);

			return newname;
		} else {
			logger.error("oldname the same newname");
			return null;
		}
	}

	/**
	 * @param uploadedInputStream
	 * @param uploadedFileLocation
	 * @param serverId
	 * @return
	 */

	public static Map<String, Object> writeToFile(
			InputStream uploadedInputStream, String uploadedFileLocation,
			HttpServletRequest request,DirectoryType type) {
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
			String uuid = FileHashCode.getFileMD5(uploadedFileLocation);
			String tempPath = makeFileDir(uuid,request,type,false);
			StringBuffer sb = new StringBuffer();
			sb.append(tempPath).append(File.separator)
					.append(uuid.substring(12));
			copyFile(uploadedFileLocation, sb.toString());

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

			FileUploadUtil.delFile(uploadedFileLocation);
			out.flush();
			out.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			// int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					// bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	/**
	 *  @功能：保存临时目录文件到UUID目录
	 * 
	 *  @param request 
	 *  @param tempFilePath 
	 * 
	 */
	public static Map<String, Object> writeToFile(HttpServletRequest request,
			String tempFilePath) {
		Map<String, Object> map = new HashMap<String, Object>(7);
		try {
			String uuid = FileHashCode.getFileMD5(tempFilePath);
			String tempPath = FileUploadUtil.makeFileDir(uuid, request, DirectoryType.UPLOAD,false);
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

			map = ImageDetailInformation.getImageInformation(tempFilePath);
			map.put(Constant.UUID, uuid);

			// delFile(tempFilePath);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @功能：处理移动终端的图片上传。
	 * 
	 * @param request
	 * @param tempFilePath.
	 * 
	 * @return path
	 * 
	 */
	public static String writeBase64File(HttpServletRequest request,
			String tempFilePath) {
		try {
			String uuid = FileHashCode.getFileMD5(tempFilePath);
			String tempPath = FileUploadUtil.makeFileDir(uuid, request, DirectoryType.UPLOAD,false);
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

			FileUploadUtil.delFile(tempFilePath);
			return uuid;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
