package leandroportfolio.league.handler;

import leandroportfolio.league.handler.exceptions.LeagueCreationException;
import leandroportfolio.league.handler.exceptions.UserCreationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserCreationException.class)
	protected ResponseEntity<ApiError> handleUserCreationException(UserCreationException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,Constants.UserExceptionConstant, ex.getMessage()));
	}
	
	@ExceptionHandler(LeagueCreationException.class)
	protected ResponseEntity<ApiError> handleLeagueCreationException(LeagueCreationException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,Constants.LeagueExceptionConstant, ex.getMessage()));
	}

	private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
