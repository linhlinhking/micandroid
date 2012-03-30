package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.ImageInformation;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.ImageInformationDao;

@Repository("imageInformationDao")
public class ImageInformationDaoImpl extends
		BaseDaoImpl<ImageInformation, Integer> implements ImageInformationDao {

}
