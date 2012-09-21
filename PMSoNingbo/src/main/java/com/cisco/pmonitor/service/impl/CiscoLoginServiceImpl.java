package com.cisco.pmonitor.service.impl;

import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.service.ICiscoLoginService;
import com.cisco.pmonitor.service.util.Result;

public class CiscoLoginServiceImpl implements ICiscoLoginService {

	@Override
	public Result<Boolean> login(String username, String password) {
		Result<Boolean> rs = new Result<Boolean>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return rs;
		}
		username = username.trim();
		password = password.trim();
		int count = 0;
		String strLDAPUrl = null;
		Hashtable<String, String> ldapProperties = new Hashtable<String, String>();
		try {
			ldapProperties.put("java.naming.factory.initial",
					"com.sun.jndi.ldap.LdapCtxFactory");
			ldapProperties.put("java.naming.security.authentication", "simple");
			ldapProperties.put("java.naming.security.principal", "uid="
					+ username
					+ ",ou=active,ou=employees,ou=people,o=cisco.com");
			ldapProperties.put("java.naming.security.credentials", password);
			strLDAPUrl = "ldap://ldap.cisco.com:389";
			ldapProperties.put("java.naming.provider.url", strLDAPUrl);
			DirContext authContext = new InitialDirContext(ldapProperties);
			SearchControls ctls = new SearchControls();
			ctls.setReturningObjFlag(true);
			ctls.setSearchScope(2);
            String filter = "(&(uid="+ username + "))";

			NamingEnumeration<SearchResult> answer = 
					authContext.search("o=cisco.com",filter, ctls);
			for (count = 0; answer.hasMore(); count++) {
				answer.next();
			}

			authContext.close();
		} catch (NamingException nEx) {
		} catch (Exception ex) {
		}
		rs.setSuccess(count != 0);
		rs.setDefaultModel(count != 0);
		return rs;
	}

}
