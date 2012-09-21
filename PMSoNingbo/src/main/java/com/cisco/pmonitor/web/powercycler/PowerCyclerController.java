package com.cisco.pmonitor.web.powercycler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.query.PowerCyclerQuery;
import com.cisco.pmonitor.service.IPowerCyclerService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("/powercycler")
public class PowerCyclerController {
	
	protected final Logger logger = Logger.getLogger(PowerCyclerController.class);
	private IPowerCyclerService powerCyclerService;

	public void setPowerCyclerService(IPowerCyclerService powerCyclerService) {
		this.powerCyclerService = powerCyclerService;
	}

	@RequestMapping(value = "/powercycler_view", method = RequestMethod.GET)
	public String toPowerCyclerView(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "powercycler/powercycler_view");
	}
	
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doPowerCyclerView(PowerCyclerQuery query) {
		Result<Map<String, Object>> rs;
		try {
			rs = powerCyclerService.loadPowerCyclersByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("PowerCyclerController.doPowerCyclerView : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/powercycler_form", method = RequestMethod.POST)
	public String doForm(PowerCyclerQuery query, Model model) {
		String linkUrl = "powercycler/query_list?name=" + query.getName() + 
						 "&type=" + query.getType() +
						 "&status=" + query.getStatus()+
						 "&protocol=" + query.getProtocol() + 
						 "&host=" + query.getHost();
		model.addAttribute("linkUrl", linkUrl);
		return "linkurl";
	}
	
	@RequestMapping(value = "/powercycler_add", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "powercycler/powercycler_add");
	}
	
	@RequestMapping(value = "/powercycler_add", method = RequestMethod.POST)
	public String doAdd(PowerCyclerDo powerCyclerDo, Model model) {
		Result<Integer> rs;
		try {
			rs = powerCyclerService.addPowerCycler(powerCyclerDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The add powercycler operation is success.");
			}
			else {
				model.addAttribute("msg", "The add powercycler operation is error.");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The add powercycler operation is error.");
			logger.error("PowerCyclerController.doAdd : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/powercycler_edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam int id, HttpServletRequest request, Model model) {
		Result<PowerCyclerDo> rs;
		try {
			rs = powerCyclerService.findPowerCyclerById(id);
			if(rs.isSuccess()) {
				model.addAttribute("powerCyclerDo", rs.getDefaultModel());
			}
		} catch (ServiceException e) {
			logger.error("PowerCyclerController.toEdit : ", e);
		}
		return SessionHandler.verifySession(request, "powercycler/powercycler_edit");
	}
	
	@RequestMapping(value = "/powercycler_edit", method = RequestMethod.POST)
	public String doEdit(PowerCyclerDo powerCyclerDo, Model model) {
		Result<Integer> rs;
		try {
			rs = powerCyclerService.updatePowerCycler(powerCyclerDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The update powercycler operation is success.");
			}
			else {
				model.addAttribute("msg", "The update powercycler operation is error.");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The update powercycler operation is error.");
			logger.error("PowerCyclerController.doAdd : ", e);
		}
		return "msg";
	}
	@RequestMapping(value = "/powercycler_del", method = RequestMethod.POST)
	public String doDel(@RequestParam int id, Model model) {
		Result<Integer> rs;
		try {
			rs = powerCyclerService.deletePowerCycler(id);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The update powercycler operation is success.");
			}
			else {
				model.addAttribute("msg", "The update powercycler operation is error.");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The update powercycler operation is error.");
			logger.error("PowerCyclerController.doAdd : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/query_all", method = RequestMethod.POST)
	@ResponseBody
	public List<PowerCyclerDo> queryAll() {
		Result<List<PowerCyclerDo>> rs;
		try {
			rs = powerCyclerService.loadAllPowerCyclers();
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("PowerCyclerController.queryAll : ", e);
		}
		return null;
	}
}
