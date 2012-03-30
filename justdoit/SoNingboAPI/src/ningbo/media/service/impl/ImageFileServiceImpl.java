package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ningbo.media.bean.ImageFile;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.ImageFileDao;
import ningbo.media.service.ImageFileService;

@Service("imageFileService")
public class ImageFileServiceImpl extends BaseServiceImpl<ImageFile, Integer>
		implements ImageFileService {

	@Autowired
	public ImageFileServiceImpl(@Qualifier("imageFileDao")ImageFileDao imageFileDao) {
		super(imageFileDao);

	}

}
