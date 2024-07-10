package org.summer.context.injection;

import org.summer.context.scanner.ComponentScanner;

public interface InjectionService {
    <T> T injectDependencies(T instance, ComponentScanner scanner, String basePackage);
}
