package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.bean.Location;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.LocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/location")
public class LocationController extends BaseController<Location> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private LocationService locationService;

	@RequestMapping
	public String getEventPage(HttpServletRequest request,
			HttpServletResponse response) {
		return "location-list";
	}

	@Cacheable(value = "records")
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public @ResponseBody
	JqgridPage<Location> getAll(HttpServletRequest request) {
		int pages = Integer.valueOf(request.getParameter("page"));
		int rowNum = Integer.valueOf(request.getParameter("rows"));
		Pagination<Location> p = locationService.getAllByPage(pages, rowNum);
		List<Location> lists = p.getList();
		if (null != lists && lists.size() > 0) {
			JqgridPage<Location> jq = new JqgridPage<Location>();
			jq.setTotal(String.valueOf(p.getTotalPage()));
			jq.setRecords(String.valueOf(p.getPageSize()));
			jq.setPage(String.valueOf(p.getPageNo()));
			jq.setRows(lists);
			return jq;
		}
		logger.error("No Data.");
		return new JqgridPage<Location>();
	}

	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") String id, Model model)
			throws Exception {
		if(id == null || id.length() < 0){
			logger.error("Can't get the location's MD5Value.") ;
			return "location-list" ;
		}
		Location loc = locationService.get(Constant.MD5_FIELD, id) ;
		if(null != loc){
			model.addAttribute("location",loc);
			
			return "location-list";
		}
		
		return "location-list" ;
	}
	
	

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
