package ningbo.media.core.message;

import java.util.List;

/**
 * 返回JSON的消息。
 * @author Devon.Ning
 *
 * @param <T>
 */
public class DataListMessage<T> {
	
	private boolean result ;
	
	private String message ;
	
	private List<T> data ;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
