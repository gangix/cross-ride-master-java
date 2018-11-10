package com.crossover.techtrial.exceptions;

public class EndTimeIsGreaterThanCurrentException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The EndTime is Greater than CurrentTime!";
	}
}
