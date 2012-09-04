package ningbo.media.service;

import java.util.List;

import ningbo.media.domain.Event;

public interface IEventService {

	public Event create(Event event);

	public Event read(Long id);

	public List<Event> readAll();

	public Event update(Event event);

	public Event delete(Long id);
}
