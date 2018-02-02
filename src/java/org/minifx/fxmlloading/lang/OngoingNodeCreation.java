/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.lang;

import java.util.Objects;

import org.minifx.fxmlloading.builders.FxmlNodeBuilders;
import org.minifx.fxmlloading.factories.impl.ControllerFactory;

import javafx.scene.Node;

public class OngoingNodeCreation {

    private final ControllerFactory controllerFactory;

    public OngoingNodeCreation(ControllerFactory controllerFactory) {
        this.controllerFactory = Objects.requireNonNull(controllerFactory, "controllerFactory must not be null");
    }

    public Node byConventionFrom(Class<?> controllerClass) {
        return FxmlNodeBuilders.byConventionFrom(controllerClass).controllersFrom(controllerFactory).build();
    }

    public Node fromFxml(String classpathResource) {
        return FxmlNodeBuilders.fromFxml(classpathResource).controllersFrom(controllerFactory).build();
    }

}
