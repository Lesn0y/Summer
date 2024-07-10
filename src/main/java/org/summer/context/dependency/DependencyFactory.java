package org.summer.context.dependency;

import org.summer.context.dependency.exceptions.InstanceCreationException;

import java.lang.reflect.InvocationTargetException;

/**
 * Factory class for creating instances of specified classes.
 */
public class DependencyFactory {

    /**
     * Creates an instance of the specified class using its default constructor.
     *
     * @param implClass The class object representing the type to instantiate.
     * @return A new instance of the specified class.
     * @throws InstanceCreationException if the instance creation fails due to instantiation,
     *                                  IllegalAccessException, InvocationTargetException, or NoSuchMethodException.
     */

    public static <T> T createInstance(Class<T> implClass) {
        try {
            return implClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new InstanceCreationException("Failed to create an instance of: " + implClass.getName());
        }
    }

}
