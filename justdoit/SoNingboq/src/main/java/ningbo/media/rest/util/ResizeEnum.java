package ningbo.media.rest.util;

public enum ResizeEnum {
	b1("100x100"),b2("200x200"),b3("800x600");
	
	private String name ;
	
	
	public String getName(){
		return name ;
	}
	
	ResizeEnum(String name){
		this.name = name ;
	}
	
}
