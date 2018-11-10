package com.crossover.techtrial.exceptions;

public class PersonIsNotRegisteredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The Person is not registered";
	}
}
