package org.summer.context.injection;

import org.summer.context.annotation.IntensiveAutowired;
import org.summer.context.scanner.ComponentScanner;

/**
 * Interface for injecting dependencies into instances based on field annotations.
 */
public interface InjectionService {
    /**
     * Injects dependencies into the fields of the given instance. Dependencies are identified by the
     * {@link IntensiveAutowired}.
     *
     * @param instance    The instance into which dependencies should be injected.
     * @param scanner     The scanner used to find dependency classes.
     * @param basePackage The base package to scan for dependency classes.
     * @return The instance with dependencies injected.
     * @throws RuntimeException if there is an error during dependency injection.
     */
    <T> T injectDependencies(T instance, ComponentScanner scanner, String basePackage);
}
