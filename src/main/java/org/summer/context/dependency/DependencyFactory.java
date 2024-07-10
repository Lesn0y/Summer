package org.summer.context.dependency;

import org.summer.context.dependency.exceptions.InstanceCreationException;

import java.lang.reflect.InvocationTargetException;

public class DependencyFactory {

    public static <T> T createInstance(Class<T> implClass) {
        try {
            return implClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new InstanceCreationException("Failed to create an instance of: " + implClass.getName());
        }
    }

}
