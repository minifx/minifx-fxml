/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.lang.instance;

import static java.util.Objects.requireNonNull;

/**
 * Sample Skeleton for 'SimpleView.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SimpleViewController {

    private final SimpleViewValueModel model;

    @FXML
    private Slider slider1; // Value injected by FXMLLoader

    @FXML
    private Label label1; // Value injected by FXMLLoader

    public SimpleViewController(SimpleViewValueModel model) {
        this.model = requireNonNull(model, "model must not be null");
    }

    @FXML
    public void initialize() {
        // if the model is shared you cannot the same model property twice to different observables, the second one will win
        slider1.valueProperty().addListener((ctrl, oldVal, newVal) -> model.sliderValueProperty().set(newVal.doubleValue()));

        label1.textProperty().bind(model.sliderValueProperty().asString());
    }
}
