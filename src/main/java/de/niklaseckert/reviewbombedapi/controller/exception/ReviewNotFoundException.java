package de.niklaseckert.reviewbombedapi.controller.exception;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(Long id) {
        super("Could not find review " + id);
    }
}
