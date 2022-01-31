package de.niklaseckert.reviewbombedapi.controller.exception;

public class ListNotFoundException extends RuntimeException {

    public ListNotFoundException(Long id) {
        super("Could not find list " + id);
    }
}
