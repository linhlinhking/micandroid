package ningbo.media.bean.enums;

public enum EventRequestType {
	EQ("eq"),LT("lt"),GT("gt") ;
	
	private final String value ;
	
	private EventRequestType(String value){
		this.value = value ;
	}
	
	public String getValue(){
		return value ;
	}
	
}
