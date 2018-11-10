package com.crossover.techtrial.exceptions;

public class TimeOverlappingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Shared ride time is not overlapping!";
	}

}
