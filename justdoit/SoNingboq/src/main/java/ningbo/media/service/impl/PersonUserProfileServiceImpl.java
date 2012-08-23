package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.PersonUserProfile;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.PersonUserProfileDao;
import ningbo.media.service.PersonUserProfileService;

@Service("personUserProfileService")
public class PersonUserProfileServiceImpl extends
		BaseServiceImpl<PersonUserProfile, Integer> implements
		PersonUserProfileService {

	@Autowired
	public PersonUserProfileServiceImpl(@Qualifier("personUserProfileDao")
	PersonUserProfileDao personUserProfileDao) {
		super(personUserProfileDao);
	}
}
