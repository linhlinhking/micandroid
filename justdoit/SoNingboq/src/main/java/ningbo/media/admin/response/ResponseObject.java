package ningbo.media.admin.response;


public class ResponseObject<E extends Object> {

	protected Boolean success;
	
	protected E data;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	
	
}
