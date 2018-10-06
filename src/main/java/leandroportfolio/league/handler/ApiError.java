package leandroportfolio.league.handler;

import org.springframework.http.HttpStatus;

public class ApiError {
	ApiError(HttpStatus status, String errorCode) {
		this.status = status;
		this.errorCode = errorCode;
	}
	
	private HttpStatus status;
	private String errorCode;
	
	public HttpStatus getStatus() {
		return this.status;
	}
}
