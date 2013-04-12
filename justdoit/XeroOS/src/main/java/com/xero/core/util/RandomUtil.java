package com.xero.core.util;

public class RandomUtil {

	private static String[] randomValues = new String[] { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n", "u", "t", "s", "o", "x", "v",
			"p", "q", "r", "w", "y", "z" };
	private static int DEFAULTLENGTH = 6 ;

	public static String generatePassword(int len) {
		if(len < 1){
			len = DEFAULTLENGTH ;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < len; i++) {
			Double number = Math.random() * (randomValues.length - 1);
			str.append(randomValues[number.intValue()]);
		}

		return str.toString();
	}
	
	public static void main(String args[]){
		//System.out.println(generatePassword(0));
	}

}
