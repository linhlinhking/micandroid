package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.StaffPicks;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.StaffPicksDao;

@Repository("staffPicksDao")
public class StaffPicksDaoImpl extends BaseDaoImpl<StaffPicks, Integer>
		implements StaffPicksDao {

	public StaffPicksDaoImpl() {
		super(StaffPicks.class);
	}
}
