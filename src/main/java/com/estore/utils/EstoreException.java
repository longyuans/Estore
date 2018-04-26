package com.estore.utils;

public class EstoreException extends Exception{
    private String errorCode;
    public EstoreException(String errorCode,String message) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
