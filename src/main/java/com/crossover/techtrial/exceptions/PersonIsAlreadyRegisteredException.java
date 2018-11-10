package com.crossover.techtrial.exceptions;

public class PersonIsAlreadyRegisteredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The Person is already registered";
	}
}
