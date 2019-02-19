/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.factories.impl;

import static java.util.Objects.requireNonNull;

import javafx.util.Callback;

public class NestedControllerFactory extends PreferredControllerFactory {

    private final Callback<Class<?>, Object> childrenControllerFactory;

    public NestedControllerFactory(Object controller, Callback<Class<?>, Object> childrenControllerFactory) {
        super(controller);
        this.childrenControllerFactory = requireNonNull(childrenControllerFactory,
                "Children controller factory must not be null");
    }

    @Override
    protected Object callNested(Class<?> c) {
        Object nestedController = childrenControllerFactory.call(c);
        if (c.isInstance(nestedController)) {
            return nestedController;
        }

        throw new IllegalStateException("A controller of class '" + c
                + "' is requested, while the neither the provide controller nor the nested factory are matching the request.");
    }

}