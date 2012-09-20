package ningbo.media.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ningbo.media.admin.jqgrid.JqgridPage;
import ningbo.media.bean.SystemUser;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.service.SystemUserService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/user")
public class SystemUserController extends BaseController<SystemUser>{
	
	@Resource
	private SystemUserService systemUserService ;

	
	@RequestMapping
	public String getEventPage(HttpServletRequest request,
			HttpServletResponse response) {
		return "user-list" ;
	}
	
	@Cacheable(value = "records")
	@RequestMapping(value="/getAll",method = RequestMethod.POST)
	public @ResponseBody JqgridPage<SystemUser> getAll(){
		Pagination<SystemUser> p = systemUserService.getAllByPage(1, 20) ;
		List<SystemUser> lists = p.getList() ;
		if(null !=lists && lists.size() > 0 ){
			JqgridPage<SystemUser> jq = new JqgridPage<SystemUser>();
			jq.setTotal(String.valueOf(p.getTotalPage())) ;
			jq.setRecords(String.valueOf(p.getPageSize())) ;
			jq.setPage(String.valueOf(p.getPageNo())) ;
			jq.setRows(lists) ;
			
			return jq ;
		}
		
		return new JqgridPage<SystemUser>() ;
	}

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
