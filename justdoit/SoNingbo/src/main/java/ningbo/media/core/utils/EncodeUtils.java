package ningbo.media.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Description:字符编码操作工具
 * @author Devon.Ning
 * @2012-4-18下午04:10:38
 * @version 1.0
 * <p>Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.</p>
 */
public class EncodeUtils {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	public static Md5PasswordEncoder md5coder=new Md5PasswordEncoder();
	
	public static String getMd5PasswordEncoder(String password,String salt){
		return md5coder.encodePassword(password, salt);
	}

	/**
	 * Hex编码.
	 */
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String base64Encode(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 编码.
	 */
	public static String urlEncode(String input, String encoding) {
		try {
			return URLEncoder.encode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 解码.
	 */
	public static String urlDecode(String input, String encoding) {
		try {
			return URLDecoder.decode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * Html 转码.
	 */
	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * Html 解码.
	 */
	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String xmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}
}
