package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.bean.StaffPicks;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.StaffPicksDao;
import ningbo.media.service.StaffPicksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("staffPicksService")
public class StaffPicksServiceImpl extends BaseServiceImpl<StaffPicks, Integer>
		implements StaffPicksService {

	@Resource
	private StaffPicksDao staffPicksDao;

	@Autowired
	public StaffPicksServiceImpl(
			@Qualifier("staffPicksDao") StaffPicksDao staffPicksDao) {
		super(staffPicksDao);
	}

	public StaffPicks queryUniqueStaffPicks(Integer userId, Integer locationId) {
		String hql = "from StaffPicks as bean where 1=1 and bean.systemUser.id = ? and bean.location.id like ? ";
		List<StaffPicks> lists = staffPicksDao.findByHql(hql, userId,
				locationId);
		if (null == lists) {
			return null;
		}
		return lists.get(0);
	}
}
