package ningbo.media.exception;



/**
 * description:Catch DataFormatException
 * 
 * @author Devon
 *
 */
public class DataFormatException extends Exception{

	private static final long serialVersionUID = -4313565638653324697L;

	public DataFormatException(String message){
		super("Data Format Error:" + message) ;
	}
}
