package com.xero.core.util;

import java.util.Locale;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class TranslateUtil {

	private static final String GOOGLE_TRANSLATE_API_REFERRER = "https://www.googleapis.com/language/translate/v2?";
	private static final String GOOGLE_TRANSLATE_API_KEY = "AIzaSyAx8aeKzwGeiM3DRwx5ZmkbNSWMlQBDlxo";

	private static TranslateUtil instance = null;

	private TranslateUtil() {
		
	}

	public static synchronized TranslateUtil getInstance() {
		if (null == instance) {
			instance = new TranslateUtil();
		}
		return instance;
	}

	public static String getEnglishByChinese(String chinese) {
		GoogleAPI.setHttpReferrer(GOOGLE_TRANSLATE_API_REFERRER);
		GoogleAPI.setKey(GOOGLE_TRANSLATE_API_KEY);
		String content = null;
		try {
			content = Translate.DEFAULT.execute(chinese,
					Language.CHINESE_SIMPLIFIED, Language.ENGLISH);
		} catch (GoogleAPIException ex) {
			ex.printStackTrace();
		}
		return content;
	}

	public static String translationContent(String content, String local) {
		GoogleAPI.setHttpReferrer(GOOGLE_TRANSLATE_API_REFERRER);
		GoogleAPI.setKey(GOOGLE_TRANSLATE_API_KEY);
		String temp = null;
		try {
			if ("zh".equals(local)) {
				temp = Translate.DEFAULT.execute(content,
						Language.CHINESE_SIMPLIFIED, Language.ENGLISH);
			} else if ("en".equals(local)) {
				temp = Translate.DEFAULT.execute(content, Language.ENGLISH,
						Language.CHINESE_SIMPLIFIED);
			}
		} catch (GoogleAPIException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static String translationContent(String content, String fromLang,
			String toLang) {
		GoogleAPI.setHttpReferrer(GOOGLE_TRANSLATE_API_REFERRER);
		GoogleAPI.setKey(GOOGLE_TRANSLATE_API_KEY);
		String temp = null;
		try {
			temp = Translate.DEFAULT.execute(content,
					Language.fromString(fromLang), Language.fromString(toLang));
		} catch (GoogleAPIException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static void main(String args[]) {
		System.out.println(Locale.CHINA) ;
		System.out.println(Locale.CHINESE) ;
		String temp = TranslateUtil.getEnglishByChinese("世界,和平,伟大,中国,军事");
		String temp3 = translationContent("世界,和平,伟大,中国,军事",Language.CHINESE_SIMPLIFIED.toString(),Language.ALBANIAN.toString()) ;
		System.out.println(Language.CHINESE_SIMPLIFIED.toString()) ;
		System.out.println(temp);
		System.out.println(temp3);
	}
}
