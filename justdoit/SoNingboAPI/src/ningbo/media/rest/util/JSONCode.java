package ningbo.media.rest.util;

public class JSONCode {

	
	public static final String SUCCESS = "0" ; //操作成功
	
	/**系统级别异常*/
	public static final String THROWEXCEPTION = "10001" ;//系统异常
	public static final String LOCATION_COUNT_EXCEPTION = "13001" ;//获取位置数目异常
	
	/**应用级别的异常*/
	public static final String KEYISNULL = "20001" ; //key输入为空
	public static final String KEYINPUTINVALID = "20002" ; //key输入无效
	public static final String NOUPLOADFILE = "20003" ;//没有文件上传
	public static final String LOCATIONID_NOINPUT = "20004" ; //必填项不能为空
	public static final String NOINPUT = "20005" ; //必填项不能为空
	public static final String DELETE_FILE_ISNULL = "20006" ;//删除的文件ID不能为空
	
	/**SystemUser-2*/
	public static final String USERNAME_EXISTS = "20007" ; //用户名称存在
	public static final String EMAIL_EXISTS = "20008" ; //用户email存在
	
	/**Locatioin-3*/
	
	
	
	
	/**最早的处理的方式(Favorite)*/
	public static final String GLOBAL_KEYISNULL = "1" ;
	public static final String GLOBAL_KEYINPUTINVALID = "2" ;
	public static final String FAVORITE_LOCATIONISNULL = "3" ;
	public static final String FAVORITE_LOCATIONEXISTS = "4" ;
	public static final String FAVORITE_USERID_NOTEXISTS = "5" ;
	public static final String FAVORITE_ISEXISTS = "6" ;
	public static final String FAVORITE_GET_SERIAL = "7" ;
	public static final String FAVORITE_INPUT_INVALID = "8" ;
	
	public static final String LOCATION_CATEGORY2ID_INVALID = "3" ;
	public static final String LOCATION_CATEGORY_NOEXISTS = "4" ;
	public static final String LOCATION_EXCEPTION = "5" ;
	
}
