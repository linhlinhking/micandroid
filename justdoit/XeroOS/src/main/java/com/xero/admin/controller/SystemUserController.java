package com.xero.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.bean.type.JoinUsType;
import com.xero.admin.bean.type.MailType;
import com.xero.admin.service.SystemUserService;
import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.Response.ResponseMessage;
import com.xero.core.controller.BaseController;
import com.xero.core.email.SendManagerService;
import com.xero.core.security.MD5Util;
import com.xero.core.util.ApplicationContextUtil;
import com.xero.core.web.WebConstants;
import com.xero.website.bean.Company;
import com.xero.website.bean.CompanyUser;
import com.xero.website.service.CompanyService;
import com.xero.website.service.CompanyUserService;

@Controller
public class SystemUserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private CompanyUserService companyUserService;

	@Resource
	private CompanyService companyService;

	private SendManagerService sendMgrService = (SendManagerService) ApplicationContextUtil
			.getContext().getBean("sendMail");

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (null == session) {
			model.setViewName("redirect:/");
			return model;
		}
		SystemUser sysUser = (SystemUser) session
				.getAttribute(WebConstants.XERO_USER_SESSION);
		if (null != sysUser) {
			Date ep = sysUser.getExpiredDateTime();
			if (ep.before(new Date())) {
				model.setViewName("redirect:/payment");
			} else {
				Integer userId = sysUser.getId();
				Company cmp = companyService.getCompanyByUserId(userId);
				if (null != cmp) {
					Integer companyId = cmp.getId();
					Integer planId = cmp.getPlanId();
					ResponseCollection<SystemUser> resLists = systemUserService
							.getUsersByCompanyId(companyId);
					List<SystemUser> listUsers = resLists.getData();
					Integer numberUsers = 1;
					if (null != listUsers && listUsers.size() > 0) {
						numberUsers = listUsers.size();
					}
					int leftDays = DateUtil.daysOfTwoDate(new Date(), ep);
					model.addObject("allowsRegisteredUsers", numberUsers);
					model.addObject("expiredDate", ep);
					model.addObject("leftDays", leftDays);
					model.addObject("currentCompany", companyId);
					model.addObject("planId", planId);
					model.setViewName("/manage-user");

				} else {
					model.setViewName("redirect:/");
				}

			}
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}

	@RequestMapping(value = "/user-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<SystemUser> getAllUser(
			HttpServletRequest request, @RequestParam("planId") Integer planId,
			@RequestParam("companyId") Integer companyId) {
		ResponseCollection<SystemUser> res = new ResponseCollection<SystemUser>();
		// HttpSession session = request.getSession(false);
		// if(null == session){
		// res.setResult(false);
		// res.setData(null);
		// res.setMessage("No Authorization.") ;
		// }else{
		res = systemUserService.getUsersByCompanyId(companyId);
		// }
		return res;
	}

	@RequestMapping(value = "/user-add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SystemUser> doAddUser(
			@RequestParam(value = "name", required = true) String username,
			@RequestParam(value = "uemail", required = true) String uemail,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "expiredDate", required = true) String expiredDate,
			@RequestParam(value = "planId", required = true) Integer planId,
			@RequestParam(value = "companyId", required = true) Integer companyId) {
		ResponseEntity<SystemUser> res = new ResponseEntity<SystemUser>(false);
		SystemUser user = null;
		try {
			if (null != userId) {
				user = systemUserService.get(userId);
				if (null == user) {
					res.setResult(false);
					res.setData(null);
					res.setJson(null);
				} else {
					username = ("" == username) ? user.getUsername() : username;
					uemail = ("" == uemail) ? user.getUemail() : uemail;

					String newPsd = MD5Util.calcMD5(password);

					user.setUsername(username);
					user.setUemail(uemail);
					user.setPassword(newPsd);
					user.setUpdateDateTime(new Date());

					user = systemUserService.saveOrUpdate(user);
					res.setData(user);
					res.setResult(true);
					res.setJson(null);
				}
			} else {
				user = new SystemUser();
				String newPsd = MD5Util.calcMD5(password);
				Date expDate = DateUtil.strToDate(expiredDate,
						DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
				// DateUtil.millisToDate(expiredDate);

				user.setUsername(username);
				user.setUemail(uemail);
				user.setPassword(newPsd);
				user.setJoinInType(JoinUsType.INVITATION.toString());
				user.setCreateDateTime(new Date());
				user.setPlanId(planId);
				user.setExpiredDateTime(expDate);

				user = systemUserService.saveOrUpdate(user);
				Integer uId = user.getId();

				CompanyUser link = new CompanyUser();
				link.setCompanyId(companyId);
				link.setUserId(uId);
				link.setCreateDateTime(new Date());

				companyUserService.save(link);

				Company c = companyService.get(companyId);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("username", username);
				params.put("uPassword", password);
				if (null != c) {
					String cmpName = c.getCompanyName();
					params.put("uCompany", cmpName);
				}

				try {
					sendMgrService.sendHtmlMail(MailType.MAILNEWUSER, uemail,
							null, params);
				} catch (Exception ex) {
					logger.error("Mail New User Error.User's Email is "
							+ uemail, ex);
				}

				res.setData(user);
				res.setResult(true);
				res.setJson(null);
			}

		} catch (Exception ex) {
			logger.error("Add User Error.", ex);
			res.setResult(false);
		}

		return res;
	}

	@RequestMapping(value = "/user-delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage doDelete(HttpServletRequest request,
			@RequestParam("userId") Integer id) {
		ResponseMessage res = new ResponseMessage();
		try {
			SystemUser sysUser = systemUserService.get(id);
			HttpSession session = request.getSession(false);
			if (null != sysUser) {
				SystemUser currentSignUser = (SystemUser) session
						.getAttribute(WebConstants.XERO_USER_SESSION);
				if (sysUser.getId().equals(currentSignUser.getId())) {
					res.setResult(true);
					res.setStatusCode(600);
				} else {
					sysUser.setDeleted(true);
					systemUserService.saveOrUpdate(sysUser);
					res.setResult(true);
				}
			} else {
				res.setResult(false);
			}
			res.setUrl("/user");
		} catch (Exception ex) {
			logger.error("Delete User Error.User'Id is" + id, ex);
		}
		return res;
	}

	@RequestMapping(value = "/user-plan", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage updateUserPlan(
			@RequestParam(value = "planId", required = true) Integer planId,
			@RequestParam(value = "companyId", required = true) Integer companyId,
			HttpServletRequest request) {
		ResponseMessage msg = new ResponseMessage();
		try {
			Company cmp = companyService.get(companyId);
			if (null != cmp && null != planId) {
				ResponseCollection<SystemUser> res = systemUserService
						.getUsersByCompanyId(companyId);
				List<SystemUser> lists = res.getData();
				Integer userNumber = 0;
				if (null != lists) {
					userNumber = lists.size();
				}

				if (planId == 3 || (planId == 2 && userNumber <= 5)) {
					cmp.setPlanId(planId);
					cmp.setExpiredDate(DateUtil.addDate(new Date(), 30));
					cmp = companyService.saveOrUpdate(cmp);
					msg.setResult(true);
					msg.setStatusCode(200);
				} else {
					msg.setResult(false);
					msg.setStatusCode(603);
				}

			} else {
				msg.setResult(false);
				msg.setStatusCode(600);
			}

		} catch (Exception ex) {
			logger.error("Add User Error.", ex);
			msg.setResult(false);
			msg.setStatusCode(500);
		}
		msg.setUrl("/payment");
		return msg;
	}

}
