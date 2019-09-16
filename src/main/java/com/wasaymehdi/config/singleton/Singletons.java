package com.wasaymehdi.config.singleton;

import javax.ws.rs.core.Configuration;

/**
 * Utility class to extract "singletons" from our configuration object.
 */
public class Singletons {

    public static <T> T get(final Configuration configuration, final Class<T> clazz) {

        return (T) configuration.getProperty(clazz.getName());

    }

}
