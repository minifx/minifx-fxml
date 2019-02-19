/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import java.util.Arrays;

import org.minifx.fxmlloading.configuration.FxmlLoadingConfiguration;
import org.minifx.fxmlloading.factories.impl.ClassToInstanceLookupControllerFactory;
import org.minifx.fxmlloading.factories.impl.SingleControllerFactory;

import javafx.util.Callback;

public class NonNestableFxmlNodeBuilder extends AbstractFxmlNodeBuilder<SingleControllerFactory> {
    public NonNestableFxmlNodeBuilder(FxmlLoadingConfiguration configuration,
            SingleControllerFactory controllerFactory) {
        super(configuration, controllerFactory);
    }

    public NestableFxmlNodeBuilder nestedControllersFrom(Callback<Class<?>, Object> nestedFactory) {
        return new NestableFxmlNodeBuilder(configuration(), factory().withNestedFrom(nestedFactory));
    }

    public NestableFxmlNodeBuilder nestedControllers(Iterable<?> controllerInstances) {
        return new NestableFxmlNodeBuilder(configuration(),
                factory().withNestedFrom(ClassToInstanceLookupControllerFactory.ofInstances(controllerInstances)));
    }

    public NestableFxmlNodeBuilder nestedControllers(Object... controllerInstances) {
        return nestedControllers(Arrays.asList(controllerInstances));
    }
}