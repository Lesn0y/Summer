package org.summer.context;

import org.summer.context.services.ComponentScanner;
import org.summer.context.services.impl.ComponentScannerImpl;

import java.util.List;

public class IntensiveContext {

    private final ComponentScanner searchService = new ComponentScannerImpl();
    private final String basePackage;

    public IntensiveContext(String basePackage) {
        this.basePackage = basePackage;
    }

    public <T> T getObject(Class<T> type) {
        List<Object> objectsT = searchService.findClassesByType(type, basePackage);
        System.out.println(objectsT);
        return null;
    }
}
