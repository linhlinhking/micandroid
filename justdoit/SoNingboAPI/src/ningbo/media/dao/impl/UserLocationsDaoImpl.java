package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.UserLocations;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.UserLocationsDao;

@Repository("userLocationsDao")
public class UserLocationsDaoImpl extends BaseDaoImpl<UserLocations, Integer>
		implements UserLocationsDao {
	
	public UserLocationsDaoImpl(){
		super(UserLocations.class);
	}

}
