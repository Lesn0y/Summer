package org.summer.context.services;

import java.util.List;

public interface ComponentScanner {

    <T> List<Object> findClassesByType(Class<T> type, String basePackage);
    
}
