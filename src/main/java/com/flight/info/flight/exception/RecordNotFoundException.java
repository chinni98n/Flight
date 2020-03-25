package com.flight.info.flight.exception;

public class RecordNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(final String message) {
		super(message);
	}
}
