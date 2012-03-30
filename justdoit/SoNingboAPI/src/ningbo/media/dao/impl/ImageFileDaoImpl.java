package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.ImageFile;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.ImageFileDao;

@Repository("imageFileDao")
public class ImageFileDaoImpl extends BaseDaoImpl<ImageFile, Integer> implements
		ImageFileDao {

	public ImageFileDaoImpl(){
		super(ImageFile.class) ;
	}
}
