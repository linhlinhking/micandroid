package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ningbo.media.bean.PhotoType;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.PhotoTypeDao;
import ningbo.media.service.PhotoTypeService;


@Repository("photoTypeService")
public class PhotoTypeServiceImpl extends BaseServiceImpl<PhotoType, Integer> implements PhotoTypeService{
	
	@Autowired
	public PhotoTypeServiceImpl(@Qualifier("photoTypeDao")PhotoTypeDao photoTypeDao) {
		super(photoTypeDao);
	}

}
