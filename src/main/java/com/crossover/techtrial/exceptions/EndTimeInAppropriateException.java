package com.crossover.techtrial.exceptions;

public class EndTimeInAppropriateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "End time less than or equal to start time!";
	}

}
