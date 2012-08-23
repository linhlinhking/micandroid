package ningbo.media.dao;

import ningbo.media.bean.Rate;
import ningbo.media.core.dao.BaseDao;

public interface RateDao extends BaseDao<Rate, Integer> {
	
	public Rate getRateByIds(Integer userId,Integer locationId) ;

}
