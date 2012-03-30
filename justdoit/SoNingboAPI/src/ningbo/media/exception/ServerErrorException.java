package ningbo.media.exception;

public class ServerErrorException extends RuntimeException {

	private static final long serialVersionUID = -7469862445987667027L;

	public ServerErrorException(String message) {
		super("Unexpected Server Error: " + message);
	}

	public ServerErrorException(Throwable cause) {
		super("Unexpected Server Error: " + cause.getMessage(), cause);
	}
}
