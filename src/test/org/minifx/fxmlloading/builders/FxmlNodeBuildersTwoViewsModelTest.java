/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.minifx.fxmlloading.factories.ModelSharingControllerFactory;
import org.minifx.fxmlloading.lang.model.SimpleViewController;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class FxmlNodeBuildersTwoViewsModelTest extends ApplicationTest {

    private Parent node2;
    private Parent node1;

    @Test
    public void modelsOfRepeatedViewAreIndependent()  {
        Node sliderController1 = node1.lookup("#slider1");
        clickOn(sliderController1);

        Label labelController1 = (Label) node1.lookup("#label1");
        Label labelController2 = (Label) node2.lookup("#label1");

        assertThat(labelController1.getText()).isNotEqualTo("0.0");
        assertThat(labelController2.getText()).isEqualTo("0.0");


        assertTrue(true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(rootNode()));
        stage.show();
    }

    private Parent rootNode() {
        TabPane tab = new TabPane();
        node2 = FxmlNodeBuilders.byConventionFrom(SimpleViewController.class)
                .controllersFrom(ModelSharingControllerFactory.newDefault()).build();

        node1 = FxmlNodeBuilders.byConventionFrom(SimpleViewController.class)
                .controllersFrom(ModelSharingControllerFactory.newDefault()).build();
        tab.getTabs().addAll(new Tab("a", node1), new Tab("b", node2));
        return tab;
    }

}
