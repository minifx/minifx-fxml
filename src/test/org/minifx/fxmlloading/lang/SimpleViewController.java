/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.lang;

/**
 * Sample Skeleton for 'SimpleView.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SimpleViewController {

    @FXML
    private Slider slider1; // Value injected by FXMLLoader

    @FXML
    private Label label1; // Value injected by FXMLLoader

    @FXML
    public void initialize() {
        label1.textProperty().bind(slider1.valueProperty().asString());
    }
}
