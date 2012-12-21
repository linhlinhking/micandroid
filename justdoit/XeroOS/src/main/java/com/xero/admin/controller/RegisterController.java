
package com.xero.admin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.xero.admin.bean.type.PlanType;
import com.xero.admin.service.SystemUserService;
import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseMessage;
import com.xero.core.controller.BaseController;
import com.xero.core.security.MD5Util;
import com.xero.website.bean.Company;
import com.xero.website.bean.CompanyUser;
import com.xero.website.service.CompanyService;
import com.xero.website.service.CompanyUserService;


@Controller
public class RegisterController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private CompanyService companyService;

	@Resource
	private CompanyUserService companyUserService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView toRegister(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("register");
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseMessage doRegister(HttpServletRequest request,
			@RequestParam("companyName") String companyName,
			@RequestParam("uemail") String uemail,
			@RequestParam("password") String password,
			@RequestParam("linkXero") Integer linkXero) throws Exception {
		ResponseMessage resMsg = new ResponseMessage();
		try {
			Company cmp = new Company();

			cmp.setCompanyName(companyName);
			cmp.setCreateDateTime(new Date());
			cmp.setPlanId(PlanType.FREE.getId()) ;
			Integer companyId = companyService.save(cmp);
			
			SystemUser tmp = systemUserService.get(SystemUser.UEMAIL, uemail);
			if (null != tmp) {
				resMsg.setResult(false);
				resMsg.setUrl("register");
				return resMsg;
			}

			if ("" != password && null != password) {
				password = MD5Util.calcMD5(password);
			}

			SystemUser sysUser = new SystemUser();
			String username = "";

			Date expiredDate = DateUtil.addDate(new Date(), 30);

			if ("" != uemail && uemail != null) {
				String temp[] = uemail.split("@");
				username = temp[0];
			}
			sysUser.setJoinInType(JoinUsType.REGISTRATION.toString());
			sysUser.setCreateDateTime(new Date());
			sysUser.setPassword(password);
			sysUser.setPlanId(1);
			sysUser.setExpiredDateTime(expiredDate);
			sysUser.setUemail(uemail);
			sysUser.setUsername(username);
			sysUser.setLinkXero(linkXero) ;
			
			Integer userId = systemUserService.save(sysUser);

			CompanyUser link = new CompanyUser();
			link.setCompanyId(companyId);
			link.setUserId(userId);
			link.setCreateDateTime(new Date());
			
			companyUserService.save(link);

			setSession(request, sysUser, false);

			resMsg.setResult(true);
			resMsg.setUrl("contact");
		} catch (Exception ex) {
			logger.error("Current " + uemail + "Registe Error.", ex);
			resMsg.setResult(false);
			resMsg.setStatusCode(500);
			resMsg.setMessage("Registration Error.");
			resMsg.setUrl("register");
		}
		return resMsg;
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseMessage checkEmail(
			@RequestParam("currentEmail") String uemail) {
		ResponseMessage res = systemUserService.checkEmail(uemail);
		return res;
	}

}
