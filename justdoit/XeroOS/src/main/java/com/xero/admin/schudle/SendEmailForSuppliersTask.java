package com.xero.admin.schudle;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.service.SystemUserService;

@Component
public class SendEmailForSuppliersTask {

	@Resource
	private SystemUserService systemUserService;

	
	/**
	 * 
	 *  (cron="0 0 6 ? * Tue") 
	 */
	@Scheduled(cron = "0/5 * *  * * ? ")
	public void doSomethingWithDelay() {
		// System.out.println("5 seconds !");
		SystemUser sysUser = systemUserService.get(1);
		//System.out.println(sysUser.getUemail());
	}
}
