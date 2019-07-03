package io.spotnext.kakao.exceptions;

public class IOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IOException(String message) {
		super(message);
	}

	public IOException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public IOException(Throwable rootCause) {
		super(rootCause);
	}

}
