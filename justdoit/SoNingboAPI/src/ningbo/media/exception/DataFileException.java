package ningbo.media.exception;


public class DataFileException extends Exception {

	private static final long serialVersionUID = 6064038972524870108L;

	public DataFileException() {

	}

	public DataFileException(String message) {
		super("IO Exception:" + message) ;
	}
	
	public DataFileException(Throwable cause) {
		super("Unexpected Server Error: " + cause.getMessage(), cause);
	}
	
	

}
