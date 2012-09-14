package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.bean.Location;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.service.LocationService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/location")
public class LocationController extends BaseController<Location> {

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
	JqgridPage<Location> getAll() {
		Pagination<Location> p = locationService.getAllByPage(1, 20);
		List<Location> lists = p.getList();
		if (null != lists && lists.size() > 0) {
			JqgridPage<Location> jq = new JqgridPage<Location>();
			jq.setTotal(String.valueOf(p.getTotalPage()));
			jq.setRecords(String.valueOf(p.getPageSize()));
			jq.setPage(String.valueOf(p.getPageNo()));
			jq.setRows(lists);

			return jq;
		}

		return new JqgridPage<Location>();
	}

	@Cacheable(value = "records")
	@RequestMapping(value = "/getPages", method = RequestMethod.POST)
	public  @ResponseBody JqgridPage<Location> getAllByPage() {
		List<Location> lists = locationService.getAll();
		if (null == lists) {
			new JqgridPage<Location>();
		}

		int totalCount = lists.size();
		if (totalCount < 0) {
			totalCount = 0;
		}

		Pagination<Location> p = new Pagination<Location>(1, 20, totalCount,
				lists);
		JqgridPage<Location> jq = new JqgridPage<Location>();
		jq.setPage(String.valueOf(p.getPageNo())) ;
		jq.setRecords(String.valueOf(p.getPageSize())); 
		jq.setTotal(String.valueOf(p.getTotalPage())) ;
		jq.setRows(lists) ;
		
		return jq ;
	}

	@Override
	public Long getResultSize() {
		return null;
	}

	@Override
	public List<Location> listResults(int firstResult, int maxResults) {
		return null;
	}

	@Override
	public void sortResults(List<Location> results, String field, String order) {

	}

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
