package ningbo.media.service.impl;

import ningbo.media.bean.UserLocations;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.UserLocationsDao;
import ningbo.media.service.UserLocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userLocationsService")
public class UserLocationsServiceImpl extends
		BaseServiceImpl<UserLocations, Integer> implements UserLocationsService {

	@Autowired
	public UserLocationsServiceImpl(@Qualifier("userLocationsDao")UserLocationsDao userLocationsDao){
		super(userLocationsDao);
	}
}
