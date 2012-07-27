package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.FeedBack;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FeedBackDao;

@Repository("feedBackDao")
public class FeedBackDaoImpl extends BaseDaoImpl<FeedBack, Integer> implements
		FeedBackDao {

	public FeedBackDaoImpl(){
		super(FeedBack.class);
	}
}
