package org.summer.context.scanner;

import java.util.List;

/**
 * Interface for scanning and finding component classes.
 */
public interface ComponentScanner {
    <T> List<Object> findClassesByType(Class<T> type, String basePackage);
}
