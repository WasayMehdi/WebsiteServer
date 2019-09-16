package com.wasaymehdi.config.singleton;

import com.wasaymehdi.config.Config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a class that we instantiate once through
 * our {@link Config}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Singleton {
    //INTENTIONALLY LEFT BLANK
}
