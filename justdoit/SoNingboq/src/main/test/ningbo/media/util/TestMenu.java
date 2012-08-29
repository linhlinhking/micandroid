package ningbo.media.util;

/**
 * 定义一个枚举类型，代表星期一到星期日的7个缩写常量
 *   同时还定义了枚举类型的属性day，该属性可以是final,也可是变量 同时还定义了该枚举类型的一个方法printDay
 */
public enum TestMenu {
	Mon("Monday"), Tue("Tuesday"), Wed("Wednesday"), Thu("Thursday"), Fri(
			"Friday"), Sat("Saturday"), Sun("Sunday");

	/** 定义枚举类型自己的属性 **/
	private final String day;

	private TestMenu(String day) {
		this.day = day;
	}

	/** 定义枚举类型自己的方法 **/
	public static void printDay(int i) {
		switch (i) {
		case 1:
			System.out.println(TestMenu.Mon);
			break;
		case 2:
			System.out.println(TestMenu.Tue);
			break;
		case 3:
			System.out.println(TestMenu.Wed);
			break;
		case 4:
			System.out.println(TestMenu.Thu);
			break;
		case 5:
			System.out.println(TestMenu.Fri);
			break;
		case 6:
			System.out.println(TestMenu.Sat);
			break;
		case 7:
			System.out.println(TestMenu.Sun);
			break;
		default:
			System.out.println("wrong number!");
		}
	}

	public String getDay() {
		return day;
	}
	
	public static void main(String [] args){
		System.out.println(TestMenu.Fri.getDay());
	}
}
