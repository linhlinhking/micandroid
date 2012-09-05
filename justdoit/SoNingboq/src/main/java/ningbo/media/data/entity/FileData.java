package ningbo.media.data.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;

@XmlType(name = "file", namespace = WsConstants.NS, propOrder = { "name",
		"url", "size", "thumbnail_url", "delete_url", "delete_type" })
@XmlRootElement(name = "data")
public class FileData {

	private String name;

	private String url;

	private String size;

	private String thumbnail_url;

	private String delete_url;

	private String delete_type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public String getDelete_url() {
		return delete_url;
	}

	public void setDelete_url(String delete_url) {
		this.delete_url = delete_url;
	}

	public String getDelete_type() {
		return delete_type;
	}

	public void setDelete_type(String delete_type) {
		this.delete_type = delete_type;
	}
	
	
}
