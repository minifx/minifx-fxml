/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.factory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HeaderSubViewController {

    @FXML
    Button okButton;

    @FXML
    void initialize() {
        System.out.println(okButton.getText());
    }
}
