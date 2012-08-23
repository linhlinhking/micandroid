package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Rate;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.RateDao;

@Repository("rateDao")
public class RateDaoImpl extends BaseDaoImpl<Rate, Integer> implements RateDao {

	public RateDaoImpl() {
		super(Rate.class);
	}

	public Rate getRateByIds(Integer userId, Integer locationId) {
		try {
			String hql = "from Rate m where 1=1 and m.systemUser.id = ? and m.location.id = ? ";
			Rate rate = (Rate) findUnique(hql, userId, locationId);
			if (rate == null) {
				return null;
			} else {
				return rate;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
