package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.LinkUserAndImage;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LinkUserAndImageDao;

@Repository("linkUserAndImageDao")
public class LinkUserAndImageDaoImpl extends
		BaseDaoImpl<LinkUserAndImage, Integer> implements LinkUserAndImageDao {
	

	public LinkUserAndImageDaoImpl(){
		super(LinkUserAndImage.class) ;
	}
}
