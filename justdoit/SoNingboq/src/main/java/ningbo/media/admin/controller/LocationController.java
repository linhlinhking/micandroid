package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.admin.response.ResponseObject;
import ningbo.media.admin.response.ResponseVO;
import ningbo.media.admin.vo.LocationListVO;
import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.rest.dto.SecondCategoryData;
import ningbo.media.rest.util.Constant;
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
		Pagination<Location> datas = locationService.getAllByPage(1, 5) ;
		
		ModelAndView model = new ModelAndView();
		model.addObject("total", total);
		model.addObject("datas", datas) ;
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

	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public @ResponseBody ResponseObject<String> deleteLocationByMd5Value(HttpServletRequest request){
		ResponseObject<String> res = new ResponseObject<String>() ;
		String md5 = request.getParameter("md5Value") ;
		//删除相关的图片，关注，评论
		Location location = locationService.get(Constant.MD5_FIELD, md5) ;
		if(null != location){
			locationService.delete(location) ;
			res.setSuccess(true) ;
			
		}else{
			res.setSuccess(false) ;
		}
		
		return res ;
	}
	
	@RequestMapping(value = "/getFirstCatgory")
	public @ResponseBody
	ResponseVO<FirstCategory> getAllFirstCategory() {
		ResponseVO<FirstCategory> res = new ResponseVO<FirstCategory>();
		List<FirstCategory> lists = firstCategoryService.getAll();
		if (null != lists) {
			res.setData(lists);
			res.setResult(true);
		} else {
			res.setMessage("没有数据.");
			res.setResult(false);

		}
		return res;
	}

	@RequestMapping(value = "/getSecondCategory/{ids}")
	public @ResponseBody
	ResponseVO<SecondCategoryData> getSecondCategoryById(
			@PathVariable("ids") Integer id) {
		ResponseVO<SecondCategoryData> res = new ResponseVO<SecondCategoryData>();
		List<SecondCategoryData> lists = secondCategoryService
				.querySecondCategoryData(id);
		if (null != lists) {
			res.setData(lists);
			res.setResult(true);
		} else {
			res.setMessage("没有数据.");
			res.setResult(false);
		}
		return res;
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
	
	@RequestMapping(value = "/getLocation", method = RequestMethod.GET)
	public ModelAndView toList(
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

			return null;
		}
		return null;
	}

	@RequestMapping(value = "/getData/{categoryId}/{pageNumber}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody
	LocationListVO getDatasPageById(
			@PathVariable("categoryId") Integer categoryId,
			@PathVariable("pageNumber") String pageNumber,
			@PathVariable("pageSize") String pageSize) {

		if (pageNumber == null || pageSize == null) {
			return null;
		}
		Pagination<Location> p = locationService.getLocationsById(
				Integer.valueOf(pageNumber), Integer.valueOf(pageSize), categoryId);

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
				model.addAttribute("pageNumber", pageNumber);
				model.addAttribute("pageSize", pageSize);
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

	@RequestMapping(value = "/search/{searchName}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseVO<Location> searchLocationsByPage(
			@PathVariable("searchName") String searchName) {
		List<Location> lists = locationService.queryLocationByName(searchName);
		ResponseVO<Location> resVO = new ResponseVO<Location>();
		if (null != lists) {
			if (lists.size() > 10) {
				resVO.setMessage("数据量太大，请输入更详细的位置信息.");
				resVO.setResult(false);
			} else {
				resVO.setResult(true);
				resVO.setData(lists);
				resVO.setMessage("OK");
			}
			return resVO;
		}
		resVO.setMessage("没有数据.");
		resVO.setResult(false);

		return resVO;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView toSearchLocations(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("location-search");
		return model;
	}

}
