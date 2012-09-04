package ningbo.media.repository.jpa;

import ningbo.media.domain.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, Long> {

}
