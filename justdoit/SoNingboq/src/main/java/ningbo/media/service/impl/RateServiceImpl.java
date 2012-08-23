package ningbo.media.service.impl;

import javax.annotation.Resource;

import ningbo.media.bean.Rate;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.RateDao;
import ningbo.media.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("rateService")
public class RateServiceImpl extends BaseServiceImpl<Rate, Integer> implements
		RateService {

	@Resource
	private RateDao rateDao ;
	
	@Autowired
	public RateServiceImpl(@Qualifier("rateDao")
	RateDao rateDao) {
		super(rateDao);
	}
	
	
	public Rate getRateByIds(Integer userId,Integer locationId) {
		return rateDao.getRateByIds(userId, locationId) ;
	}
}
