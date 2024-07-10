package org.summer.context;

import org.summer.context.injection.InjectionService;
import org.summer.context.injection.InjectionServiceImpl;
import org.summer.context.scanner.ComponentScanner;
import org.summer.context.scanner.ComponentScannerImpl;
import org.summer.context.scanner.exceptions.MultipleImplementationsException;
import org.summer.context.scanner.exceptions.NoImplementationException;

import java.util.List;

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
        List<Object> components = componentScanner.findClassesByType(type, basePackage);

        if (components.isEmpty()) {
            throw new NoImplementationException("No implementation found for: " + type.getName());
        }
        if (components.size() > 1) {
            throw new MultipleImplementationsException("Multiple implementations found for: " + type.getName());
        }

        return null;
    }
}
