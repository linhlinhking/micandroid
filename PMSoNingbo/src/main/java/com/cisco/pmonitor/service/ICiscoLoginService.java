package com.cisco.pmonitor.service;

import com.cisco.pmonitor.service.util.Result;

public interface ICiscoLoginService {

	/**
	 * check the authorization of the user in cisco domain network.
	 * @param username
	 * @param password
	 * @return
	 */
	public Result<Boolean> login(String username, String password);
}
