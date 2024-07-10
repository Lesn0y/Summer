package org.summer.context.scanner;

import org.summer.context.scanner.exceptions.MultipleImplementationsException;
import org.summer.context.scanner.exceptions.NoImplementationException;

/**
 * Interface defines methods for scanning packages
 * and finding classes that match specific criteria.
 */
public interface ComponentScanner {
    /**
     * Finds classes in the scanned package that match the specified type.
     *
     * @param type The class object representing the type to search for.
     * @param basePackage The package to scan for components
     * @return A class that matches the specified type.
     * @throws NoImplementationException If no implementation is found for the specified type.
     * @throws MultipleImplementationsException If multiple implementations are found for the specified type.
     */
    <T> Class<?> findClassByType(Class<T> type, String basePackage) throws NoImplementationException, MultipleImplementationsException;
}
