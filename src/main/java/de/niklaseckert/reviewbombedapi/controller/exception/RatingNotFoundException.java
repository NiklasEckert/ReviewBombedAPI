package de.niklaseckert.reviewbombedapi.controller.exception;

/**
 * Exception which is thrown when a {@link de.niklaseckert.reviewbombedapi.model.Rating Rating} is not found.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
public class RatingNotFoundException extends RuntimeException {

    public RatingNotFoundException(Long id) {
        super("Could not find rating " + id);
    }
}
