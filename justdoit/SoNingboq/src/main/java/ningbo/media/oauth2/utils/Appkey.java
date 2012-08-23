package ningbo.media.oauth2.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Appkey {
//secret
	public static String _AppKey_() {
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(r.nextInt());
		}
		return sb.toString();
	}

	public static String getAppKey(){
		return maybeAppKey(10) ;
	}
	
	private static String maybeAppKey(int code_len) {
		int count = 0;
		char str[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < code_len) {
			int i = Math.abs(r.nextInt(10));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
	
	public static void main(String args[]){
		System.out.println(getSecretKey(getAppKey())) ;
	}
	
	public static String getSecretKey(String appKey){
		try {
			//SHA-1
			//MD5
			MessageDigest msgDigest = MessageDigest.getInstance("MD5") ;
			msgDigest.update(appKey.getBytes()) ;
			byte[] b = msgDigest.digest() ;
			//System.out.println(byte2hex(b)) ;
			return null ;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	


}
