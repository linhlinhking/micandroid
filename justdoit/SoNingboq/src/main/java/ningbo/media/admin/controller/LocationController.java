package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.admin.vo.LocationListVO;
import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.service.FirstCategoryService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SecondCategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private FirstCategoryService firstCategoryService;

	@Resource
	private SecondCategoryService secondCategoryService;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long total = locationService.getTotalCount();
		ModelAndView model = new ModelAndView();
		model.addObject("total", total);
		model.setViewName("location-list");
		return model;
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

	@RequestMapping(value = "/getData/{pageNumber}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody
	LocationListVO getDatasByPage(
			@PathVariable("pageNumber") String pageNumber,
			@PathVariable("pageSize") String pageSize) {
		
		if (pageNumber == null || pageSize == null) {
			return null;
		}
		Pagination<Location> p = locationService.getAllByPage(
				Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
		List<Location> lists = p.getList();

		LocationListVO locationVO = new LocationListVO();

		if (null != lists && lists.size() > 0) {
			locationVO.setLocations(lists);
			locationVO.setPageNumber(p.getPageNo());
			locationVO.setPageSize(p.getPageSize());

			return locationVO;
		}
		return null;
	}

	@RequestMapping(value = "/first/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<SecondCategory> getSecondCategoryByFirstId(
			@PathVariable("id") String id) {
		if (null == id) {
			return null;
		}
		FirstCategory first = firstCategoryService.get(Integer.valueOf(id));
		List<SecondCategory> lists = null;
		if (null != first) {
			lists = first.getSecondCategorys();
		}
		return lists;
	}

	@RequestMapping(value = "/{pageNumber}/{pageSize}/edit/{id}", method = RequestMethod.GET)
	public String toEditLocation(@PathVariable("pageNumber") String pageNumber,
			@PathVariable("pageSize") String pageSize,
			@PathVariable("id") String id, Model model) {
		if (null == id || id.length() < 0) {
			return "location-list";
		}
		Location location = locationService.get(Integer.valueOf(id));
		if (null != location) {
			List<FirstCategory> firstList = firstCategoryService.getAll();
			List<SecondCategory> secondList = null;

			List<SecondCategory> tempSecond = location.getSecondCategorys();
			if (tempSecond != null && tempSecond.size() > 0) {
				SecondCategory sc = tempSecond.get(0);
				FirstCategory fc = sc.getFirstCategory();
				model.addAttribute("selectFirst", fc);
				model.addAttribute("selectSecond", sc);
				secondList = fc.getSecondCategorys();
				model.addAttribute("secondList", secondList);
				model.addAttribute("pageNumber", pageNumber) ;
				model.addAttribute("pageSize", pageSize) ;
			}

			model.addAttribute("firstList", firstList);
			model.addAttribute("location", location);
			return "location-edit";
		}
		return null;
	}

	@RequestMapping(value = "/{pageNumber}/{pageSize}/edit/{id}", method = RequestMethod.POST)
	public String doEditLocation(@PathVariable("pageNumber") String pageNumber,
			@PathVariable("pageSize") String pageSize,
			@ModelAttribute("locationAttr") Location location,
			@PathVariable Integer id) {
		// if the name_cn is changed.and the pinying need to change it.but now i
		// don't change it.
		// if any problem,please change here.
		locationService.saveOrUpdate(location);
		return "redirect:/admin/location";
	}

}
