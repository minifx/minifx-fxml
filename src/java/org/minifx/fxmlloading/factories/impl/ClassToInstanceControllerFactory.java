/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.factories.impl;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A pseudo factory, which provides instances from a map
 * <p>
 * This class is not threadsafe
 *
 * @author kfuchsbe
 */
public class ClassToInstanceControllerFactory implements ControllerFactory {

    private final Map<Class<?>, Object> instances;

    public ClassToInstanceControllerFactory(Map<Class<?>, Object> instances) {
        this.instances = new HashMap<>(requireNonNull(instances, "instances must not be null"));
    }

    public static final ClassToInstanceControllerFactory ofInstances(Object... controllerInstances) {
        requireNonNull(controllerInstances, "controllerInstances must not be null");
        Map<Class<?>, Object> classToInstances = new HashMap<>();
        for (Object instance : controllerInstances) {
            putOrThrowIfPresent(classToInstances, instance);
        }
        return new ClassToInstanceControllerFactory(classToInstances);
    }

    private static void putOrThrowIfPresent(Map<Class<?>, Object> classToInstances, Object instance) {
        Class<? extends Object> type = instance.getClass();
        if (classToInstances.containsKey(type)) {
            throw new IllegalArgumentException(
                    "Controller of type '" + type + "' was provided twice. This is not allowed.");
        }
        classToInstances.put(type, instance);
    }

    @Override
    public Object call(Class<?> param) {
        return instances.get(param);
    }

    public ClassToInstanceControllerFactory and(Object controllerInstance) {
        requireNonNull(controllerInstance, "controllerInstance must not be null");
        Map<Class<?>, Object> builder = new HashMap<>(instances);
        putOrThrowIfPresent(builder, controllerInstance);

        Class<? extends Object> type = controllerInstance.getClass();
        builder.put(type, controllerInstance);
        return new ClassToInstanceControllerFactory(builder);
    }

}
