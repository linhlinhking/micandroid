package ningbo.media.bean.enums;

public enum RepeatType {
	DAYS("days"),CUSTOMS("customs") ;
	
	private final String value ;
	
	private RepeatType(String value){
		this.value = value ;
	}
	
	public String getValue(){
		return value ;
	}
	
}
