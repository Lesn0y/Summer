package org.summer.context.scanner;

/**
 * Interface for scanning and finding component classes.
 */
public interface ComponentScanner {
    <T> Class<?> findClassesByType(Class<T> type, String basePackage);
}
