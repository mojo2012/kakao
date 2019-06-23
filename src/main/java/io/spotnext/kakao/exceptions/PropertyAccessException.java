package io.spotnext.kakao.exceptions;

public class PropertyAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropertyAccessException(String message, Throwable rootCause) {
		super(message, rootCause);
	}
	
	public PropertyAccessException(String message) {
		super(message);
	}
	
	public PropertyAccessException(Throwable rootCause) {
		super(rootCause);
	}
}
