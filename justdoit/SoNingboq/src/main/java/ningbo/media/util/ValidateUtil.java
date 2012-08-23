package ningbo.media.util;

import java.util.regex.Pattern;

public class ValidateUtil {

	public static boolean isNumeric(String character) {
		Pattern pattern = Pattern.compile("[0-9]*") ;
		return pattern.matcher(character).matches() ;
		
	}
}
