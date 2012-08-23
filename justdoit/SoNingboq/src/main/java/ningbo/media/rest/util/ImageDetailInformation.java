package ningbo.media.rest.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import ningbo.media.util.ExifAnalyzer;
import ningbo.media.util.Geolocation;

public class ImageDetailInformation {

	/**
	 * width height size.
	 * 
	 * @param image
	 *            path
	 * @return image information.
	 */
	public static Map<String, Object> getImageInformation(String imagePath) {
		Map<String, Object> map = new HashMap<String, Object>(6);
		File imageFile = new File(imagePath);
		try {
			FileInputStream fis = new FileInputStream(imageFile);
			BufferedImage buff = ImageIO.read(imageFile);
			map.put(Constant.WIDTH, buff.getWidth() * 1L);
			map.put(Constant.HEIGHT, buff.getHeight() * 1L);
			map.put(Constant.FILESIZE, imageFile.length());

			ExifAnalyzer exif = ExifAnalyzer.create(imageFile);
			String formatTemp = "";
			if (null != exif) {
				Geolocation loc = exif.getGeolocation();
				Date takeTime = exif.getDateTime(TimeZone.getDefault());
				if (null != takeTime) {
					formatTemp = DateUtil.date2String(takeTime,
							"yyyy-MM-dd HH:mm:ss");
				}

				map.put(Constant.LATITUDE, loc.getLatitude());
				map.put(Constant.LONGITUDE, loc.getLongitude());
				map.put(Constant.TAKE_PHOTO_DATE, formatTemp);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			map = null;
		} catch (IOException e) {
			e.printStackTrace();
			map = null;
		}
		return map;
	}

	public static void main(String args[]) {
		String path = "/Users/ning/upload/temp/1189";

		Map<String, Object> m = getImageInformation(path);
		for (Map.Entry<String, Object> entry : m.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
