/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import static java.util.Objects.requireNonNull;

import org.minifx.fxmlloading.FxmlNodeLoader;
import org.minifx.fxmlloading.configuration.FxmlLoadingConfiguration;
import org.minifx.fxmlloading.factories.impl.ControllerFactory;

import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class AbstractFxmlNodeBuilder<F extends ControllerFactory> implements FxmlNodeBuilder {

    private final FxmlLoadingConfiguration configuration;
    private final F factory;

    AbstractFxmlNodeBuilder(FxmlLoadingConfiguration configuration, F controllerFactory) {
        this.configuration = requireNonNull(configuration);
        this.factory = requireNonNull(controllerFactory, "controller factory must not be null");
    }

    @Override
    public final boolean canBuild() {
        return this.configuration.hasFxmlResource();
    }

    @Override
    public final Parent build() {
        return FxmlNodeLoader.loadNodeFrom(configuration, factory);
    }

    final F factory() {
        return factory;
    }

    final FxmlLoadingConfiguration configuration() {
        return configuration;
    }

}