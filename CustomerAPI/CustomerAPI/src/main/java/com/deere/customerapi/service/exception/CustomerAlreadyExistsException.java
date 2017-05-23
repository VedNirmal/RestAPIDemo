package com.deere.customerapi.service.exception;

/**
 * @author nirmal.ved
 */
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(final String message) {
        super(message);
    }

}
