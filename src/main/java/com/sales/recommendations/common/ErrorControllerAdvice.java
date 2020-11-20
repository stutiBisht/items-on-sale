package com.sales.recommendations.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

//this method will catch all the exception thrown at global level of code, it can be refactored further intoto multiple methods on the 
//instances of exceptions
@ControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
		Error error = new Error();
		if ((exception.getCause()) instanceof HttpStatusCodeException) {
			HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) (exception.getCause());
			error.setMessage(httpStatusCodeException.getMessage());
			error.setErrorType("Bad Request");
			error.setStatusCode("400");
			return new ResponseEntity<>(httpStatusCodeException.getMessage(), httpStatusCodeException.getStatusCode());
		} else if ((exception.getCause()) instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) (exception
					.getCause());
			Map<String, String> errors = new HashMap<>();
			methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((er) -> {
				String fieldName = ((FieldError) er).getField();
				String errorMessage = er.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			return new ResponseEntity<>("Request failed due to validation error --> " + errors,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else if ((exception.getCause()) instanceof SQLException) {
			return new ResponseEntity<>("Something went wrong in DB", HttpStatus.INTERNAL_SERVER_ERROR);
		} else if ((exception.getCause()) instanceof SQLException) {
			return new ResponseEntity<>("Something went wrong in DB", HttpStatus.INTERNAL_SERVER_ERROR);
		} else if ((exception.getCause()) instanceof EntityNotFoundException) {
			return new ResponseEntity<>("No Record found in DB", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			error.setMessage(exception.getMessage());
			error.setErrorType("Bad Request");
			error.setStatusCode("400");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
