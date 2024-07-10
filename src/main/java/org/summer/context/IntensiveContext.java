package org.summer.context;

import org.summer.context.scanner.ComponentScanner;
import org.summer.context.injection.InjectionService;
import org.summer.context.scanner.ComponentScannerImpl;
import org.summer.context.injection.InjectionServiceImpl;

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
        List<Object> objectsT = componentScanner.findClassesByType(type, basePackage);
        System.out.println(objectsT);
        return null;
    }
}
