package ningbo.media.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;
import com.drew.metadata.exif.GpsDirectory;

public class ImageExifTest {

	@Test
	public void testImage() throws JpegProcessingException, MetadataException {
		String path = "/Users/ning/upload/97.jpg";// photo.JPG
		File jpegFile = new File(path);
		Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
		//GpsDirectory
		Directory exif = metadata.getDirectory(ExifDirectory.class);
		Iterator tags = exif.getTagIterator();
		while (tags.hasNext()) {
			Tag tag = (Tag) tags.next();
			System.out.println(tag.getDescription());
		}
	}

	public Map<String, Object> getImageExif() {
		Map<String, Object> map = new HashMap<String, Object>();
		String path = "/Users/ning/upload/97.jpg";// photo.JPG
		try {
			File jpegFile = new File(path);
			Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
			
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		}

		return map;
	}
}
