package ningbo.media.core.rest;

import java.net.URI;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.sun.jersey.api.core.ResourceContext;

public abstract class BaseResource {

	private static final String IMAGE_FOLDER = "upload/images";
	private static final String DOWNLOAD_FOLDER = "upload/download";
	
	@Context
	private UriInfo uriInfo ;
	
	@Context
	private ServletContext servlet;

	@Context
	private ResourceContext resource;
	
	
	
	protected URI getServletBaseUrl() {
		return uriInfo.getBaseUri();
	}

	protected URI getResourceUrl() {
		return uriInfo.getAbsolutePath();
	}

	protected String getImageRealPath() {
		return servlet.getRealPath(IMAGE_FOLDER);
	}

	protected URI getImagePathUrl() {
		return getServerBaseUrl().resolve(IMAGE_FOLDER);
	}
	
	protected String getDownloadRealPath() {
		return servlet.getRealPath(DOWNLOAD_FOLDER);
	}

	protected URI getDownloadPathUrl() {
		return getServerBaseUrl().resolve(DOWNLOAD_FOLDER);
	}

	protected URI getServerBaseUrl() {
		return getServletBaseUrl().resolve(servlet.getContextPath() + "/");
	}

	public <T extends BaseResource> T getSubResource(Class<T> clazz) {
		return resource.getResource(clazz);
	}
	
}
