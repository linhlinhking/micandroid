package ningbo.media.service;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Event;

import org.junit.Test;


public class EventServiceTest extends BaseTest{

	@Resource
	private EventService eventService ;
	
	@Test
	public void testUserEventsList(){
		List<Event> events = eventService.getUserEventsList(28) ;
		for(Event e : events){
			System.out.println(e.getSubject()) ;
		}
	}
}
