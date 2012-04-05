package ningbo.media.service;

import ningbo.media.bean.Rate;
import ningbo.media.core.service.BaseService;

public interface RateService extends BaseService<Rate, Integer>{
	
	public Rate getRateByIds(Integer userId,Integer locationId) ;
}
