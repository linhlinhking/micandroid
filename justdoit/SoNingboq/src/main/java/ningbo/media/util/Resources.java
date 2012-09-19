package ningbo.media.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Resources {

	 /** 本地化资源文件。 */
    public static final String RESOURCE_LOCATION = "upload";
    public static final String ACCESS_ERROR = "accessError" ;
    
    /** class 全名的资源ResourceBundle。 */
    private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_LOCATION);
    
    public static final String GLOBAL_PATH_LOCATION = "global.path.location" ;
    
    public static final String GLOBAL_PATH = "global.path" ;
    
    private Resources(){}
    
    /**
    public static ResourceBundle getResource(String source){
    	return ResourceBundle.getBundle(source) ;
    }
    */
    
    
    /**
     * 从此资源包中获取给定键的字符串。如果未找到给定键的对象，则返回空字符串。
     * @param key 所需字符串的键。
     * @return 给定键的字符串。
     */
    /** */
    public static String getText(String key) {
        if (key == null || "".equals(key.trim()))
            return "";
        String message = "";
        try {
            message = rb.getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
        return message;
    }
    

    
}
