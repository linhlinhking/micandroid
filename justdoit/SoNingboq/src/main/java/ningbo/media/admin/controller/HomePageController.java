package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.bean.FirstCategory;
import ningbo.media.core.web.BaseController;
import ningbo.media.service.FirstCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/admin/home")
public class HomePageController extends BaseController {

	@Resource
	private  FirstCategoryService firstCategoryService ;
	
	
	@RequestMapping
	public void getEventPage(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("start....");
		FirstCategory fc = firstCategoryService.get(1) ;
		renderJson(response, fc, "encoding:utf-8");
	}

	@Override
	public Long getResultSize() {
		return null;
	}

	@Override
	public List listResults(int firstResult, int maxResults) {
		return null;
	}

	@Override
	public void sortResults(List results, String field, String order) {

	}

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
