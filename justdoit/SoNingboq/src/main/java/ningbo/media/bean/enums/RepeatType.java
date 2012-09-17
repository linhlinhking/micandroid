package ningbo.media.bean.enums;

public enum RepeatType {
	WEEKS("weeks"),DAYS("days"),CUSTOMS("customs") ;
	
	private final String value ;
	
	private RepeatType(String value){
		this.value = value ;
	}
	
	public String getValue(){
		return value ;
	}
	
	public static void main(String args[]){
		System.out.println(RepeatType.WEEKS.getValue()) ;
	}
	
}
