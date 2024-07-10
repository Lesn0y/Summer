package org.summer.context.scanner.exceptions;

/**
 * Exception thrown when multiple implementations are found for a specified type.
 */
public class MultipleImplementationsException extends RuntimeException {

    public MultipleImplementationsException(String message) {
        super(message);
    }
}
