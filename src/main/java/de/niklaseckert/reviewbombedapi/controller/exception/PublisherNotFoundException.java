package de.niklaseckert.reviewbombedapi.controller.exception;

/**
 * Exception which is thrown when a {@link de.niklaseckert.reviewbombedapi.model.Publisher Publisher} is not found.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(Long id) {
        super("Could not find publisher " + id);
    }
}
