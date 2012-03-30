package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.LinkUserAndImage;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LinkUserAndImageDao;
import ningbo.media.service.LinkUserAndImageService;

@Service("linkUserAndImageService")
public class LinkUserAndImageServiceImpl extends
		BaseServiceImpl<LinkUserAndImage, Integer> implements
		LinkUserAndImageService {

	@Autowired
	public LinkUserAndImageServiceImpl(
			@Qualifier("linkUserAndImageDao") LinkUserAndImageDao linkUserAndImageDao) {
		super(linkUserAndImageDao);
	}

}
