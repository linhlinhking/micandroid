package ningbo.media.website.controller;

import java.util.List;

import javax.annotation.Resource;
import ningbo.media.core.message.DataListMessage;
import ningbo.media.website.entity.FirstCategory;
import ningbo.media.website.service.FirstCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstCategoryController {
	
	@Resource
	private FirstCategoryService firstCategoryService ;

	@RequestMapping(value = "/category/first/all",method = RequestMethod.GET,headers="Accept=application/json,application/xml")
	public @ResponseBody DataListMessage<FirstCategory> getAllFirstCategory(){
		DataListMessage<FirstCategory> msg = new DataListMessage<FirstCategory>() ;
		try{
			List<FirstCategory> listAll = firstCategoryService.getAll() ;
			if(null != listAll){
				msg.setResult(true) ;
				msg.setData(listAll) ;
				return msg ;
			}
		}catch(Exception ex){
			ex.printStackTrace() ;
		}
		msg.setResult(false) ;
		msg.setMessage("No Data") ;
		return msg ;
	}
}
