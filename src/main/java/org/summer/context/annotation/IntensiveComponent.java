package org.summer.context.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to indicate that a class is a component and should be managed by the IntensiveContext.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveComponent {
}
