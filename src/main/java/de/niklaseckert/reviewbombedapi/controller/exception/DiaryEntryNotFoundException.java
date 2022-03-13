package de.niklaseckert.reviewbombedapi.controller.exception;

/**
 * Exception which is thrown when a {@link de.niklaseckert.reviewbombedapi.model.DiaryEntry Diary Entry} is not found.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
public class DiaryEntryNotFoundException extends RuntimeException {

    public DiaryEntryNotFoundException(Long id) {
        super("Could not find diary entry " + id);
    }
}
