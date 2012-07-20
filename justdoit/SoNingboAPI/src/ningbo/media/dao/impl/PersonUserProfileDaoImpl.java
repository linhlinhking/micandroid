package ningbo.media.dao.impl;

import ningbo.media.bean.PersonUserProfile;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.PersonUserProfileDao;

import org.springframework.stereotype.Repository;

@Repository("personUserProfileDao")
public class PersonUserProfileDaoImpl extends
		BaseDaoImpl<PersonUserProfile, Integer> implements PersonUserProfileDao {

	public PersonUserProfileDaoImpl() {
		super(PersonUserProfile.class);
	}
}
