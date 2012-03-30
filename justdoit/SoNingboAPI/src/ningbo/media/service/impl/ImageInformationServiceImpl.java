package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ningbo.media.bean.ImageInformation;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.ImageInformationDao;
import ningbo.media.service.ImageInformationService;

@Service("imageInformationService")
public class ImageInformationServiceImpl extends
		BaseServiceImpl<ImageInformation, Integer> implements
		ImageInformationService {

	@Autowired
	public ImageInformationServiceImpl(
			@Qualifier("imageInformationDao") ImageInformationDao imageInformationDao) {
		super(imageInformationDao);
	}

}
