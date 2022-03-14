package de.niklaseckert.reviewbombedapi.controller.exception;

/**
 * Exception which is thrown when a {@link de.niklaseckert.reviewbombedapi.model.Game Game} is not found.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Long id) {
        super("Could not find game " + id);
    }
}
