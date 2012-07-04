package ningbo.media.rest.util;

public class JSONCode {

	
	public static final String SUCCESS = "0" ; //操作成功
	
	public static final String RESULT_FAIL = "false" ;
	public static final String RESULT_SUCCESS = "true" ;
	
	/**系统级别异常*/
	public static final String THROWEXCEPTION = "10001" ;//系统异常
	public static final String LOCATION_COUNT_EXCEPTION = "13001" ;//获取位置数目异常
	public static final String THROW_MESSAGE = "Request The Resource No Exists." ;
	public static final String SERVER_EXCEPTION = "System Error." ;
	
	
	/**应用级别的全局异常*/
	public static final String KEYISNULL = "20001" ; //key输入为空
	public static final String KEYINPUTINVALID = "20002" ; //key输入无效
	public static final String NOUPLOADFILE = "20003" ;//没有文件上传
	public static final String LOCATIONID_NOINPUT = "20004" ; //必填项不能为空
	public static final String NOINPUT = "20005" ; //必填项不能为空
	public static final String DELETE_FILE_ISNULL = "20006" ;//删除的文件ID不能为空
	
	/**SystemUser-3*/
	public static final String USERNAME_EXISTS = "30001" ; //用户名称存在
	public static final String USER_EMAIL_EXISTS = "30002" ; //用户email存在
	public static final String USER_NOEXISTS = "30003" ; //用户不存在
	public static final String USERNAME_NOINPUT = "30004" ;//用户名为空
	public static final String USE_PASSWORD_NOINPUT = "30005" ;//用户密码为空
	public static final String USER_INPUT_INVALID = "30006" ;
	
	/**Message*/
	public static final String MSG_USERNAME_EXISTS = "username already exist." ; //用户名称存在
	public static final String MSG_USER_EMAIL_EXISTS = "email already exist." ; //用户名称存在
	public static final String MSG_USER_NOEXISTS = "user no exists." ; //用户不存在
	public static final String MSG_USER_PASSWORD_NOINPUT = "password no input." ;
	public static final String MSG_USER_RESEND_EMAIL = "email resend successfully." ;
	public static final String MSG_USER_ACTIVATED = "user has activated." ;
	public static final String MSG_USER_USERNAME_NO_INPUT = "user no input." ;
	public static final String MSG_USER_FORGOT_PASSWORD = "password had sended to your email." ;
	public static final String MSG_USER_USER_MD5VALUE = "No The User Of Md5 value" ;
	public static final String MSG_KEY_ISNULL = "key no input." ;
	public static final String MSG_KEY_INVALID = "key input invalid." ;
	public static final String MSG_USER_AVATAR_UPDATE = "The User Avatar Aleady Update." ;
	
	
	public static final String MSG_FAVORITE_ALREADY_EXIST = "Already Favorite." ;
	public static final String MSG_FAVORITE_LOCATION_SUCCESS = "Favorite Successfully." ;
	
	public static final String MSG_LOCATION_NO_LOGIN = "Please Sign In." ;
	
	/**Locatioin-4*/
	public static final String LOCATION_NOEXISTS = "20010" ; //位置不存在
	public static final String LOCATION_LATITUDE_IS_NULL = "20011" ;//经纬度为空
	public static final String LOCATION_NEARBY_NODATA = "20012" ;//没有数据
	
	
	/**Comment-5*/
	public static final String COMMENT_THROWEXCEPTION = "40001" ;
	public static final String COMMENT_NOEXISTS = "20011" ; //位置不存在
	
	/**Event-6*/
	public static final String EVENT_NOEXISTS = "20012" ;
	
	/**ModuleFile-7*/
	public static final String MODULEFILE_TOOL_NOEXISTS = "70001" ;
	public static final String MODULEFILE_TYPE_NOEXISTS = "70002" ;
	public static final String MODULEFILE_USER_NOEXISTS = "70003" ;
	public static final String MODULEFILE_FILEID_ERROR = "70004" ;
	public static final String MODULEFILE_FILE_NOEXISTS = "70005" ;
	public static final String MODULEFILE_VALID_CHARACTER = "70006" ;
	public static final String MODULEFILE_BASE64_INVALID = "70007" ;
	public static final String NO_DATA = "No Data." ;
	
	public static final String MSG_MODULEFILE_DELETE_SUCCESS = "Deleted successfully." ;
	public static final String MSG_MODULEFILE_DELETE_FAIL = "Delete failed" ;

	
	
	/**Menu-8*/
	public static final String MENU_LOCATION_NOEXISTS = "80001" ;
	public static final String MENU_TOOL_NOEXISTS = "80002" ;
	
	/**Shop -9*/
	public static final String SHOP_LOCATION_NOEXISTS = "90001" ;
	
	
	
	
	
	/**最早的处理的方式(Favorite)*/
	public static final String GLOBAL_KEYISNULL = "1" ;
	public static final String GLOBAL_KEYINPUTINVALID = "2" ;
	public static final String FAVORITE_LOCATIONISNULL = "3" ;
	public static final String FAVORITE_LOCATIONEXISTS = "4" ;
	public static final String FAVORITE_USERID_NOTEXISTS = "5" ;
	public static final String FAVORITE_ISEXISTS = "6" ;
	public static final String FAVORITE_GET_SERIAL = "7" ;
	public static final String FAVORITE_INPUT_INVALID = "8" ;
	public static final String FAVORITE_NOEXISTS = "9" ;
	
	public static final String LOCATION_CATEGORY2ID_INVALID = "3" ;
	public static final String LOCATION_CATEGORY_NOEXISTS = "4" ;
	public static final String LOCATION_EXCEPTION = "5" ;
	public static final String LOCATION_ID_INVALID = "6" ;
	public static final String LOCATION_BASE64_NOEXISTS = "7" ;
	
	
	
}
