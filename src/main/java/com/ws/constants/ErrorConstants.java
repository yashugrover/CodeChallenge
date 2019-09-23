package com.ws.constants;

/**
 * Error codes for the application
 *
 */
public enum ErrorConstants {

    INVALID_RANGE("400.ZIPCODE.001"),
    FILE_EMPTY("400.ZIPCODE.002"),
    INPUT_FILE_MISSING_IN_PARAM("400.ZIPCODE.003"),
	INVALID_IMPLEMENTATION_SELECTED("400.ZIPCODE.004"),
	FILE_NOT_FOUND("400.ZIPCODE0.005"),
	INVALID_ZIPCODE("400.ZIPCODE0.006");

    private final String code;

    private ErrorConstants(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
