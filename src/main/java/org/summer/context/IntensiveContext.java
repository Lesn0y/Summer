package org.summer.context;

import org.summer.context.services.SearchComponentService;

import java.util.List;

public class IntensiveContext {

    private final SearchComponentService searchService;

    public IntensiveContext(String basePackage) {
        this.searchService = new SearchComponentService(basePackage);
    }

    public <T> T getObject(Class<T> type) {
        List<Object> objectsT = searchService.findClassesByType(type);
        System.out.println(objectsT);
        return null;
    }
}
