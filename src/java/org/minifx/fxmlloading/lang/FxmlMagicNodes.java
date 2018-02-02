/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.lang;

import org.minifx.fxmlloading.factories.ModelSharingControllerFactory;
import org.minifx.fxmlloading.factories.impl.ControllerFactory;

/**
 * Provides static convenient methods for the most common use cases of loading javafx nodes from fxml.
 *
 * @author kfuchsbe
 */
public final class FxmlMagicNodes {

    private static final ControllerFactory GLOBAL_CONTROLLER_FACTORY = ModelSharingControllerFactory.newDefault();

    private FxmlMagicNodes() {
        /* Only static methods */
    }

    public static OngoingNodeCreation globallyWiredNode() {
        return new OngoingNodeCreation(GLOBAL_CONTROLLER_FACTORY);
    }

    public static OngoingNodeCreation isolatedWiredNode() {
        return new OngoingNodeCreation(ModelSharingControllerFactory.newDefault());
    }

}
