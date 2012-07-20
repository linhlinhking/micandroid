package ningbo.media.service.impl;

import ningbo.media.bean.FeedBack;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FeedBackDao;
import ningbo.media.service.FeedBackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("feedBackService")
public class FeedBackServiceImpl extends BaseServiceImpl<FeedBack, Integer>
		implements FeedBackService {

	@Autowired
	public FeedBackServiceImpl(@Qualifier("feedBackDao")
	FeedBackDao feedBackDao) {
		super(feedBackDao);
	}
}
