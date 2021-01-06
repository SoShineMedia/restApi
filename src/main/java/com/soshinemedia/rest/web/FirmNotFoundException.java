package com.soshinemedia.rest.web;


public class FirmNotFoundException extends RuntimeException {
    public FirmNotFoundException() {
    }

    public FirmNotFoundException(Long id ) {
        super("Firm: " + id +" not found.");
    }
}