/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import static java.util.Objects.requireNonNull;

import org.minifx.fxmlloading.configuration.FxmlLoadingConfiguration;
import org.minifx.fxmlloading.factories.impl.ControllerFactory;

public class NestableFxmlNodeBuilder extends AbstractFxmlNodeBuilder<ControllerFactory> {

    public NestableFxmlNodeBuilder(FxmlLoadingConfiguration configuration, ControllerFactory controllerFactory) {
        super(configuration, requireNonNull(controllerFactory, "controller factory must not be null"));
    }

}