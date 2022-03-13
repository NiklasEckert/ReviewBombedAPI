package de.niklaseckert.reviewbombedapi.controller.exception;

/**
 * Exception which is thrown when a {@link de.niklaseckert.reviewbombedapi.model.Review Review} is not found.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(Long id) {
        super("Could not find review " + id);
    }
}
