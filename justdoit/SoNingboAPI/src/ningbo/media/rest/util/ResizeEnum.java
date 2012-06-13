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
	
	
	public static void main(String args[]){
		String size = ResizeEnum.b1.getName();
		String [] lens = size.split("x") ;
		System.out.println("width:" + lens[0] + " height:"+ lens[1]) ;
		
		ResizeEnum[] lss = ResizeEnum.values() ;
		long t1 = System.currentTimeMillis() ;
		for(ResizeEnum r : lss){
			System.out.println(r.getName());
		}
		long t2 = System.currentTimeMillis() ;
		System.out.println("time:" + (t2 - t1)) ;
	}
}
