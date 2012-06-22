package ningbo.media.util;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class TranslateUtil {

	private static final String GOOGLE_TRANSLATE_API_REFERRER = "https://www.googleapis.com/language/translate/v2?" ;
	private static final String GOOGLE_TRANSLATE_API_KEY = "AIzaSyAx8aeKzwGeiM3DRwx5ZmkbNSWMlQBDlxo" ;
	
	private static TranslateUtil instance = null ;
	
	private TranslateUtil(){}
	
	public static synchronized TranslateUtil getInstance(){
		if(null == instance){
			instance = new TranslateUtil();
		}
		return instance ;
	}
	
	public static String getEnglishByChinese(String chinese){
		GoogleAPI.setHttpReferrer(GOOGLE_TRANSLATE_API_REFERRER) ;
		GoogleAPI.setKey(GOOGLE_TRANSLATE_API_KEY) ;
		String content = null ;
		try{
			content = Translate.DEFAULT.execute(chinese, Language.CHINESE_SIMPLIFIED, Language.ENGLISH);
		}catch(GoogleAPIException ex){
			ex.printStackTrace() ;
		}
		return content ;
	}
	
	public static void main(String args[]){
		String temp = TranslateUtil.getEnglishByChinese("世界,和平,伟大,中国,军事") ;
		System.out.println(temp) ;
	}
}
