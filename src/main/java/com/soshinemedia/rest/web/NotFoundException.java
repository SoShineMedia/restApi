
package com.soshinemedia.rest.web;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String Id ) {
        super("Entity: " + Id +" not found.");
    }
}
