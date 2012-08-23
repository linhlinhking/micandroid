package ningbo.media.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;

@XmlType(name = "", namespace = WsConstants.NS, propOrder = {"result","message","data"})
@XmlRootElement
public class DataMessage<T> {

	private boolean result;

	private String message;

	private T data;
	
	public DataMessage(){
		
	}

	@XmlElement
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
