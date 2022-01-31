package de.niklaseckert.reviewbombedapi.controller.exception;
public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(Long id) {
        super("Could not find publisher " + id);
    }
}
