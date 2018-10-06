package leandroportfolio.league.handler;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ApiError implements Serializable{
	public ApiError(HttpStatus status, String errorCode, String message) {
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public ApiError() {
	}
	
	private HttpStatus status;
	private String errorCode;
	private String message;
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public String getMessage() {
		return this.message;
	}
}
