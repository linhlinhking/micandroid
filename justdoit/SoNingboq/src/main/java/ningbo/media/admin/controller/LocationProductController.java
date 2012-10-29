package ningbo.media.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.bean.LocationProduct;
import ningbo.media.core.web.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/location/product")
public class LocationProductController extends BaseController<LocationProduct>{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Loading The List Page") ;
		ModelAndView model = new ModelAndView() ;
		model.setViewName("location-product-list") ;
		return null;
	}
	
	@RequestMapping("/add")
	public String toAdd(){
		
		return null ;
	}
	
	public String doAdd(){
		return null ;
	}
	
	@RequestMapping("/edit")
	public String toEdit(){
		return null ;
	}
	
	public String doEdit(){
		return null ;
	}
	
}
