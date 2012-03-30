package ningbo.media.rest.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageDetailInformation {

	
	/**
	 * width height size.
	 * 
	 * @param image path
	 * @return image information.
	 */
	public static Map<String, Object> getImageInformation(String imagePath) {
		Map<String, Object> map = new HashMap<String, Object>(3);
		File imageFile = new File(imagePath);
		try {
			FileInputStream fis = new FileInputStream(imageFile);
			BufferedImage buff = ImageIO.read(imageFile);
			
			map.put(Constant.WIDTH, buff.getWidth() * 1L);
			map.put(Constant.HEIGHT, buff.getHeight() * 1L);
			map.put(Constant.FILESIZE, imageFile.length());
			fis.close();
		} catch (FileNotFoundException e) {
			map = null;
		} catch (IOException e) {
			e.printStackTrace(); 
			map = null;
		}
		return map;
	}
	
	public static void main(String args[]){
		String path = "/Users/ning/upload/temp/1189" ;
		
		 Map<String, Object> m = getImageInformation(path); 
         for (Map.Entry<String, Object> entry : m.entrySet()) { 
                 System.out.println(entry.getKey() + " " + entry.getValue()); 
         } 
	}
}
