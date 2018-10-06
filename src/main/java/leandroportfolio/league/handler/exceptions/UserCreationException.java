package leandroportfolio.league.handler.exceptions;

public class UserCreationException extends RuntimeException {

	public UserCreationException(String message) {
		this.message = message;
	}
	private static final long serialVersionUID = 1L;
	
	private String message;
	public String getMessage() {
		return message;
	}


}
