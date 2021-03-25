
package com.soshinemedia.pyngoapi.web;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String Id ) {
        super("Entity: " + Id +" not found.");
    }
}
