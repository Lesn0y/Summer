package org.summer.context.injection;

import org.summer.context.annotation.IntensiveAutowired;
import org.summer.context.dependency.DependencyFactory;
import org.summer.context.scanner.ComponentScanner;

import java.lang.reflect.Field;

/**
 * This service scans the fields of a given instance for dependencies marked with the {@link IntensiveAutowired}
 * annotation and injects the appropriate implementations.
 */
public class InjectionServiceImpl implements InjectionService {


    /**
     * Injects dependencies into the fields of the given instance. Dependencies are identified by the
     * {@link IntensiveAutowired} annotation and are instantiated and injected using the provided
     * {@link ComponentScanner}.
     *
     * @param instance    The instance into which dependencies should be injected.
     * @param scanner     The scanner used to find dependency classes.
     * @param basePackage The base package to scan for dependency classes.
     * @param <T>         The type of the instance.
     * @return The instance with dependencies injected.
     * @throws RuntimeException if there is an error during dependency injection.
     */
    @Override
    public <T> T injectDependencies(T instance, ComponentScanner scanner, String basePackage) {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IntensiveAutowired.class)) {
                field.setAccessible(true);
                try {
                    Class<?> aClass = scanner.findClassesByType(field.getType(), basePackage);
                    T componentClass = (T) DependencyFactory.createInstance(aClass);

                    field.set(instance, componentClass);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency for field: " + field.getName(), e);
                }
            }
        }
        return instance;
    }
}
