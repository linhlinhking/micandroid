package ningbo.media.core.kernel.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ningbo.media.core.kernel.model.JdbcInfo;
import ningbo.media.core.kernel.model.ProjectInfo;

public final class ResourceUtils {

	private static final JdbcInfo DEFAULT_JDBC_INFO;

	private static final ProjectInfo PROJECT_INFO;

	/** 头描述文件 */
	private static final String CODE_TEMPLATE;

	static {
		DEFAULT_JDBC_INFO = new JdbcInfo();
		Properties config = new Properties();
		PROJECT_INFO = new ProjectInfo();
		InputStream in = null;
		
		String proFilePath = FileUtil.getResourcePath("default.properties");
		try {
			in = new BufferedInputStream(new FileInputStream(proFilePath));
			config.load(in);
			String driver = config.getProperty("jdbc.driver");
			String url = config.getProperty("jdbc.url");
			String username = config.getProperty("jdbc.username");
			String password = config.getProperty("jdbc.password");
			DEFAULT_JDBC_INFO.setDriver(driver);
			DEFAULT_JDBC_INFO.setUrl(url);
			DEFAULT_JDBC_INFO.setUsername(username);
			DEFAULT_JDBC_INFO.setPassword(password);
			PROJECT_INFO.setProjectName(config.getProperty("project.name"));
			String pkName = config.getProperty("project.package");
			if (pkName.endsWith(".") || pkName.endsWith(",")
					|| pkName.endsWith("。")) {
				throw new RuntimeException("包名不正确");
			}
			String cache = config.getProperty("mybatis.cache");
			PROJECT_INFO
					.setCache(cache == null ? "" : "type=\"" + cache + "\"");
			PROJECT_INFO.setPackageName(pkName);
			PROJECT_INFO.setBizTable(config.getProperty("table.biz")
					.toUpperCase());
			PROJECT_INFO.setUser(config.getProperty("project.user"));

			CODE_TEMPLATE = "/*\n" + " * Copyright (c) 2010-2011 宁波商外文化传媒有限公司.\n"
					+ " * [Project:" + config.getProperty("project.name")
					+ ",Id:${name}.java  ${datetime} "
					+ config.getProperty("project.user") + " ]\n" + " */\n"
					+ "package ${package};\n";
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件不存在" + e.getLocalizedMessage());
		} catch (IOException e) {
			throw new RuntimeException("文件读取异常:" + e.getLocalizedMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			in = null;
			config = null;
		}
	}

	private ResourceUtils() {
	}

	protected static class Inner {
		private static final ResourceUtils RESOURCE_UTILS = new ResourceUtils();
	}

	public static ResourceUtils getInstance() {
		return Inner.RESOURCE_UTILS;
	}

	public JdbcInfo getJdbc() {
		return DEFAULT_JDBC_INFO;
	}

	public ProjectInfo getProject() {
		System.out.println(PROJECT_INFO);
		return PROJECT_INFO;
	}

	public String getCodeTemplate() {
		return CODE_TEMPLATE;
	}

	// src\main\java\ningbo\media\core\kernel\resource

	
	

}
