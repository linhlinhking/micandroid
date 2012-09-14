package ningbo.media.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.bean.ModuleFile;
import ningbo.media.core.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/image")
public class ModuleFileController extends BaseController<ModuleFile>{

	
	
	public Long getResultSize() {
		return null;
	}

	public List<ModuleFile> listResults(int firstResult, int maxResults) {
		return null;
	}

	public void sortResults(List<ModuleFile> results, String field, String order) {
		
	}

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
