package de.niklaseckert.reviewbombedapi.controller.exception;

public class DiaryEntryNotFoundException extends RuntimeException {

    public DiaryEntryNotFoundException(Long id) {
        super("Could not find diary entry " + id);
    }
}
