package com.wasaymehdi;

import com.wasaymehdi.config.Config;
import com.wasaymehdi.config.DevelopmentConfig;
import com.wasaymehdi.config.ProductionConfig;
import com.wasaymehdi.config.singleton.Singleton;
import org.reflections.Reflections;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("api")
public class WebsiteApplication extends Application {

    private static final boolean DEVELOPMENT_MODE = "true".equals(
            System.getProperty("com.wasaymehdi.config.development")
    );

    private static final String PACKAGE = "com.wasaymehdi";

    // #getClasses gets called twice in jersey
    private final Set<Class<?>> classes;

    public WebsiteApplication() {

        final Set<Class<?>> classes = new HashSet<>();

        final Reflections reflections = new Reflections(PACKAGE);

        classes.addAll(reflections.getTypesAnnotatedWith(Path.class));

        classes.addAll(reflections.getTypesAnnotatedWith(Provider.class));

        this.classes = Collections.unmodifiableSet(classes);

    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    /**
     * Store all the "singletons" in the context of this application run
     */
    @Override
    public Map<String, Object> getProperties() {

        final Map<String, Object> map = new HashMap<>();

        final Config config = DEVELOPMENT_MODE ? new DevelopmentConfig() : new ProductionConfig();

        Stream.of(config.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Singleton.class))
                .collect(Collectors.toMap(Function.identity(), m -> invoke(m, config)));

        return map;

    }

    private static Object invoke(final Method method, final Config config) {

        try {

            return method.invoke(config);

        } catch (Exception e) {

            throw new RuntimeException("Cannot instantiate singleton class");

        }

    }

}
