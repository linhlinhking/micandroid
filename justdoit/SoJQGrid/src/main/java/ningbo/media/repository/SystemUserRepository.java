package ningbo.media.repository;

import ningbo.media.domain.SystemUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

	SystemUser findByUsername(String username);

	Page<SystemUser> findByUsernameLike(String username, Pageable pageable);

	Page<SystemUser> findByFirstNameLike(String firstName, Pageable pageable);

	Page<SystemUser> findByLastNameLike(String lastName, Pageable pageable);

	Page<SystemUser> findByFirstNameLikeAndLastNameLike(String firstName,
			String lastName, Pageable pageable);

	@Query("select u from t_user u where u.role.role = :role")
	Page<SystemUser> findByRole(@Param("role") Integer role, Pageable pageable);
}
