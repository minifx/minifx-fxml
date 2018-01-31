/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading;

import static java.util.Objects.requireNonNull;
import static org.minifx.fxmlloading.util.FxmlControllers.useOnceOrThrow;

import java.io.IOException;
import java.net.URL;

import org.minifx.fxmlloading.configuration.FxmlLoadingConfiguration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class FxmlNodeLoader {

    private final Callback<Class<?>, Object> controllerFactory;
    private final FxmlLoadingConfiguration configuration;

    private FxmlNodeLoader(FxmlLoadingConfiguration convention, Callback<Class<?>, Object> controllerFactory) {
        this.configuration = requireNonNull(convention, "controller convention must not be null");
        this.controllerFactory = requireNonNull(controllerFactory, "controllerFactory must not be null");
    }

    public static final Node loadNodeFrom(FxmlLoadingConfiguration convention,
            Callback<Class<?>, Object> childControllerFactory) {
        return new FxmlNodeLoader(convention, childControllerFactory).create();
    }

    private Node create() {
        return stackPaneOf(loadFxml());
    }

    private Parent loadFxml() {
        URL fxmlUrl = configuration.fxmlResource();
        try {
            FXMLLoader loader = new FXMLLoader(fxmlUrl, configuration.resourceBundle());
            loader.setLocation(fxmlUrl);
            loader.setControllerFactory(c -> useOnceOrThrow(provideController(c)));
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load fx node from '" + fxmlUrl + "'", e);
        }
    }

    private static final Node stackPaneOf(Parent root) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(root);
        return stackPane;
    }

    private Object provideController(Class<?> clazz) {
        Object controller = controllerFactory.call(clazz);

        if (controller == null || !clazz.isAssignableFrom(controller.getClass())) {
            throw new IllegalStateException("ControllerFactory [" + controllerFactory.getClass().getName()
                    + "] returned a controller [" + controller + "] that is not compatible with the requested type ["
                    + clazz.getName() + "]");
        }
        return controller;
    }

}
