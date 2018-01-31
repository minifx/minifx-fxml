/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.lang;

import org.minifx.fxmlloading.builder.FxmlNodeBuilder;
import org.minifx.fxmlloading.factories.ModelSharingControllerFactory;
import org.minifx.fxmlloading.factories.impl.ControllerFactory;

import javafx.scene.Node;

/**
 * Provides static convenient methods for the most common use cases of loading javafx nodes from fxml.
 *
 * @author kfuchsbe
 */
public final class FxmlNodes {

    private static final ControllerFactory GLOBAL_CONTROLLER_FACTORY = ModelSharingControllerFactory.newDefault();

    private FxmlNodes() {
        /* Only static methods */
    }

    public static OngoingNodeCreation globalNode() {
        return new OngoingNodeCreation(GLOBAL_CONTROLLER_FACTORY);
    }

    public static OngoingNodeCreation isolatedNode() {
        return new OngoingNodeCreation(ModelSharingControllerFactory.newDefault());
    }

    /* Will not autowired anything in the instance. Is that what we want? */
    public static Node nonNestedNodeFrom(Object controllerInstance) {
        return FxmlNodeBuilder.byConventionFrom(controllerInstance).build();
    }

}
