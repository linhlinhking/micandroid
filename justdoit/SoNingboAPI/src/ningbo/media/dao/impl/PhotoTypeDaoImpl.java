package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.PhotoType;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.PhotoTypeDao;

@Repository("photoTypeDao")
public class PhotoTypeDaoImpl extends BaseDaoImpl<PhotoType, Integer> implements
		PhotoTypeDao {

	public PhotoTypeDaoImpl() {
		super(PhotoType.class);
	}
}
