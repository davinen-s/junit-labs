package com.accenture.academy.exception;

/**
 * Exception raised due to business rule breach. 
 * 
 * @author davinen.s.curoopen
 */
public class BusinessException extends Exception {

	/**
	 * Default serial id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public BusinessException() {
		super();
	}

	/**
	 * Constructor with overloaded parameter.
	 * 
	 * @param erroMessage
	 *            the error message
	 */
	public BusinessException(String erroMessage) {
		super(erroMessage);
	}

}
