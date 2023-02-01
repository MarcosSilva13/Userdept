package com.devsuperior.userdept.services.exceptions;

public class UserNullFieldsException extends NullPointerException {

    public UserNullFieldsException() {
        super();
    }

    public UserNullFieldsException(String message) {
        super(message);
    }
}
