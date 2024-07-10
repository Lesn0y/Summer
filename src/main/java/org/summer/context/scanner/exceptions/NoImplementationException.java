package org.summer.context.scanner.exceptions;

/**
 * Exception thrown when no implementation is found for a given interface.
 */
public class NoImplementationException extends RuntimeException {

    public NoImplementationException(String message) {
        super(message);
    }

}
