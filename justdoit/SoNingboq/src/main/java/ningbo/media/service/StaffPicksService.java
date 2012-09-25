package ningbo.media.service;

import ningbo.media.bean.StaffPicks;
import ningbo.media.core.service.BaseService;

public interface StaffPicksService extends BaseService<StaffPicks, Integer> {

	public StaffPicks queryUniqueStaffPicks(Integer userId,Integer locationId); 
}
