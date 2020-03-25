package com.flight.info.flight.exception;

public class RunwaysAreBusyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RunwaysAreBusyException() {
		super();
	}

	public RunwaysAreBusyException(final String message) {
		super(message);
	}

}
