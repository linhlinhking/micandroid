package ningbo.media.admin.util;

import java.util.HashMap;
import java.util.Map;

public final class Result<T> {

	private boolean success;
	private T defaultModel;
	private Map<String, T> model;
	private String msg;

	public Result() {
		model = new HashMap<String, T>();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getDefaultModel() {
		return defaultModel;
	}

	public void setDefaultModel(T defaultModel) {
		this.defaultModel = defaultModel;
	}

	public T getModel(String key) {
		return model.get(key);
	}

	public void setModel(String key, T value) {
		model.put(key, value);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
