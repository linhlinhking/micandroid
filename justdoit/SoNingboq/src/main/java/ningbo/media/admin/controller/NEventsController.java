package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.bean.EventCategory;
import ningbo.media.bean.EventDate;
import ningbo.media.bean.NEvents;
import ningbo.media.bean.enums.RepeatType;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.service.EventCategoryService;
import ningbo.media.service.EventDateService;
import ningbo.media.service.NEventsService;
import ningbo.media.util.MD5;
import ningbo.media.web.util.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/event")
public class NEventsController extends BaseController<NEvents> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private NEventsService nEventsService;

	@Resource
	private EventCategoryService eventCategoryService;
	
	@Resource
	private EventDateService eventDateService ;

	@Cacheable(value = "records")
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public @ResponseBody
	JqgridPage<NEvents> getAll(HttpServletRequest request)
			throws ServiceException {
		int pages = Integer.valueOf(request.getParameter("page"));
		int rowNum = Integer.valueOf(request.getParameter("rows"));
		Pagination<NEvents> p = nEventsService.getAllByPage(pages, rowNum);
		List<NEvents> lists = p.getList();
		if (null != lists && lists.size() > 0) {
			JqgridPage<NEvents> jq = new JqgridPage<NEvents>();
			jq.setTotal(String.valueOf(p.getTotalPage()));
			jq.setRecords(String.valueOf(p.getPageSize()));
			jq.setPage(String.valueOf(p.getPageNo()));
			jq.setRows(lists);
			return jq;
		}
		logger.error("No Data.");
		return new JqgridPage<NEvents>();
	}

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		List<EventCategory> eventCategorys = eventCategoryService.getAll();
		model.addObject("eventCategorys", eventCategorys);
		model.setViewName("event-list");
		return model;
	}
	

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		List<EventCategory> eventCategorys = eventCategoryService.getAll();
		model.addObject("eventCategorys", eventCategorys);
		model.setViewName(SessionHandler.verifySession(request,
				"event-add"));
		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(HttpServletRequest request,
			@RequestParam("eventCategoryId") Integer eventCategoryId,
			@RequestParam("repeatTypeItem") Integer repeatTypeItem,
			@RequestParam("eventDates_repeatType") String repeatType,
			@RequestParam("eventDates_startDate") String startDate,
			@RequestParam("eventDates_endDate") String endDate,
			@RequestParam("eventDates_frequency") String frequency,
			@RequestParam("eventDates_repeatValue") String repeatValue,
			@RequestParam("eventDates_startTime") String startTime,
			@RequestParam("eventDates_endTime") String endTime,

			@ModelAttribute("eventAttr") NEvents nEvent) {

		nEvent = nEventsService.saveOrUpdate(nEvent);
		Integer ids = nEvent.getId() ;
		String eventMd5 = MD5.calcMD5(String.valueOf(ids)) ;
		nEvent.setMd5Value(eventMd5) ;
		nEvent = nEventsService.saveOrUpdate(nEvent);
		
		EventCategory category = eventCategoryService
				.get(eventCategoryId);
		if(null != category){
			nEvent.setEventCategory(category) ;
		}
		EventDate eDate = new EventDate();
		eDate.setEndDate(endDate) ;
		eDate.setStartDate(startDate) ;
		eDate.setStartTime(startTime) ;
		eDate.setEndTime(endTime) ;
		
		if(1 == repeatTypeItem){
			eDate.setRepeat(true) ;
			if(null != repeatType && repeatType.length() > 0){
				eDate.setRepeatType(RepeatType.valueOf(repeatType.toUpperCase())) ;
			}
			eDate.setFrequency(frequency) ;
			eDate.setRepeatValue(repeatValue) ;
			
		}else{
			eDate.setRepeat(false) ;
		}
		eDate.setnEvents(nEvent) ;
		eDate = eventDateService.saveOrUpdate(eDate) ;		

		return SessionHandler.verifySession(request, "admin/event");
	}
}
