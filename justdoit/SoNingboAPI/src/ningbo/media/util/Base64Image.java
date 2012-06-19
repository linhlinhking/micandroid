package ningbo.media.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.jersey.core.util.Base64;

public class Base64Image {

	public static void main(String args[]) {
		String str = bin2XmlString("C:/images_test/Penguins.jpg");

		System.out.println(str);
		if (xmlString2Bin(str,new File("C:/server/apache-tomcat-6.0.35/222"))) {
			System.out.println("ok");
		}

	}

	public static String getImageBase64(String imagePath) {
		if (null == imagePath) {
			return null;
		}
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imagePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	public static boolean generateImage(String imageCode, String savePath) {

		if (null == imageCode) {
			return false;
		}

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imageCode);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(savePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 解密，并将内容写入至指定文件中

	public static boolean xmlString2Bin(String base64String, File file) {
		byte[] data;
		FileOutputStream output = null;
		boolean ret = false;
		try {
			data = Base64.decode(base64String);
			output = new FileOutputStream(file);
			output.write(data);
			output.close();
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 将文件内容加密

	public static String bin2XmlString(String filename) {
		byte[] data = null;
		FileInputStream input = null;
		String ret = null;
		int n;
		try {
			data = new byte[(int) new File(filename).length()];
			input = new FileInputStream(new File(filename));
			n = input.read(data);// 这个就是一个文件读取过程。没有写while，一次性读完
			input.close();
			ret = new String(Base64.encode(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
