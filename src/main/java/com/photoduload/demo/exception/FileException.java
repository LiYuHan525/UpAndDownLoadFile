package com.photoduload.demo.exception;

public class FileException extends Exception {

    private String message;
    private String code;

    public FileException(String message1, String code) {
        this.message = message1;
        this.code = code;
    }

    public FileException(String message1) {
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
