package ningbo.media.web.bean;

public class QParameter implements java.io.Serializable, Comparable<QParameter> {
	private static final long serialVersionUID = 1277642579055678022L;
	private String name;
	private String value;

	public QParameter(String name, String value) {
		this.name = name;
		this.value = value;
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

	public int compareTo(QParameter param) {
		int compared;
		compared = this.name.compareTo(param.getName());
		if (0 == compared) {
			compared = this.value.compareTo(param.getValue());
		}
		return compared;
	}
}