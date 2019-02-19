/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.factories.impl;

import static java.util.Objects.requireNonNull;

public abstract class PreferredControllerFactory implements ControllerFactory {

    private final Object preferredController;

    public PreferredControllerFactory(Object controller) {
        this.preferredController = requireNonNull(controller, "Controller must not be null");
    }

    @Override
    public final Object call(Class<?> c) {
        if (c.isInstance(preferredController)) {
            return preferredController;
        }
        return callNested(c);
    }

    protected abstract Object callNested(Class<?> c);

    protected Object preferredController() {
        return preferredController;
    }

}