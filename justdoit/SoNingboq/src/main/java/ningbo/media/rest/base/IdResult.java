package ningbo.media.rest.base;

import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;

@XmlType(name = "IdResult", namespace = WsConstants.NS)
public class IdResult extends WSResult {
	private Integer id;

	public IdResult() {
	}

	public IdResult(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}