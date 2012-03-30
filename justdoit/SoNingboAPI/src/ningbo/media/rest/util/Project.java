package ningbo.media.rest.util;

import java.util.ArrayList;
import java.util.List;

public class Project {

	private String name;

	private RemoteAddress remoteAddress = new RemoteAddress();

	private List<Module> modules = new ArrayList<Module>();

	private String contextUrl;

	private String restRootUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RemoteAddress getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(RemoteAddress remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getContextUrl() {
		return contextUrl;
	}

	public void setContextUrl(String contextUrl) {
		this.contextUrl = contextUrl;
	}

	public String getRestRootUrl() {
		return restRootUrl;
	}

	public void setRestRootUrl(String restRootUrl) {
		this.restRootUrl = restRootUrl;
	}

}
