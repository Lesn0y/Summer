package org.summer.context;

import org.summer.context.dependency.DependencyFactory;
import org.summer.context.injection.InjectionService;
import org.summer.context.injection.InjectionServiceImpl;
import org.summer.context.scanner.ComponentScanner;
import org.summer.context.scanner.ComponentScannerImpl;
import org.summer.context.scanner.exceptions.MultipleImplementationsException;
import org.summer.context.scanner.exceptions.NoImplementationException;

/**
 * Is a custom dependency injection container
 * that scans a given package for classes annotated with {@link IntensiveComponent}
 * and provides instances of these classes on demand.
 */
public class IntensiveContext {

    private final ComponentScanner componentScanner;
    private final InjectionService injectionService;
    private final String basePackage;

    /**
     * @param basePackage the package to scan for components
     */
    public IntensiveContext(String basePackage) {
        this.basePackage = basePackage;
        this.injectionService = new InjectionServiceImpl();
        this.componentScanner = new ComponentScannerImpl();
    }

    /**
     * Retrieves an instance of the specified class type, initializing and
     * injecting its dependencies if it is annotated with: <p>
     * {@link org.summer.context.annotation.IntensiveComponent} - for instance <p>
     * {@link org.summer.context.annotation.IntensiveAutowired} - for fields.
     *
     * @param <T> the type of the class
     * @param type the class object representing the type to retrieve
     * @return an instance of the specified type
     * @throws NoImplementationException if no implementation is found for the specified type
     * @throws MultipleImplementationsException if multiple implementations are found for the specified type
     */
    public <T> T getObject(Class<T> type) {
        Class<?> component = componentScanner.findClassByType(type, basePackage);

        T componentClass = (T) DependencyFactory.createInstance(component);
        return injectionService.injectDependencies(componentClass, componentScanner, basePackage);
    }
}
