package ningbo.media.admin.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.bean.NEvents;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.service.NEventsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/event")
public class NEventsController extends BaseController<NEvents>{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private NEventsService nEventsService ;
	
	@RequestMapping
	public String getEventPage(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Loading list...") ;
		return "event-list";
	}
	
	@Cacheable(value = "records")
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public @ResponseBody
	JqgridPage<NEvents> getAll(HttpServletRequest request) throws ServiceException {
		int pages=Integer.valueOf(request.getParameter("page"));
		int rowNum=Integer.valueOf(request.getParameter("rows"));
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
		logger.error("No Data.") ;
		return new JqgridPage<NEvents>();
	}

	
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
