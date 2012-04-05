package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Event;
import ningbo.media.core.service.BaseService;

public interface EventService extends BaseService<Event, Integer> {

	public List<Event> getUserEventsList(Integer userId);

	public List<Event> getLocationEventsList(Integer locationId);
}
