package de.niklaseckert.reviewbombedapi.controller.exception;

/**
 * Exception which is thrown when a {@link de.niklaseckert.reviewbombedapi.model.ListModel List Model} is not found.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
public class ListNotFoundException extends RuntimeException {

    public ListNotFoundException(Long id) {
        super("Could not find list " + id);
    }
}
