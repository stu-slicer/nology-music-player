package com.nology.musicplayer.exceptions;

/**
 * Unchecked database exception for wrapping database exceptions.
 */
public class TrackDatabaseException extends RuntimeException {

    public TrackDatabaseException(String message) {
        super(message);
    }

    public TrackDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
