package org.summer.context.dependency.exceptions;

/**
 * Exception thrown when an instance of a class cannot be created.
 */
public class InstanceCreationException extends RuntimeException {

    public InstanceCreationException(String message) {
        super(message);
    }
}
