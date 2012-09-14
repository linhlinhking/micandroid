package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.admin.util.Result;
import ningbo.media.bean.FirstCategory;
import ningbo.media.core.web.BaseController;
import ningbo.media.service.FirstCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/admin/home")
public class HomePageController extends BaseController {

	@Resource
	private  FirstCategoryService firstCategoryService ;
	
	
	@RequestMapping
	public String getEventPage(Model model) {
		logger.debug("start....");
		try {
			Result<FirstCategory> rs = firstCategoryService.getCategoryById(1) ;
			if(rs.isSuccess()){
				model.addAttribute("firstCategory", rs.getDefaultModel()) ;
				model.addAttribute("msg", "The get category operation is success!");
			}else{
				model.addAttribute("msg", "The get category operation is fail!");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			model.addAttribute("msg", "get category operation is exception!");
		}
		
		
		return "msg" ;
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
