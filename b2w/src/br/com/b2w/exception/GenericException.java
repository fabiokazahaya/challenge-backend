package br.com.b2w.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericException(Throwable cause) {
		super(cause);
	}
}
