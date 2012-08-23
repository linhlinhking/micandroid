package ningbo.media.proxy.bean;

import java.io.Serializable;

public class FormParamter implements Serializable, Comparable<FormParamter> {

	private static final long serialVersionUID = 2509878075040036504L;

	private String name;

	private String value;
	

	public FormParamter(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public FormParamter(String name, double value) {
		this.name = name;
		this.value = String.valueOf(value) ;
	}
	
	public FormParamter(String name, int value) {
		this.name = name;
		this.value = String.valueOf(value) ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public int compareTo(FormParamter form) {
		int compared;
		compared = this.name.compareTo(form.getName());
		if (0 == compared) {
			compared = this.value.compareTo(form.getValue());
		}
		return compared;
	}

}
