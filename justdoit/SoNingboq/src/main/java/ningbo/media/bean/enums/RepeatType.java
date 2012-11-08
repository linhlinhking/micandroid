package ningbo.media.bean.enums;

public enum RepeatType {
	WEEKS("weeks"),DAYS("days"),CUSTOMS("customs"),MONTHS("months") ;
	
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
