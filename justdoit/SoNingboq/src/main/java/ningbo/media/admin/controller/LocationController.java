package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.FirstCategoryService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SecondCategoryService;

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
	
	@Resource
	private FirstCategoryService firstCategoryService ;
	
	@Resource
	private SecondCategoryService secondCategoryService ;

	@RequestMapping
	public String getEventPage(Model model) {
		Long total = locationService.getTotalCount();
		model.addAttribute("total", total);
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
		if (id == null || id.length() < 0) {
			logger.error("Can't get the location's MD5Value.");
			return "location-list";
		}
		Location loc = locationService.get(Constant.MD5_FIELD, id);
		if (null != loc) {
			model.addAttribute("location", loc);

			return "location-list";
		}

		return "location-list";
	}

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	// @PathVariable("pageNumber") String pageNumber,
	// @PathVariable("pageSize") String pageSize
	@RequestMapping(value = "/getData/{pageNumber}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody
	List<Location> getDatasByPage(
			@PathVariable("pageNumber") String pageNumber,
			@PathVariable("pageSize") String pageSize) {
		if (pageNumber == null || pageSize == null) {
			return null;
		}
		Pagination<Location> p = locationService.getAllByPage(
				Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
		List<Location> lists = p.getList();
		if (null != lists && lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String toEditLocation(@PathVariable("id") String id, Model model) {
		if (null == id || id.length() < 0) {
			return "location-list";
		}
		Location location = locationService.get(Integer.valueOf(id));
		if (null != location) {
			List<FirstCategory> firstList = firstCategoryService.getAll() ;
			List<SecondCategory> secondList = secondCategoryService.getAll() ;
			
			model.addAttribute("fistList", firstList) ;
			model.addAttribute("secondList", secondList);
			model.addAttribute("location", location) ;
			return "location-edit" ;
		}

		return null;
	}

}
