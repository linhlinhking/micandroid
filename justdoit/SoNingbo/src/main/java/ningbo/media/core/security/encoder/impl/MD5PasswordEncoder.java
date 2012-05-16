package ningbo.media.core.security.encoder.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import ningbo.media.core.security.encoder.PasswordEncoder;

/**
 * Description:MD5加密算法实现
 * 
 * @author Devon.Ning
 * @2012-4-16下午04:53:33
 * @version 1.0
 * 
 * Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 * 
 */
public class MD5PasswordEncoder implements PasswordEncoder {

	/** 盐值 */
	private String defaultSalt;

	private static final String ALGORITHM = "md5";

	public String encodePassword(String rawPass) {
		return encodePassword(rawPass, defaultSalt);
	}

	public String encodePassword(String rawPass, String salt) {
		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		try {
			digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		return new String(Hex.encodeHex(digest));
	}

	public boolean isPasswordValid(String encPass, String rawPass) {
		return isPasswordValid(encPass, rawPass, defaultSalt);
	}

	public boolean isPasswordValid(String encPass, String rawPass, String salt) {
		if (encPass == null) {
			return false;
		}
		String pass2 = encodePassword(rawPass, salt);
		return encPass.equals(pass2);
	}

	public String getDefaultSalt() {
		return defaultSalt;
	}

	public void setDefaultSalt(String defaultSalt) {
		this.defaultSalt = defaultSalt;
	}

	protected String mergePasswordAndSalt(String password, Object salt,
			boolean strict) {
		if (password == null) {
			password = "";
		}
		if (strict && (salt != null)) {
			if ((salt.toString().lastIndexOf("{") != -1)
					|| (salt.toString().lastIndexOf("}") != -1)) {
				throw new IllegalArgumentException(
						"Cannot use { or } in salt.toString()");
			}
		}
		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return password + "{" + salt.toString() + "}";
		}
	}

	protected final MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm ["
					+ ALGORITHM + "]");
		}
	}

}
