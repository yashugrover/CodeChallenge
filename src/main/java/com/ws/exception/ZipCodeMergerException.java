package com.ws.exception;

import com.ws.constants.ErrorConstants;

public class ZipCodeMergerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorConstants errorCode;

	public ZipCodeMergerException() {
		super();
	}

	public ZipCodeMergerException(String message){
	    super(message);
    }

	public ZipCodeMergerException(String message, ErrorConstants errorCode, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ZipCodeMergerException(String message, ErrorConstants errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ZipCodeMergerException(ErrorConstants errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ErrorConstants getErrorCode() {
		return errorCode;
	}
}
