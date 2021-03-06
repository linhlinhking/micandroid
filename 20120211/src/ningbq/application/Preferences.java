package ningbq.application;

public class Preferences {

	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";
	public static final String CHECK_UPDATES_KEY = "check_updates";
	public static final String CHECK_UPDATE_INTERVAL_KEY = "check_update_interval";
	public static final String VIBRATE_KEY = "vibrate";
	public static final String TIMELINE_ONLY_KEY = "timeline_only";
	public static final String REPLIES_ONLY_KEY = "replies_only";
	public static final String DM_ONLY_KEY = "dm_only";

	public static final String RINGTONE_DEFAULT_KEY = "content://settings/system/notification_sound";

	public static final String LAST_TWEET_REFRESH_KEY = "last_tweet_refresh";
	public static final String LAST_DM_REFRESH_KEY = "last_dm_refresh";
	public static final String LAST_FOLLOWERS_REFRESH_KEY = "last_followers_refresh";

	public static final String TWITTER_ACTIVITY_STATE_KEY = "twitter_activity_state";

	public static final String HIGHLIGHT_BACKGROUND = "highlight_background";
	public static final String USE_PROFILE_IMAGE = "use_profile_image";
	public static final String PHOTO_PREVIEW = "photo_preview";
	public static final String FORCE_SHOW_ALL_IMAGE = "force_show_all_image";

	public static final String RT_PREFIX_KEY = "rt_prefix";
	public static final String RT_INSERT_APPEND = "rt_insert_append"; // 转发时光标放置在开始还是结尾

	public static final String NETWORK_TYPE = "network_type";

	// 当前用户相关信息
	public static final String CURRENT_USER_ID = "current_user_id";
	public static final String CURRENT_USER_SCREEN_NAME = "current_user_screenname";

	public static final String UI_FONT_SIZE = "ui_font_size";

	public static final String USE_ENTER_SEND = "use_enter_send";
	public static final String USE_GESTRUE = "use_gestrue";
	public static final String USE_SHAKE = "use_shake";
	public static final String FORCE_SCREEN_ORIENTATION_PORTRAIT = "force_screen_orientation_portrait";
	
	public static  double LOCATION_LATITUDE = 0.0 ;
	public static  double LOCATION_LONGITUDE = 0.0 ;
}
