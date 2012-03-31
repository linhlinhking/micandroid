package com.ssh2;

/**
 * 
 * @author JeccyZhao
 *
 */
public class Constants {
	// default User in session
	public static String DefaultUserInSession= "USER";
	
	// default page size
	public static Integer DefaultPageSize  = 25;
	
	// default maximum page size
	public static Integer DefaultMaximumPageSize  = 30;
	
	// AJAX request parameter
	public static String  DefaultAjaxParamFlag = "ajax";
	
	// page query parameter
	public static String  DefaultPageParamFlag = "page";
	
	// path holding the uploaded files
	public static String DefaultFileUploadDir = "uploads";
	
	// the parameter name for holding the session user
	public static String DefaultUsrParamSessionHolder = "SESSION_USR_DATA";
	
	// home path of web application
	public static String WebHomePath = "";
	
	// file separator
	public static String FileSeparator = System.getProperty("file.separator");
	
	// start time param name in front side
	public static String FrontSideStartTimeParamName = "startTime";
	
	// end time param name in front side
	public static String FrontSideEndTimeParamName = "endTime";
	
		// authority resources
	public static String ContextUrlAuthorities = "urlAuthorities";
	
	// i18n message of context
	public static String ContextI18nMessages = "i18nMessages";
	
	// available access urls
	public static String ContextAvailURLs = "urlAvailables";
	
	// resource of WEB-INF
	public static String RESOURCE_WEB_INF = "WEB-INF";
	
	// prefix of file resource
	public static String RESOURCE_FILE_PREFIX = "file:/";
	
	// prefix of classpath resource
	public static String RESOURCE_CLASSPATH_PREFIX = "classpath:";
	
	// classpath of i18n_message, default with "zh_CN"
	public static String RESOURCE_MESSAGE_CN = "classpath:i18n/messages_zh_CN.properties";
	
	// classpath of urlrewrite 
	public static String RESOURCE_URLREWRITE = "classpath:urlrw/";
	
	// default password for staff
	public static String DefaultStaffPassword = "123456";
	
	// default separate char
	public static char DefaultSeparateChar = ',';
	
	// default common user role
	public static String DefaultRoleCommonUser = "ROLE_USER";
	
	// default admin user role
	public static String DefaultRoleAdminUser = "ROLE_ADMIN";
	
	// default editable category value
	public static Integer DefaultEditableCatVal = 1;
	
}
