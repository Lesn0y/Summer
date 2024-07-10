package org.summer.context;

import org.summer.context.dependency.DependencyFactory;
import org.summer.context.injection.InjectionService;
import org.summer.context.injection.InjectionServiceImpl;
import org.summer.context.scanner.ComponentScanner;
import org.summer.context.scanner.ComponentScannerImpl;

public class IntensiveContext {

    private final ComponentScanner componentScanner;
    private final InjectionService injectionService;
    private final String basePackage;

    public IntensiveContext(String basePackage) {
        this.basePackage = basePackage;
        this.injectionService = new InjectionServiceImpl();
        this.componentScanner = new ComponentScannerImpl();
    }

    public <T> T getObject(Class<T> type) {
        Class<?> component = componentScanner.findClassesByType(type, basePackage);

        T componentClass = (T) DependencyFactory.createInstance(component);
        return injectionService.injectDependencies(componentClass, componentScanner, basePackage);
    }
}
