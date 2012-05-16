package ningbo.media.common.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:本地文件上传
 * 
 * @author Devon.Ning
 * @2012-4-16下午05:10:33
 * @version 1.0
 *          <p>
 *          Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 *          </p>
 */
public class LocalFileUpload {

	private Logger log = LoggerFactory.getLogger(LocalFileUpload.class);

	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String storeByExt(String path, String ext, MultipartFile file)
			throws IOException {
		String filename = UploadUtil.generateFilename(path, ext);
		File dest = new File(servletContext.getRealPath(filename));
		dest = UploadUtil.getUniqueFile(dest);
		copy(file, dest);
		return filename;
	}

	public String storeByFilename(String filename, MultipartFile file)
			throws IOException {
		File dest = new File(servletContext.getRealPath(filename));
		copy(file, dest);
		return filename;
	}

	public String storeByExt(String path, String ext, File file)
			throws IOException {
		String filename = UploadUtil.generateFilename(path, ext);
		File dest = new File(servletContext.getRealPath(filename));
		dest = UploadUtil.getUniqueFile(dest);
		copy(file, dest);
		return filename;
	}

	public String storeByFilename(String filename, File file)
			throws IOException {
		File dest = new File(servletContext.getRealPath(filename));
		copy(file, dest);
		return filename;
	}

	/** 上传多个文件 */
	private void copy(MultipartFile file, File dest) throws IOException {
		try {
			UploadUtil.checkDirAndCreate(dest.getParentFile());
			file.transferTo(dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	/** 上传单个文件 */
	private void copy(File file, File dest) throws IOException {
		try {
			UploadUtil.checkDirAndCreate(dest.getParentFile());
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}
}
