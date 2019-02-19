/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import java.util.Arrays;

import org.minifx.fxmlloading.configuration.FxmlLoadingConfiguration;
import org.minifx.fxmlloading.factories.impl.ClassToInstanceLookupControllerFactory;
import org.minifx.fxmlloading.factories.impl.DelegatingControllerFactory;

import javafx.util.Callback;

public class OngoingFxmlNodeBuilder {
    private final FxmlLoadingConfiguration configuration;

    public OngoingFxmlNodeBuilder(FxmlLoadingConfiguration configuration) {
        this.configuration = configuration;
    }

    public NestableFxmlNodeBuilder controllersFrom(Callback<Class<?>, Object> controllerFactory) {
        return new NestableFxmlNodeBuilder(configuration, DelegatingControllerFactory.of(controllerFactory));
    }

    public NestableFxmlNodeBuilder controllers(Iterable<?> controllerInstances) {
        return new NestableFxmlNodeBuilder(configuration,
                ClassToInstanceLookupControllerFactory.ofInstances(controllerInstances));
    }

    public NestableFxmlNodeBuilder controllers(Object... controllerInstances) {
        return controllers(Arrays.asList(controllerInstances));
    }

}