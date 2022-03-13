package de.niklaseckert.reviewbombedapi.controller.exception;

/**
 * Exception which is thrown when a {@link de.niklaseckert.reviewbombedapi.model.Developer Developer} is not found.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
public class DeveloperNotFoundException extends RuntimeException {

    public DeveloperNotFoundException(Long id) {
        super("Could not find developer " + id);
    }
}
