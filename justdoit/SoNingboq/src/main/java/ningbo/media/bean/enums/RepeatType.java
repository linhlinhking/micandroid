package ningbo.media.bean.enums;

public enum RepeatType {
	WEEKS("week"),DAYS("day"),CUSTOMS("custom"),MONTH("month") ;
	
	private String value ;
	
	private RepeatType(String value){
		this.value = value ;
	}
	
	public String getValue(){
		return value ;
	}
	
	public void setValue(String value){
		this.value = value ;
	}
}
