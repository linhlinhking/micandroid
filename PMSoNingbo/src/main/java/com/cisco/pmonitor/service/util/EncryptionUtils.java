package com.cisco.pmonitor.service.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * A tool for symmetric encryption.
 * User: shuaizha
 * Date: 12-3-23
 * Time: 下午3:43
 */
public final class EncryptionUtils {

    private static int key = -1;

    //initialize the symmetric key, if there is no key in the system then it will have nothing to look up.
    static {

        try {
            //register the key for your special location
            BufferedReader br = new BufferedReader(new FileReader(new File("C:/key.lic")));
            StringBuilder keyString = new StringBuilder();
            String str;
            while(null != (str = br.readLine())) {
                keyString.append(str);
            }
            char[] chars = keyString.toString().toCharArray();
            int tmp = 0;
            for(int i = 0, j = 1; i < chars.length - 1; i ++, j ++) {
                tmp ^= chars[i] ^ chars[j];
            }
            key = tmp % 64;
        } catch (Exception e) {
        }
    }

    /**
     * it's a symmetric encryption algorithm,
     * so you can encode or decode the message,
     * using the same method: encrypt method.
     * @param message the source string
     * @return encrypted string
     */
    public static String encrypt(String message) {
        if(StringUtils.isEmpty(message) || key == -1) {
            return "";
        }
        char[] chars = message.toCharArray();
        for(int i = 0; i < chars.length; i ++) {
            chars[i] ^= key;
        }
        return new String(chars);
    }



    public static void main(String []args) {
        String source = "encryption";
        String encode = EncryptionUtils.encrypt(source);
        System.out.println(encode);
        String decode = EncryptionUtils.encrypt(encode);
        System.out.println(decode);
    }
}
