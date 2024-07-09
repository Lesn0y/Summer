package org.summer.context.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A service that scans a base package for classes and filters them based on type.
 */
public class SearchComponentService {

    private static final Logger log = LogManager.getLogger(SearchComponentService.class);
    private final String basePackage;
    private final Set<File> filesSet;

    public SearchComponentService(String basePackage) {
        this.basePackage = basePackage.replace('.', '/');
        filesSet = scanBasePackage();
    }

    /**
     * Finds classes in the scanned package that match the specified type.
     *
     * @param type The class object representing the type to search for.
     * @return A list of classes that match the specified type.
     */
    public <T> List<Object> findClassesByType(Class<T> type) {
        return filesSet.stream()
                .map(this::getClassNameFromFile)
                .map(this::loadClass)
                .filter(Objects::nonNull)
                .filter(obj -> !obj.isInterface() && !Modifier.isAbstract(obj.getModifiers()))
                .filter(type::isAssignableFrom)
                .collect(Collectors.toList());
    }

    /**
     * Loads a class using its fully qualified name.
     *
     * @param className The fully qualified name of the class to load.
     * @return The {@code Class} object representing the loaded class, or {@code null} if not found.
     */
    private Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("Failed to load class {}", className, e);
            return null;
        }
    }

    /**
     * Scans the base package recursively to find all class files.
     *
     * @return A set of {@code File} objects representing the {@code .class} files found in the base package.
     */
    private Set<File> scanBasePackage() {
        Set<File> files = new HashSet<>();
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Enumeration<URL> resources = classLoader.getResources(this.basePackage);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File file = new File(resource.toURI());
                files.addAll(findClassFiles(file));
            }
        } catch (IOException e) {
            log.error("I/O error while scanning package: {}", basePackage);
        } catch (URISyntaxException e) {
            log.error("URI syntax error while scanning package: {}", basePackage);
        }
        return files;
    }

    /**
     * Recursively finds {@code .class} files starting from the given directory.
     *
     * @param startPackage The directory to start searching for class files.
     * @return A set of {@code File} objects representing the {@code .class} files found in the directory.
     */
    private Set<File> findClassFiles(File startPackage) {
        Set<File> files = new HashSet<>();
        File[] listFiles = startPackage.listFiles();
        if (listFiles == null) {
            return files;
        } else {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    files.addAll(findClassFiles(file));
                } else if (file.getName().endsWith(".class")) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * @param file The {@code File} object representing the {@code .class} file.
     * @return The fully qualified name of the desired class
     */
    private String getClassNameFromFile(File file) {
        String absolutePath = file.getAbsolutePath();
        String relativePath = absolutePath.substring(absolutePath.indexOf(basePackage), absolutePath.length() - 6);
        return relativePath.replace(File.separatorChar, '.');
    }
}