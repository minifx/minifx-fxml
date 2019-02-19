/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import java.util.Objects;

import org.minifx.fxmlloading.configuration.impl.ControllerBasedFxmlLoadingConfiguration;
import org.minifx.fxmlloading.configuration.impl.ResourceBasedFxmlLoadingConfiguration;
import org.minifx.fxmlloading.factories.impl.ControllerFactory;

/**
 * Provides entry points for fluent clauses to load javafx nodes from fxml files, based on different input parameter. The methods chains are designed to avoid non-meaningful only provide build methods, where it makes sense an
 *
 * @author kfuchsbe
 */
public class FxmlNodeBuilders {

    public static OngoingFxmlNodeBuilder fromFxml(String classpathResource) {
        return new OngoingFxmlNodeBuilder(ResourceBasedFxmlLoadingConfiguration.fromFxml(classpathResource));
    }

    public static NonNestableFxmlNodeBuilder byConventionFrom(Object controllerInstance) {
        Objects.requireNonNull(controllerInstance);
        if (controllerInstance instanceof Class) {
            throw new IllegalArgumentException("Passed in controller must be a controller instance, not a class!");
        }
        ControllerBasedFxmlLoadingConfiguration convention = ControllerBasedFxmlLoadingConfiguration
                .of(controllerInstance.getClass());
        return new NonNestableFxmlNodeBuilder(convention, ControllerFactory.fromController(controllerInstance));
    }

    public static OngoingFxmlNodeBuilder byConventionFrom(Class<?> controllerClass) {
        Objects.requireNonNull(controllerClass);
        ControllerBasedFxmlLoadingConfiguration convention = ControllerBasedFxmlLoadingConfiguration
                .of(controllerClass);
        return new OngoingFxmlNodeBuilder(convention);
    }

}
