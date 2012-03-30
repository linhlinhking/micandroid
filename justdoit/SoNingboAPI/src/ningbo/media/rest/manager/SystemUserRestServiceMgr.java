package ningbo.media.rest.manager;

import javax.ws.rs.core.MediaType;

import ningbo.media.rest.util.Project;
import ningbo.media.rest.util.RestConfig;
import ningbo.media.rest.util.RestConstant;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


public class SystemUserRestServiceMgr  {

	public static String BASE_URL = "";

	private static Client client = null;

	private static Project project = null;

	private static WebResource webResource = null;

	static {
		client = Client.create();
		project = RestConfig.getInstance().parserRestConfig();
		StringBuffer buffer = new StringBuffer();
		buffer.append(RestConstant.HTTP_PREFIX)
				.append(project.getRemoteAddress().getAddress())
				.append(project.getRemoteAddress().getPort())
				.append(project.getContextUrl())
				.append(project.getRestRootUrl());
		BASE_URL = buffer.toString();

		webResource = client.resource(BASE_URL + "/"
				+ RestConfig.getInstance().getUserMgrPath() + "/"
				+ RestConfig.getInstance().getUserMgrName());
	}

	public String getSystemUserByName(String name) {
		String userJson = webResource.path("/" + name).get(String.class) ;
		return userJson;
	}

	public String addSystemUser(String userJson) {
		String flag = webResource.type(MediaType.APPLICATION_JSON).post(String.class, userJson) ;
		return flag;
	}
	

}
