package com.flight.info.flight.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flight.info.flight.constants.ApplicationConstants;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(RunwaysAreBusyException.class)
	public ExceptionResponse handleException(final RunwaysAreBusyException exception, final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setStatus(ApplicationConstants.RUNWAY_BUSY_CODE);
		return error;
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ExceptionResponse handleException(final RecordNotFoundException exception, final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setStatus(ApplicationConstants.RUNWAY_BUSY_CODE);
		return error;
	}
}
