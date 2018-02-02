/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.lang;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class FxmlMagicNodesExampleTest extends ApplicationTest {

    private Parent root;
    private Slider slider;
    private Label label;

    @Before
    public void setUp() {
        slider = (Slider) root.lookup("#slider1");
        label = (Label) root.lookup("#label1");
    }

    @Test
    public void fieldsAreFound() {
        assertThat(slider).isNotNull();
        assertThat(label).isNotNull();
    }

    @Test
    public void sliderToLabelBindingWorks() {
        clickOn(slider, MouseButton.PRIMARY);
        assertThat(Double.valueOf(label.getText())).isGreaterThan(40.0).isLessThan(60.0);
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = FxmlMagicNodes.globallyWiredNode().byConventionFrom(SimpleViewController.class);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
