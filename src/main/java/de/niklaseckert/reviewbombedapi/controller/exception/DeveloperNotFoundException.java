package de.niklaseckert.reviewbombedapi.controller.exception;

public class DeveloperNotFoundException extends RuntimeException {

    public DeveloperNotFoundException(Long id) {
        super("Could not find developer " + id);
    }
}
