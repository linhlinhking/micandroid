package ningbo.media.admin.response;

import java.util.List;

public class ResponseVO<T extends Object> {

	protected Boolean result;
	protected List<T> data;
	protected String message ;

	public ResponseVO(){}
	
	public ResponseVO(Boolean result) {
		super();
		this.result = result;
	}

	public ResponseVO(Boolean result, List<T> data) {
		super();
		this.result = result;
		this.data = data;
	}


	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
