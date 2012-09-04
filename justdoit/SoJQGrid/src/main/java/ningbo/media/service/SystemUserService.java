package ningbo.media.service;

import ningbo.media.domain.SystemUser;
import ningbo.media.repository.SystemUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class SystemUserService {

	@Autowired
	private SystemUserRepository repository;
	
	public Boolean create(SystemUser user) {
		user.getRole().setSystemUser(user);
		SystemUser saved = repository.save(user);
		if (saved == null) 
			return false;
		
		return true;
	}
	
	public Boolean update(SystemUser user) {
		SystemUser existingUser = repository.findByUsername(user.getUsername());
		if (existingUser == null) 
			return false;
		
		// Only firstName, lastName, and role fields are updatable
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());
		
		SystemUser saved = repository.save(existingUser);
		if (saved == null) 
			return false;
		
		return true;
	}
	
	public Boolean delete(SystemUser user) {
		SystemUser existingUser = repository.findByUsername(user.getUsername());
		if (existingUser == null) 
			return false;
		
		repository.delete(existingUser);
		SystemUser deletedUser = repository.findByUsername(user.getUsername());
		if (deletedUser != null) 
			return false;
		
		return true;
	}
}
